package coursework_question3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trader extends Dealership{

	List<Seller> sellers;
	
	public Trader(String name) {
		super(name);
		this.sellers = new ArrayList<Seller>();
	}

	@Override
	public String displaySoldCars() {
		DecimalFormat style = new DecimalFormat("00.00");
		String s = "";
		
		for (Map.Entry<Advert, User> set : soldCars.entrySet()) {
			
			s += set.getKey().getCar().getID() + " - Purchased by " + set.getValue().toString() + " with a successful £" + style.format(set.getKey().getOffers().get(set.getKey().getOffers().size()-1).getValue()) + " bid. " + "\n";

		}
		return "SOLD CARS:" + "\n" + s;
	}

	@Override
	public String displayStatistics() {
		return "Statistics";
	}

	@Override
	public String displayUnsoldCars() {
		DecimalFormat style = new DecimalFormat("00.00");
		String s = "";
		
		for (Map.Entry<Advert, User> set : unsoldCars.entrySet()) {
			
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
				this.buyer = user;
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
					this.carsForSale.put(advert, user);
					}
			}
	}
	
	private boolean checkExistence(Car car) {
		for (Map.Entry<Advert, User> set : carsForSale.entrySet()) {
			if(car == set.getKey().getCar()) {
				this.carExists = true;
			}
		}
		for (Map.Entry<Advert, User> set : unsoldCars.entrySet()) {
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
				this.soldCars.put(advert, buyer);
				this.carsForSale.remove(advert);
			} else {
				
			}
		}
		
	}

}
