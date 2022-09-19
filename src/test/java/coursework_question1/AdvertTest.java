package coursework_question1;
import static org.junit.Assert.*;

import org.junit.Test;

public class AdvertTest {

Advert ad = null;
	
	@Test
	public void coursework_testAdvertConstruction() {
		Car car = new Car(1234, "Mazda MX5", 20000, Condition.NEW);
		ad = new Advert(car);
	}
	
	@Test
	public void coursework_testGetCar() {
		Car car = new Car(2345, "Toyota Corolla", 7000, Condition.USED);
		assertEquals(2345, car.getID());
		assertEquals("Toyota Corolla", car.getName());
		assertEquals(7000, car.getPrice(), 0);
	}

	@Test
	public void coursework_testGetHighestOffer() throws Exception {
		Car car = new Car(1234, "Mazda MX5", 20000, Condition.NEW);
		Advert ad = new Advert(car);
		User user = new User("John Smith");
		User user2 = new User("John Smith");
		User user3 = new User("John Smith");
		
		ad.placeOffer(user, 50000);
		ad.placeOffer(user2, 40000);
		ad.placeOffer(user3, 30000);
		
		assertEquals("John Smith offered £50000.0", ad.getHighestOffer().toString());
	}
	
	@Test
	public void coursework_testPlaceOffer() throws Exception {
		Car car = new Car(1234, "Mazda MX5", 20000, Condition.NEW);
		User buyer = new User("Stella Kazamia");
		Advert ad = new Advert(car);

		assertTrue(ad.placeOffer(buyer, 10000));
	}
	
	@Test(expected = NullPointerException.class)
	public void coursework_testInvalidPlaceOffer() throws Exception {
		ad.placeOffer(null, 0);

	}
	
	@Test
	public void coursework_testToString() {
		Car car = new Car(1234, "Mazda MX5", 20000, Condition.NEW);
		Advert ad = new Advert(car);

		car.setBody(CarBody.HATCHBACK);
		car.setColour("Blue");
		car.setGearbox(CarType.AUTOMATIC);
		car.setNumberOfSeats(5);
		
		assertEquals(
				"Ad: 1234 - Mazda MX5 (£20000.00)\n" + "	 Type: AUTOMATIC\n" + "	 Style: HATCHBACK\n"
						+ "	 Colour: Blue\n" + "	 No. of Seats: 5\n" + "	 Condition: NEW",
						ad.toString());
	System.out.println(ad.toString());
	}

}
