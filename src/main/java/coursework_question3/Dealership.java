package coursework_question3;


import java.util.HashMap;
import java.util.Map;

public abstract class Dealership {
	
	
	protected String name;
	protected User buyer;
	protected User seller;
	protected boolean offerPlaced = false;
	protected Map<Advert, User> carsForSale;
	protected Map<Advert, User> soldCars;
	protected Map<Advert, User> unsoldCars;
	protected boolean carExists = false;
	protected boolean offerSuccessful;
	
	
	public Dealership(String name) {
		this.name = name;
		this.carsForSale = new HashMap<Advert, User>();
		this.soldCars = new HashMap<Advert, User>();
		this.unsoldCars = new HashMap<Advert, User>();
	}
	
	
	public abstract String displaySoldCars();
	
	public abstract String displayStatistics();
	
	public abstract String displayUnsoldCars();
	
	public abstract boolean placeOffer(Advert carAdvert, User user, double value);
	
	public abstract void registerCar(Advert advert, User user, String colour, CarType type, CarBody body, int noOfSeats);

	
}
