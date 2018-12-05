package Lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class TelephoneDirectory {
	private DictionaryInterface <Name, String> dictionary;
	public TelephoneDirectory (){
		dictionary = new ArrayDictionary <>();
		//dictionary = new SortedLinkedDictionary <> ();
	}

	public void display () {
		Iterator <Name> ni = dictionary.getKeyIterator();
		Iterator <String> si = dictionary.getValueIterator();

		while (ni.hasNext()) {
			System.out.println (ni.next() + " " + si.next());
		}

		System.out.println("Finished displaying");
	}

	public void readFile(Scanner data){
		Name fullName;
		while (data.hasNextLine()) {
			String a = data.nextLine();
			char[] listOfChar = a.toCharArray();
			String name = "", phoneNumber = "";
			for(int j = listOfChar.length - 13; j < listOfChar.length; j++)
				phoneNumber += listOfChar[j];
			for(int i = 0; i < listOfChar.length - 13; i++)
				name += listOfChar[i];
			fullName = new Name(name);
			dictionary.add(fullName, phoneNumber);
		}
	}
	public  String getPhoneNumber(Name name){
		return dictionary.getValue(name);
	} 

	public void removePhoneNumber (Name name) {
		dictionary.remove (name);
	}

	public void removePhoneNumber (String fullName) {
		Name fname = new Name (fullName);
		dictionary.remove(fname);
	}

	public static void main(String[] args) {

		try {
			TelephoneDirectory td = new TelephoneDirectory();	
			File cinemas = new File ("C:\\Users\\hillg2\\eclipse-workspace\\DataStructures\\src\\Lab6\\movies.txt");

			Scanner fileSc = new Scanner (cinemas);

			td.readFile (fileSc);

			td.display();
			System.out.println("\n");
			String theaterName = "West Newton Cinema";
			Name n = new Name(theaterName);

			System.out.println(theaterName + ": " + td.getPhoneNumber (n));
			System.out.println("\n");

			td.removePhoneNumber ("Capitol Theatre");

			theaterName = "Museum Of Science";
			n = new Name(theaterName);

			System.out.println(theaterName + ": " + td.getPhoneNumber (n));

			td.removePhoneNumber (theaterName);
			System.out.println("\n");

			td.display();
			System.out.println("\n");

			String phoneNumber;
			Scanner input = new Scanner (System.in);
			do {
				System.out.println ("What name are you looking for to get phone number? ");
				System.out.println (" Type quit if none");
				String searchName = input.nextLine();
				if (searchName.equals("quit"))
					break;
				n = new Name(searchName);
				phoneNumber = td.getPhoneNumber (n);
				if (phoneNumber == null)
					System.out.println ("No such theater");
				else
					System.out.println (phoneNumber);
			} while (true);
			input.close();
		}
		catch (FileNotFoundException ex) {
			System.out.println ("File movies.txt not found in the " +
					"project directory");
		}
	}



}
