package coursework_question4;


import java.util.HashMap;
import java.util.Map;

public abstract class Dealership {
	
	
	protected String name;
	protected Buyer buyer;
	protected Seller seller;
	protected boolean offerPlaced = false;
	protected Map<Advert, Seller> carsForSale;
	protected Map<Advert, Buyer> soldCars;
	protected Map<Advert, Seller> unsoldCars;
	protected boolean carExists = false;
	protected boolean offerSuccessful;
	
	
	public Dealership(String name) {
		this.name = name;
		this.carsForSale = new HashMap<Advert, Seller>();
		this.soldCars = new HashMap<Advert, Buyer>();
		this.unsoldCars = new HashMap<Advert, Seller>();
	}
	
	public abstract String displaySoldCars();
	
	public abstract String displayStatistics();
	
	public abstract String displayUnsoldCars();
	
	public abstract boolean placeOffer(Advert carAdvert, User user, double value);
	
	public abstract void registerCar(Advert advert, User user, String colour, CarType type, CarBody body, int noOfSeats);

}
