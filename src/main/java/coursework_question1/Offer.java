package coursework_question1;

public class Offer {
	
	private double value;
	private User buyer;
	
	
	public Offer(User buyer, double value) {
		if(buyer == null) {
			throw new IllegalArgumentException();	
	} else {
		this.buyer = buyer;
	}
		
		if(value <= 0) {
			throw new IllegalArgumentException();	
	} else {
		this.value = value;
	}

	}
	
	public User getBuyer() {
		return this.buyer;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public String toString() {
		
		return buyer.getFullName() + " offered £" + this.getValue();
	}

}
