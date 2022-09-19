package coursework_question1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class Advert {
	
	private Car car;
	private List<Offer> offers;
	private Offer highestOffer;
	

	public Advert(Car car) {
		this.car = car;
		this.offers = new ArrayList<Offer>();
	}
	
	
	public Car getCar() {
		return this.car;
	}
	
	public Offer getHighestOffer() {
		double value = 0;
		for(Offer o : offers) {
			if(this.highestOffer == null) {
				this.highestOffer = o;
			}
			value = o.getValue();
			if (this.highestOffer.getValue() > value) {
				continue;
			}
			else {
				this.highestOffer = o;
			}
		}
		
		return this.highestOffer;
	}
	
	public boolean placeOffer(User buyer, int value) throws Exception {
		
		boolean offerPlaced = false;
			Offer offer = new Offer(buyer, value);

			if (offer != null ) {
				if(value <= 0) {
					throw new NullPointerException();
				} else {
					offers.add(offer);
					offerPlaced = true;
				}
				}
		
		return offerPlaced;
	}
	
	public String toString() {
		
		DecimalFormat style = new DecimalFormat("00.00");
		
		String s = "Ad: " + car.getID() + " - " + car.getName() + " (£" + style.format(car.getPrice()) + ")" + "\n" + "\t" + " Type: " + car.getGearbox() + "\n" + "\t" + " Style: " + car.getBodyStyle() + "\n" + "\t" + " Colour: " + car.getColour() + "\n" + "\t" + " No. of Seats: " + car.getNumberOfSeats() + "\n" + "\t" + " Condition: " + car.getCondition();;
		
		return s;
	}
	
}
