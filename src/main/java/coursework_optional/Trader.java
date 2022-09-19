package coursework_optional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trader extends Dealership{

	List<Seller> sellers;
	private int totalCars = 0;
	
	public Trader(String name) {
		super(name);
		this.sellers = new ArrayList<Seller>();
	}

	@Override
	public String displaySoldCars() {
		DecimalFormat style = new DecimalFormat("00.00");
		String s = "";
		
		for (Map.Entry<Advert, Buyer> set : soldCars.entrySet()) {
			
			s += set.getKey().getCar().getID() + " - Purchased by " + set.getValue().toString() + " with a successful £" + style.format(set.getKey().getOffers().get(set.getKey().getOffers().size()-1).getValue()) + " bid. " + "\n";

		}
		return "SOLD CARS:" + "\n" + s;
	}

	@Override
	public String displayStatistics() {
		String sellerList = "";
		for (Seller seller : sellers) {
			sellerList += "\n" + "\t" + seller;
		}
		String s = "** Trader - AutoTrader**" + "\n"
				+ "Total Sales: " + this.totalCars + "\n"
				+ "All Sellers:" + sellerList + "\n";
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
				if(carAdvert.getCar().getType() == SaleType.FORSALE && value >= carAdvert.getCar().getPrice()) {
					try {
						carAdvert.placeOffer(user, value);
						this.offerPlaced = true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		endSale(carAdvert);
		return this.offerPlaced;
	}

	@Override
	public void registerCar(Advert advert, User user, String colour, CarType type, CarBody body, int noOfSeats) {
		if (advert == null) {
			throw new IllegalArgumentException();
			} else {
				if(checkExistence(advert.getCar()) == false) {
					advert.getCar().setBody(body);
					advert.getCar().setColour(colour);
					advert.getCar().setGearbox(type);
					advert.getCar().setNumberOfSeats(noOfSeats);
					advert.getCar().setType(SaleType.FORSALE);
					this.seller = new Seller(user.getFullName());
					this.carsForSale.put(advert, this.seller);
					if (this.sellers.isEmpty()) {
						this.sellers.add(this.seller);
					}
					
					int i = 1;
					for (Seller s : sellers) {
						if(s.getName().equals(this.seller.getName())) {
							break;
						}
						if (i++ == sellers.size()) {
							this.sellers.add(this.seller);
					        break;					        
					    }
						if(s != this.seller) {
							continue;
						}					
					}					
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
			if(this.offerPlaced == true) {
				this.soldCars.put(advert, advert.getOffers().get(advert.getOffers().size()-1).getBuyer());
				for(Map.Entry<Advert, Seller> set2 : carsForSale.entrySet()) {
					if(set2.getKey().getCar().equals(advert.getCar())) {
						Seller sel = set2.getValue();
						for (Seller s : sellers) {
							if (sel.getName().equals(s.getName()))
								s.addSales();
						}

					}
				}
				this.carsForSale.remove(advert);
			} else {
				
			}
			
			
			updateStatistics(this.seller);
		}
	}
	
	private void saveInFile(int noOfSales) {
		String sellerList = "";
		for (Seller seller : sellers) {
			sellerList += "\n" + "\t" + seller;
		}
		String s = "Total Sales: " + noOfSales + "\n"
				+ "All Sellers:" + sellerList + "\n";
	
		try {
			File file = new File("trade_statistics.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter("trade_statistics.txt");
			writer.write(s);
			writer.close();
		} catch (IOException e) {
			System.out.println("There was an error.");
			e.printStackTrace();
		}
		
	}
	
	private void updateStatistics(Seller seller) {
		this.totalCars = 0;
		
		for (Map.Entry<Advert, Buyer> set : soldCars.entrySet()) {
				this.totalCars++;
		}
		displayStatistics();
		saveInFile(this.totalCars);
	}

}
