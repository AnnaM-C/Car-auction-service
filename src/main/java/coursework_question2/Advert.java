package coursework_question2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Advert {
	
	private Car car;
	private List<Offer> offers;
	

	public Advert(Car car) {
		this.car = car;
		this.offers = new ArrayList<Offer>();
	}
	
	
	public Car getCar() {
		return this.car;
	}
	
	public Offer getHighestOffer() {
	
		Offer highestOffer = null;
		
		for (int i = 0; i < offers.size(); i++) {
			
			
			if(offers.get(i).getValue() > offers.iterator().next().getValue()) {
			
				highestOffer = offers.get(i);
			}
		}
		
		return highestOffer;
	}
	
	public boolean placeOffer(User buyer, int value) throws NullPointerException {
		
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
