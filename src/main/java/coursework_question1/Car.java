package coursework_question1;

import java.text.DecimalFormat;

public class Car {
	
	private int id;
	private String name;
	private String colour;
	private double reservedPrice;
	private CarType gearbox;
	private CarBody bodyStyle;
	private int numberOfSeats;
	private Condition condition;
	private int price;
	
	
	public Car(int id, String name, int price, Condition condition) {
		
		if(id <= 0) {
			throw new IllegalArgumentException();	
	} else {
		this.id = id;
	}
		
		if(name == null) {
			throw new IllegalArgumentException();	
	} else {
		this.name = name;
	}
		
		if(price <= 0) {
			throw new IllegalArgumentException();	
	} else {
		this.price = price;
	}

		this.condition = condition;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getColour() {
		return this.colour;
	}
	
	public double getReservedPrice() {
		return this.getPrice();
	}
	
	public CarType getGearbox() {
		return this.gearbox;
	}
	
	public CarBody getBodyStyle() {
		return this.bodyStyle;
	}
	
	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}
	
	public Condition getCondition() {
		return this.condition;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setBody(CarBody body) {
		this.bodyStyle = body;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setGearbox(CarType gearbox) {
		this.gearbox = gearbox;
	}
	
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	public String displayCarSpecification() {
		
		DecimalFormat style = new DecimalFormat("00.00");
		
		return this.getID() + " - " + this.getName() + " (£" + style.format(this.getPrice()) + ")" + "\n" + "\t" + " Type: " + this.getGearbox() + "\n" + "\t" + " Style: " + this.getBodyStyle() + "\n" + "\t" + " Colour: " + this.getColour() + "\n" + "\t" + " No. of Seats: " + this.getNumberOfSeats() + "\n" + "\t" + " Condition: " + this.getCondition();
	}
	
	public String toString() {
		
		return this.getID() + " - " + this.getName();
	}
	
}
