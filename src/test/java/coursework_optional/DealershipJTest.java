package coursework_optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DealershipJTest {
	Dealership dealership = null;
	
	@Test
	public void coursework_testAdvertMultipleCars() {
		Auctioneer dealership = new Auctioneer("Auto Auction");
		
		User seller = new Seller("Stella Kazamia");
		User seller2 = new Seller("Adam Hills");
		
		User buyer = new Buyer("Alice Wonderland", 50);
		User buyer2 = new Buyer("Bob Wonderland", 56);
		
		Car car6 = new Car(2345, "Mercedes-Benz A Class", 15000, Condition.NEW, SaleType.FORSALE);
		Car car7 = new Car(car6);
		Car car8 = new Car(car6);
		Car car9 = new Car(car6);
		Car car10 = new Car(car6);
		Car car11 = new Car(car6);
		Car car12 = new Car(car6);
		Car car13 = new Car(car6);
		Car car14 = new Car(car6);
		Car car15 = new Car(car6);
		
		Car car = new Car(1234, "Toyota Corolla", 2000, Condition.USED, SaleType.FORSALE);
		Car car2 = new Car(car);
		Car car3 = new Car(car);
		Car car4 = new Car(car);
		Car car5 = new Car(car);
		
		List<Car> cars2 = new ArrayList<Car>();
		cars2.add(car6);
		cars2.add(car7);
		cars2.add(car8);
		cars2.add(car9);
		cars2.add(car10);
		cars2.add(car11);
		cars2.add(car12);
		cars2.add(car13);
		cars2.add(car14);
		cars2.add(car15);
		
		List<Car> cars = new ArrayList<Car>();
		cars.add(car);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		
		Advert ad2 = new Advert(cars2);
		dealership.registerCar(ad2, seller2, "Blue", CarType.MANUAL, CarBody.HATCHBACK, 5);
		
		Advert ad = new Advert(cars);
		dealership.registerCar(ad, seller, "Red", CarType.AUTOMATIC, CarBody.MICRO, 4);
		
		dealership.placeOffer(ad2, buyer2, 15000);
		dealership.placeOffer(ad2, buyer2, 15000);
		dealership.placeOffer(ad2, buyer2, 15000);
		dealership.placeOffer(ad2, buyer2, 15000);
		dealership.placeOffer(ad2, buyer2, 15000);
		dealership.placeOffer(ad2, buyer2, 15000);
		dealership.placeOffer(ad2, buyer2, 15000);

		dealership.placeOffer(ad, buyer, 2000);
		dealership.placeOffer(ad, buyer, 2000);
		dealership.placeOffer(ad, buyer, 2000);
		
		dealership.endSale(ad2);
		dealership.endSale(ad);

		
		
		assertEquals(
				"SOLD CARS:\n" + "1234 - Purchased by A***e at 2000.00 (purchased 3)\n" + 
						"2345 - Purchased by B***b at 15000.00 (purchased 7)\n\n\n" +
				"UNSOLD CARS:\n" + "Ad: 1234 - Toyota Corolla (£2000.00) [ONLY 2 LEFT]\n" + "	 Type: AUTOMATIC\n"
						+ "	 Style: MICRO\n" + "	 Colour: Red\n" + "	 No. of Seats: 4\n" + "	 Condition: USED\n" +
				"Ad: 2345 - Mercedes-Benz A Class (£15000.00) [ONLY 3 LEFT]\n" + "	 Type: MANUAL\n"
						+ "	 Style: HATCHBACK\n" + "	 Colour: Blue\n" + "	 No. of Seats: 5\n" + "	 Condition: NEW\n" ,
						dealership.displaySoldCars() + dealership.displayUnsoldCars());
		
	}
	
}
