# Car-auction-service

Java program showcasing a real-world car auction service application.

In this program we explore object-orientation principles and JUnit Testing.  
• Define classes, fields, constructors and methods
• Manipulate collections of objects, lists and maps
• Implement an inheritance structure
• Use JUnit within Eclipse to define test classes.
• Exceptions and Error handling • Use of Generic classes
• File reading/writing


The program has the following functionality:
A. A user has a name and a surname (known as full name).
B. A user can place an offer to an existing car advert.
C. A user can be a seller or a buyer. A seller can provide cars to the dealership, whereas buyers can only place offers to the available cars.
D. An advert consists of a car and a list of offers that have been placed by users.
E. Each offer consists of a user and an associated value which must be greater than 0.
F. The system will be able to determine the highest offer/price for any car advert. This will determine whether a car was successfully purchased at the end of the sale.
G. Each car has its own specification stored including its unique id, name, colour, reserved price, type of gearbox, type of car body, a set number of seats, and its condition.
H. The reserve price for each car needs to be greater than or equals to 0.
I. Some properties of the car are fixed, and can only take the form of one of the values in the group of constants (unchangeable variables, like final variables). This is particularly the case with the gearbox, the body of the car and the car’s condition.
J. Each car has to be registered in the system before a user can place an offer for or buy it.
K. A car cannot be registered if it is already being processed or auctioned.
L. An auctioneer is part of the system, and keeps track of the cars (that are for auction) that are currently being sold, and a history of the cars that have been sold and/or remain unsold at the end of a sale.
M. The system shall be able to control the end of the sale for a particular car advert.
N. The system shall allow a buyer to place an offer provided that the ‘Biddable’ car (meaning the car for auction) is registered as being for sale and that the new offer value is higher than the current highest value for that car advert.
O. The system shall allow a buyer to buy a car directly (if not in the auction), provided that the car is available. Upon a successful purchase, the sale of the car should end, causing the car advert to also be removed.
P. The system will be able to display all the sold cars for any car dealership; auctioneer or trader.
Q. The system will be able to display all the unsold cars for any dealership.
R. The system will be able to display some key statistics depending on the type of the dealership.
S. A dealership can be an auctioneer or a trader. An auctioneer is responsible for managing all the car adverts that are available for auction, on which buyers can place an offer. A trader is responsible for managing the car adverts that are available for direct purchase.
T. The system differentiates its functionality for different types of users (buyers, sellers).
U. Buyers must be at least 18 years old. Anyone requesting to purchase a car below the age of 18 should automatically be rejected by the system.
