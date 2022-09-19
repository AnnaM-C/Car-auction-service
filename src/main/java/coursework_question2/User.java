package coursework_question2;


public class User {
	
	private String fullname;
	
	
	public User(String fullname) {
		
		String regExp = "([A-Z][a-z]*) ([A-Z][a-z]*)";
		
			if(fullname.matches(regExp)) {
				this.fullname = fullname;
		} else {
			throw new IllegalArgumentException();
		}
		
	}

	
	public String getName() {
		
		String[] names = this.fullname.split(" ");
		String firstName = names[0];
		
		return firstName;
	}
	
	public String getFullName() {
		return this.fullname;
	}
	
	public String toString() {
		return this.getFullName();
	}
}

