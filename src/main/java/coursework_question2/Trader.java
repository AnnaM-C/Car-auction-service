package coursework_question2;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Trader {
	
	protected String name;
	protected User buyer;
	protected User seller;
	protected boolean offerPlaced = false;
	protected Map<Advert, User> carsForSale;
	protected Map<Advert, User> soldCars;
	protected Map<Advert, User> unsoldCars;
	
	public Trader(String name) {
		this.name = name;
		this.carsForSale = new HashMap<Advert, User>();
		this.soldCars = new HashMap<Advert, User>();
		this.unsoldCars = new HashMap<Advert, User>();
	}

	
	
public String displaySoldCars() {
		
		DecimalFormat style = new DecimalFormat("00.00");
		String s = "";
		
		for (Map.Entry<Advert, User> set : soldCars.entrySet()) {
			
			s += set.getKey().getCar().getID() + " - Purchased by " + set.getValue().getName() + " with a successful £" + style.format(set.getKey().getCar().getPrice()) + " bid. " + "\n";
			
			//s += set.getKey() + set.getValue().getFullName();

		}
		
		return "SOLD CARS:" + "\n" + s;
	}
		
	
	public String displayStatistics() {
		
		String s = "Statistics";
		
		return s;
	}
	
	
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
	
		
	public void registerCar(Advert advert, User user, String colour, CarType type, CarBody body, int noOfSeats) {
		
		if (advert == null) {
			
			throw new IllegalArgumentException();
			
			} else {
				this.seller = user;
				
				if (this.checkExistence(advert.getCar()) == false) {
					advert.getCar().setBody(body);
					advert.getCar().setColour(colour);
					advert.getCar().setGearbox(type);
					advert.getCar().setNumberOfSeats(noOfSeats);
					
					this.carsForSale.put(advert, user);

				}
					
				}
				
			}
	
	
	private boolean checkExistence(Car car) {
		
		boolean carExists = false;
		
		for (Map.Entry<Advert, User> set : carsForSale.entrySet()) {
			
			if(car == set.getKey().getCar()) {
				carExists = true;
			}
		}
		
		return carExists;
	}
		
	

	
	
	public boolean placeOffer(Advert carAdvert, User user, double value) {
		
		
		if (carAdvert == null) {

			throw new IllegalArgumentException();	
			
			} else { 		
			
			if (checkExistence(carAdvert.getCar()) == true) {
				
				this.offerPlaced = true;
				this.buyer = user;
				
				
				if (value < carAdvert.getCar().getReservedPrice()) { 
					this.unsoldCars.put(carAdvert, user);
				
				}
				
				if(value >= carAdvert.getCar().getReservedPrice()) {
					this.soldCars.put(carAdvert, user);
					
					//remove the advert and user from unsold cars
					//this.unsoldCars.entrySet().iterator();
					
					for (Map.Entry<Advert, User> entry : unsoldCars.entrySet()) {
					    Advert key = entry.getKey();
					    User val = entry.getValue();
					    if(key == carAdvert) {
						    this.unsoldCars.remove(key);
					    }
					}
					
					for (Map.Entry<Advert, User> entry : carsForSale.entrySet()) {
					    Advert key = entry.getKey();
					    User val = entry.getValue();
					    if(key == carAdvert) {
						    this.carsForSale.remove(key);
					    }
					}
					
				}
				
			}
			}
			endSale(carAdvert);	
			return offerPlaced;
	
		}
	
	

	
	public void endSale(Advert advert) {
		
		if (advert == null) {
			throw new IllegalArgumentException();	
		}
		else {
			if (soldCars.containsKey(advert)) {
				//remove the ad so no further purchases can be made
				this.carsForSale.remove(advert);
			}

		}
			
	}

}
