/**
 * Auctioneer.java
 */

package coursework_question3;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * The Auctioneer class manages the car adverts for auction
 * @author annacarter
 *
 */

public class Auctioneer extends Dealership {
	
	/**
	 * HashMap of key value paires, sellers and the number of sales the seller has
	 */
	private Map<Seller, Integer> sales;
	
	/**
	 * The top seller
	 */
	private Seller topSeller;

	/**
	 * Parameterised Constructor. Initialise the sales HashMap.
	 * @param name
	 * 		The Name of the auctioneer.
	 */
	public Auctioneer(String name) {
		super(name);
		this.sales = new HashMap<Seller, Integer>();
	}
	
	
	/**
	 * Method checks if the car already exists in the carsForSale list. This ensures if any cars which are already listed as for sale are 
	 *     not added again.
	 * @param car
	 * @return boolean true of false for carExists variable
	 */
	
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
	

	/**
	 * Method to display the sold cars as a String
	 * Parameters, none
	 * @return a formatted String that includes, the sold Car ID, the name of the buyer and the price the sold car was bought for.
	 */
	
	@Override
	public String displaySoldCars() {
		DecimalFormat style = new DecimalFormat("00.00");
		String s = "";
		
		for (Map.Entry<Advert, User> set : soldCars.entrySet()) {
			
			s += set.getKey().getCar().getID() + " - Purchased by " + set.getValue().toString() + " with a successful £" + style.format(set.getKey().getCar().getPrice()) + " bid. " + "\n";

		}
		return "SOLD CARS:" + "\n" + s;
	}
	
	/**
	 * A method to display the word 'Statistics'
	 * Parameters, none
	 * @return the String 'Statistics'
	 */

	@Override
	public String displayStatistics() {
		return "Statistics";
	}

	/**
	 * A method to display the unsold cars as a String
	 * Parameters, none
	 * @return formatted String to include, the ID, name, asking price, type, style, colour, number of seats, and condition of unsold car.
	 */
	
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
	
	
	/**
	 * Method to manage the advert at the end of the sale.
	 * @param advert
	 */
	
	public void endSale(Advert advert) {
		if (advert == null) {
			throw new IllegalArgumentException();	
		}
		else {
		int size = advert.getOffers().size()-1;
		Offer offer = advert.getOffers().get(size);
		double price = advert.getCar().getPrice();
		for (Map.Entry<Advert, User> set : carsForSale.entrySet()) {
			if(advert.getCar() == set.getKey().getCar() && this.offerSuccessful == true) {
				this.soldCars.put(advert, offer.getBuyer());
				this.carsForSale.remove(advert);
			} else {
				Offer highestOffer = advert.getHighestOffer();
				if(highestOffer.getValue() > price ) {
					this.soldCars.put(advert, offer.getBuyer());
				} else {
					this.unsoldCars.put(advert, offer.getBuyer());
				}
			}
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
	
	/**
	 * Method permits a seller to add a car to the carsForSale HashMap, and adds additional information such as body type, colour, gearbox
	 *     and number of seats of the car to the advert.
	 * @param advert
	 * @param user
	 * @param colour
	 * @param type
	 * @param body
	 * @param noOfSeats
	 */

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
					advert.getCar().setType(SaleType.AUCTION);
					this.carsForSale.put(advert, user);
					}
			}
	}
	

}	
