package coursework_optional;


public abstract class User {
	
	private String fullname;
	
	public User(String fullname) {
		
		String regExp = "([A-Z][a-z]*) ([A-Z][a-z]*)";
		
			if(fullname.matches(regExp)) {
				this.fullname = fullname;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public String getFullName() {
		return this.fullname;
	}

	public abstract String getName();
	
	public abstract String toString();
	
	
	
}

