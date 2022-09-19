package coursework_optional;

public class Buyer extends User {
	
	private int age;
	private char firstCharacter;
	private char lastCharacter;

	
	public Buyer(String name, int age) {
		super(name);
		
		if(age < 18) {
			throw new IllegalArgumentException();
		} else {
			this.age = age;	
		}
	}
	
	public int getAge() {
		return this.age;
	}
	
	@Override
	public String toString() {
		String name = this.getName();
		char [] nameArray = name.toCharArray();
			
		for (char c : nameArray) {
			
			if(c == nameArray[0]) {
				this.firstCharacter = name.charAt(0);
			}
			
			if (c == nameArray[nameArray.length-1]) {
				this.lastCharacter = name.charAt(name.length()-1);
			}

		}
		return this.firstCharacter + "***" + this.lastCharacter;
	}

	@Override
	public String getName() {
		String[] names = this.getFullName().split(" ");
		String firstName = names[0];
		
		return firstName;
	}


}

