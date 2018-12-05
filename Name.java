package Lab6;

public class Name implements Comparable <Name>{
	private String firstName;
	private String lastName;

	public Name (String first, String last) {
		firstName = first; 
		lastName = last;
	}

	public Name (String fullName) {
		firstName = fullName;
		lastName = null;
	}


	public String getFirst() {
		return firstName;
	}

	public String getLast() {
		return lastName;
	}

	public String toString () {
		if(lastName != null)
			return (firstName + " " + lastName);
		else
			return firstName;
	}
	public boolean equals (Object o) {
		Name n = (Name) o;
		if(lastName != null && n.getLast() != null)
			return firstName.equals(n.getFirst()) && lastName.equals(n.getLast());
		else
			return firstName.equals(n.getFirst());
	}

	public int compareTo (Name other) {
		int a = 0;
		int b = 0;
		
		String c = toString();
		char[] d = c.toCharArray();
		
		String e = other.toString();
		char[] f = e.toCharArray();
		
		for(int i = 0; i < d.length; i++)
			a += d[i];
		
		for(int i = 0; i < f.length; i++)
			b += f[i];
		
		if(a < b)
			return -1;
		if(a > b)
			return 1;
		else
			return 0;
	}
}
