package coursework_question4;

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
	
	public Buyer getBuyer() {
		return (Buyer) this.buyer;
	}
	
	
	public double getValue() {
		return this.value;
	}
	
	public String toString() {
		
		return buyer.toString() + " offered £" + this.getValue();
	}

}
