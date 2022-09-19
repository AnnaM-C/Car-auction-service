package coursework_question3;

public class Seller extends User {
	
	public Seller(String name) {
		super(name);
		
	}

	public String toString() {
		String firstName = this.getName();
		String[] names = this.getFullName().split(" ");
		String lastName = names[1];
		char lastNameInitial = lastName.charAt(0);
		
		return firstName + " " + lastNameInitial + ". "+  "()";
	}
	
}
