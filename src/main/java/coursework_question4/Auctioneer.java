/**
 * Auctioneer.java
 */

package coursework_question4;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * The Auctioneer class manages the car adverts for auction
 * @author annacarter
 *
 */

public class Auctioneer extends Dealership {
	
	private Map<Seller, Integer> sales;
	private Seller topSeller;
	private double totalAutomatic = 0.0;
	private double totalManual = 0.0;
	private int totalCars = 0;
	private User user;

	
	public Auctioneer(String name) {
		super(name);
		this.sales = new HashMap<Seller, Integer>();
	}
	
	public Map<Seller, Integer> getSales() {
		return this.sales;
	}

	@Override
	public String displaySoldCars() {
		DecimalFormat style = new DecimalFormat("00.00");
		String s = "";
		
		for (Map.Entry<Advert, Buyer> set : soldCars.entrySet()) {
			
			s += set.getKey().getCar().getID() + " - Purchased by " + set.getValue().toString() + " with a successful £" + style.format(set.getKey().getCar().getPrice()) + " bid. " + "\n";

		}
		return "SOLD CARS:" + "\n" + s;
	}

	@Override
	public String displayStatistics() {
		String s = "** Auctioneer - Cars Ltd**" + "\n"
				+ "Total Auction Sales: " + this.totalCars + "	 Automatic Cars: "+ (this.totalAutomatic/this.totalCars)*100 + "%	 Manual Cars: " + (this.totalManual/this.totalCars)*100 + "%" + "\n"
				+ "Top Seller: " + this.topSeller + "\n";
		return s;
	}

	@Override
	public String displayUnsoldCars() {
		DecimalFormat style = new DecimalFormat("00.00");
		String s = "";
		
		for (Map.Entry<Advert, Seller> set : unsoldCars.entrySet()) {
			
			if(unsoldCars != null) {
				
			s += "Ad: " + set.getKey().getCar().getID()+ " - " + set.getKey().getCar().getName() + " (£" + style.format(set.getKey().getCar().getPrice()) + ")" + "\n" + "\t" + " Type: " + set.getKey().getCar().getGearbox() + "\n" + "\t" + " Style: " + set.getKey().getCar().getBodyStyle() + "\n" + "\t" + " Colour: " + set.getKey().getCar().getColour() +  "\n" + "\t" +  " No. of Seats: " + set.getKey().getCar().getNumberOfSeats() + "\n" + "\t" + " Condition: " + set.getKey().getCar().getCondition() + "\n";
			
			} else {
				s = "";
			}
		}
		return "UNSOLD CARS:" + "\n" + s;
	}

	@Override
	public boolean placeOffer(Advert carAdvert, User user, double value) {
		if (carAdvert == null) {

			throw new IllegalArgumentException();	
			} else {
				try {
					carAdvert.placeOffer(user, value);
					this.offerPlaced = true;
					this.offerSuccessful = true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(carAdvert.getCar().getType() == SaleType.AUCTION && value <= carAdvert.getHighestOffer().getValue() && value < carAdvert.getCar().getPrice()) {
					this.offerSuccessful = false;
				}
			}
		
		return this.offerPlaced;
	}

	@Override
	public void registerCar(Advert advert, User user, String colour, CarType type, CarBody body, int noOfSeats) {
		if (advert == null) {
			throw new IllegalArgumentException();
			} else {
				this.user = user;
				if(checkExistence(advert.getCar()) == false) {
					advert.getCar().setBody(body);
					advert.getCar().setColour(colour);
					advert.getCar().setGearbox(type);
					advert.getCar().setNumberOfSeats(noOfSeats);
					advert.getCar().setType(SaleType.AUCTION);
					this.carsForSale.put(advert, this.seller);
					this.seller = new Seller(user.getFullName());
					}
			}
	}
	
	
	private boolean checkExistence(Car car) {
		for (Map.Entry<Advert, Seller> set : carsForSale.entrySet()) {
			if(car == set.getKey().getCar()) {
				this.carExists = true;
			}
		}
		for (Map.Entry<Advert, Seller> set : unsoldCars.entrySet()) {
			if(car == set.getKey().getCar()) {
				this.carExists = true;
			}
		}
		return this.carExists;
	}
	
	public void endSale(Advert advert) {
		if (advert == null) {
			throw new IllegalArgumentException();	
		}
		else {
		boolean settings = false;
		int size = advert.getOffers().size()-1;
		Offer offer = advert.getOffers().get(size);
		double price = advert.getCar().getPrice();
		for (Map.Entry<Advert, Seller> set : carsForSale.entrySet()) {
			if(advert.getCar() == set.getKey().getCar() && this.offerSuccessful == true) { //value > advert.getCar().getPrice()
				settings = true;
			} else {
				Offer highestOffer = advert.getHighestOffer();
				if(highestOffer.getValue() > price ) {
					this.soldCars.put(advert, offer.getBuyer());
					this.sales.put(this.seller, seller.getSales());
				} else {
					this.unsoldCars.put(advert, this.seller);
				}
				}
			}
		
		if (settings == true) {
			this.soldCars.put(advert, offer.getBuyer());
			this.seller.addSales();
			this.sales.put(this.seller, seller.getSales());
			this.carsForSale.remove(advert);
		}
		
		updateStatistics(this.seller);
		}
	}
	
	private void saveInFile(int noOfSales, double percentageOfUsed, double percentageOfNew) {
		
		if (Double.isNaN(percentageOfUsed)) {
			percentageOfUsed = 0;
		}
		if (Double.isNaN(percentageOfNew)) {
			percentageOfNew = 0;
		}

		
		String s = "Total Auction Sales: " + noOfSales + " Automatic Cars: " + percentageOfUsed + "%	 Manual Cars: " + percentageOfNew + "%\n"
				+ "Top Seller: " + this.topSeller + "\n";
		try {
			File file = new File("auction_statistics.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter("auction_statistics.txt");
			writer.write(s);
			writer.close();
		} catch (IOException e) {
			System.out.println("There was an error.");
			e.printStackTrace();
		}
		
	}
	
	private void updateStatistics(Seller seller) {
		this.totalAutomatic = 0;
		this.totalCars = 0;
		this.totalManual= 0;
		
		for (Map.Entry<Advert, Buyer> set : soldCars.entrySet()) {
				this.totalCars++;
			if(set.getKey().getCar().getGearbox() == CarType.AUTOMATIC) {
				this.totalAutomatic++;
			}
			if(set.getKey().getCar().getGearbox() == CarType.MANUAL) {
				this.totalManual++;
			}
		}
		for (Map.Entry<Seller, Integer> set : sales.entrySet()) {
			if(this.topSeller == null) {
				this.topSeller = set.getKey();
			}
			if(this.topSeller.getSales() < set.getKey().getSales()) {
				this.topSeller = set.getKey();
			} else {
				continue;
			}
		}
		displayStatistics();
		saveInFile(this.totalCars, (this.totalAutomatic/this.totalCars)*100, (this.totalManual/this.totalCars)*100);
	}
}	
