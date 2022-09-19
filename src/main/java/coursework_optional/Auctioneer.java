/**
 * Auctioneer.java
 */

package coursework_optional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

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
				s += set.getKey().getCar().getID() + " - Purchased by " + set.getValue().toString() + " at " + style.format(set.getKey().getCar().getPrice()) + " (purchased " + set.getKey().getCarsSold() + ")\n";
			
		}
		return "SOLD CARS:" + "\n" + s + "\n\n";
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
					s += "Ad: " + set.getKey().getCar().getID()+ " - " + set.getKey().getCar().getName() + " (£" + style.format(set.getKey().getCar().getPrice()) + ") [ONLY " + set.getKey().getCarsNotSold() + " LEFT]" + "\n" + "\t" + " Type: " + set.getKey().getCar().getGearbox() + "\n" + "\t" + " Style: " + set.getKey().getCar().getBodyStyle() + "\n" + "\t" + " Colour: " + set.getKey().getCar().getColour() +  "\n" + "\t" +  " No. of Seats: " + set.getKey().getCar().getNumberOfSeats() + "\n" + "\t" + " Condition: " + set.getKey().getCar().getCondition() + "\n";
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
						e.printStackTrace();
					}
					for (Car c : carAdvert.getCars()) {
						if(c.getType() == SaleType.AUCTION && value <= carAdvert.getHighestOffer().getValue() && value < c.getPrice()) {
							this.offerSuccessful = false;
					}
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
					for (Car c : advert.getCars()) {
						 
							c.setBody(body);
							c.setColour(colour);
							c.setGearbox(type);
							c.setNumberOfSeats(noOfSeats);
							c.setType(SaleType.AUCTION);
							this.unsoldCars.put(advert, this.seller);
							this.seller = new Seller(user.getFullName());
						
					}
					}
			}

	
	public void endSale(Advert advert) {
		if (advert == null) {
			throw new IllegalArgumentException();	
		}
		else {
			for (Offer o : advert.getOffers()) {
					if(o.getValue() >= advert.getCars().get(0).getPrice() ) {
						this.soldCars.put(advert, o.getBuyer());
						advert.addCars();
						advert.removeCars();
						
					} else {

				}

			}
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
				for (Car c : set.getKey().getCars()) {
					if(c.getGearbox() == CarType.AUTOMATIC) {
						this.totalAutomatic++;
					}
					if(c.getGearbox() == CarType.MANUAL) {
						this.totalManual++;
					}
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
