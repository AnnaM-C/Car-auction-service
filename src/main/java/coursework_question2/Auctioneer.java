/**
 * Auctioneer.java
 */

package coursework_question2;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * The Auctioneer class manages the car adverts for auction
 * @author annacarter
 *
 */

public class Auctioneer {
	
	/**
	 * Name of Auctioneer
	 */
	protected String name;
	/**
	 * Name of buyer
	 */
	protected User buyer;
	/**
	 * Name of seller
	 */
	protected User seller;
	/**
	 * Determines if an offer has been placed
	 */
	protected boolean offerPlaced = false;
	/**
	 * HashMap of cars which are users have listed as available to sell
	 */
	protected Map<Advert, User> carsForSale;
	/**
	 * HashMap of cars which have been sold by a user
	 */
	protected Map<Advert, User> soldCars;
	/**
	 * HashMap of cars which are not sold by a user
	 */
	protected Map<Advert, User> unsoldCars;
	
	/**
	 * Parameterised Constructor. Initialises the Auctioneer name and the HashMaps, 'carsForSale', 'soldCars' and 'unsoldCars'.
	 * @param name
	 * 		The Name of the auctioneer.
	 */
	public Auctioneer(String name) {
		this.name = name;
		this.carsForSale = new HashMap<Advert, User>();
		this.soldCars = new HashMap<Advert, User>();
		this.unsoldCars = new HashMap<Advert, User>();
	}
	
	
	/**
	 * Method checks if the car already exists in the carsForSale list. This ensures if any cars which are already listed as for sale are
	 *     not added again.
	 * @param car
	 * @return boolean true of false for carExists variable
	 */
	private boolean checkExistence(Car car) {
		
		// carExists boolean variable is false
		boolean carExists = false;
		
		//Loop through the carsForSale HashMap
		for (Map.Entry<Advert, User> set : carsForSale.entrySet()) {
			
			//If the car object in function parameter equals the car object in the carsForSale list set the carExists variable to true.
			if(car == set.getKey().getCar()) {
				carExists = true;
			}
		}
		return carExists;
	}
	
	
	/**
	 * Method to display the sold cars as a String
	 * Parameters, none
	 * @return a formatted String that includes, the sold Car ID, the name of the buyer and the price the sold car was bought for.
	 */
	public String displaySoldCars() {
		
		//Ensures the price in the formatted return string is displayed with two decimal places.
		DecimalFormat style = new DecimalFormat("00.00");
		
		//Initialise s with an empty String.
		String s = "";
		
		//Loop through the sold cars HashMap.
		for (Map.Entry<Advert, User> set : soldCars.entrySet()) {
			
			s += set.getKey().getCar().getID() + " - Purchased by " + set.getValue().getName() + " with a successful £" + style.format(set.getKey().getCar().getPrice()) + " bid. " + "\n";
			
			//s += set.getKey() + set.getValue().getFullName();

		}
		
		return "SOLD CARS:" + "\n" + s;
	}
		
	/**
	 * A method to display the word 'Statistics'
	 * Parameters, none
	 * @return the String 'Statistics'
	 */
	public String displayStatistics() {
		
		//Store the word "Statistics" in a String variable.
		String s = "Statistics";
		
		return s;
	}
	
	/**
	 * A method to display the unsold cars as a String
	 * Parameters, none
	 * @return formatted String to include, the ID, name, asking price, type, style, colour, number of seats, and condition of unsold car.
	 */
	
	public String displayUnsoldCars() {
		
		//Ensures the price in the formatted return string is displayed with two decimal places.
		DecimalFormat style = new DecimalFormat("00.00");
		
		//Initialise s with an empty String.
		String s = "";
		
		//Loop through the unsold cars HashMap.
		for (Map.Entry<Advert, User> set : unsoldCars.entrySet()) {
			
			//If there are key value pairs in the unsold cars HashMap display the values as a String.
			if(unsoldCars != null) {
				
			//Add the values to store in the String and concatenate each unsold car if there is more than one.
			s += "Ad: " + set.getKey().getCar().getID()+ " - " + set.getKey().getCar().getName() + " (£" + style.format(set.getKey().getCar().getPrice()) + ")" + "\n" + "\t" + " Type: " + set.getKey().getCar().getGearbox() + "\n" + "\t" + " Style: " + set.getKey().getCar().getBodyStyle() + "\n" + "\t" + " Colour: " + set.getKey().getCar().getColour() +  "\n" + "\t" +  " No. of Seats: " + set.getKey().getCar().getNumberOfSeats() + "\n" + "\t" + " Condition: " + set.getKey().getCar().getCondition() + "\n";
			
			} else {
				s = "";
			}
		}
		return "UNSOLD CARS:" + "\n" + s;
		
	}
	
	
	/**
	 * Method removes the advert if the car was placed in the soldCars HashMap. The advert cannot be null otherwise an execption is thrown.
	 * @param advert
	 */
	
	public void endSale(Advert advert) {
		//Throw an IllegalArgumentException() if the advert is null
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
	
	/**
	 * Method allows a buyer to place an offer on a car if it exists in the carsForSale list. If the offer is successful the car is sold
	 *     and put in the soldCars HashMap. If the offer is unsuccessful the car is put in the unsoldCars HashMap. The advert cannot be null
	 *     otherwise an exception is thrown.
	 * @param carAdvert
	 * @param user
	 * @param value
	 * @return boolean offerPlaced to signal an offer has been places on the advert
	 */
	
	public boolean placeOffer(Advert carAdvert, User user, double value) {
		
		
		if (carAdvert == null) {
			//Throw an IllegalArgumentException() if the advert is null.
			throw new IllegalArgumentException();	
			} else { 		
			
			//If the advert is not null and the car exists an offer is placed and assign the user as a buyer.	
			if (checkExistence(carAdvert.getCar()) == true) {
				this.offerPlaced = true;
				this.buyer = user;
				
				//If the offer price is less than the asking price put the car into the unsold cars HashMap.
				if (value < carAdvert.getCar().getReservedPrice()) { 
					this.unsoldCars.put(carAdvert, user);
				
				}
				
				//If the offer price is equal to or more than the asking price, put the car into the sold cars HashMap.
				if(value >= carAdvert.getCar().getReservedPrice()) {
					this.soldCars.put(carAdvert, user);
					
					//If the sold car was put in the unsoldCars HashMap remove it from here as the car has now been sold.
					for (Map.Entry<Advert, User> entry : unsoldCars.entrySet()) {
					    Advert key = entry.getKey();
					    if(key == carAdvert) {
						    this.unsoldCars.remove(key);
					    }
					}
						
				}
				
			}
				}
		
			return offerPlaced;
	
		}
	
	
	/**
	 * Method permits a seller to add a car to the carsForSale HashMap, and adds additional information such as body type, colour, gearbox
	 *     and number of seats of the car to the advert
	 * @param advert
	 * @param user
	 * @param colour
	 * @param type
	 * @param body
	 * @param noOfSeats
	 */
	public void registerCar(Advert advert, User user, String colour, CarType type, CarBody body, int noOfSeats) {
		
		//Throw an IllegalArgumentException() if the advert is null
		if (advert == null) {
			throw new IllegalArgumentException();
			} else {
				//The user is a seller
				this.seller = user;
				
				//If the car has not been listed before, add the new car attributes to the advert
				if (this.checkExistence(advert.getCar()) == false) {
					advert.getCar().setBody(body);
					advert.getCar().setColour(colour);
					advert.getCar().setGearbox(type);
					advert.getCar().setNumberOfSeats(noOfSeats);
					this.carsForSale.put(advert, user);

				}
					
			}	
	}
}
	

