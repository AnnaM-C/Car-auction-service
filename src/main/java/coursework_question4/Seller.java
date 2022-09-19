package coursework_question4;

public class Seller extends User {
	
	private int sales;
	
	public Seller(String name) {
		super(name);
	}
	
	public int getSales() {
		return this.sales;
	}
	
	public void addSales() {
		this.sales++;
	}
	
	public String identifyRating() {
		String level = "";
			if (this.sales == 0) {
				level = "0";
			}
			if (this.sales > 0 && this.sales <= 5) {
				level = "1";
			}
			if (this.sales >= 6 && this.sales <= 10) {
				level = "2";
			}
			if (this.sales > 10) {
				level = "3";
			}
		return "Rating: Level " + level;
	}

	@Override
	public String toString() {
		String firstName = this.getName();
		String[] names = this.getFullName().split(" ");
		String lastName = names[1];
		char lastNameInitial = lastName.charAt(0);
		
		return firstName + " " + lastNameInitial + ". "+  "(Sales: " + this.getSales() + ", " + this.identifyRating() + ")";
	}

	@Override
	public String getName() {
		String[] names = this.getFullName().split(" ");
		String firstName = names[0];
		
		return firstName;
	}

	
}
