package addressbook;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.*;


/**
 * class that represents the addressBook.
 * It is a final class and cannot be extended
 * It uses a Hashtable of ArrayList to store the address entries .
 * The address entries are stored in alphabetical order of the first character of the first name
 * The methods defined in this class allows the user to create an empty address book,add entries to the address book,
 * remove entries from the address book and search for a specific entry in the address book.
 * To make the address book persistent, there is a method saveAddressBook that lets to save the address Book to a file. 
 * The address Book can be regenerated from file using readAddressBook method
 * 
 *
 *
 */
public final class AddressBook  {
	private Hashtable<Character,ArrayList<AddressEntry>> addressBook;
	
	public AddressBook(){
		 addressBook = new Hashtable<Character,ArrayList<AddressEntry>>();
	}
	
	/***
	 * static factory method that returns an AddressEntry object 
	 * @return AddressEntry object
	 */
	public static AddressEntry newAddressEntry(){
		return new AddressEntry();
		
	}
	/***
	 * static factory method that returns an AddressBook object
	 * @return AddressBook object
	 */
	public static AddressBook createNewAddressBook(){
		return new AddressBook();
	}
	/***
	 * This method allows to add an address entry to the address book.
	 * It takes an AddressEntry to be added.
	 * It adds the address entry in its alphabetical section ArrayList based on the first character of firstName
	 * @param addressEntry
	 */
	
	public void addEntry(AddressEntry addressEntry){
		
		ArrayList<AddressEntry> alphaEntry;
		char alpha = addressEntry.getName().getFirstName().charAt(0); // Gets the first character of the first name
		Character.toUpperCase(alpha);
		if(addressBook.containsKey(alpha)){  // Checks if the array list of the character is there in the address book
			alphaEntry = addressBook.get(alpha);
			alphaEntry.add(addressEntry);	
		}
		else{ // if the arraylist of the alphabat is not present,create a new
			alphaEntry = new ArrayList<AddressEntry>();
			alphaEntry.add(addressEntry);
			addressBook.put(alpha,alphaEntry);
		}
		
}
	/***
	 * This method allows to remove an address entry from the address book.
	 * It takes an AddressEntry as argument
	 * It returns true if the entry has been removed successfully.If the entry to be removed does not exist it returns false
	 * @param addressEntry
	 * @return true if entry exist and removed successfully,else return false
	 */
	
	public boolean removeEntry(AddressEntry addressEntry){
		ArrayList<AddressEntry> alphaEntry;
		char alpha = addressEntry.getName().getFirstName().charAt(0); // Get the ArrayList from the Hashtable addressbook in which the entry be present
		Character.toUpperCase(alpha);
		alphaEntry=addressBook.get(alpha);
		if(alphaEntry!=null){
			for(AddressEntry entry : alphaEntry){ // Check if any entry in the arraylist matches the entry to be removed 
				if((addressEntry).equals(entry)){
					alphaEntry.remove(alphaEntry.indexOf(entry));
					return true;
				}
				
			}
		}
		return false;
		
	}
	/***
	 * This method allows to save the address book to a filr
	 * It takes the path to the file as the argument
	 * The file has it own format
	 * Each address entry in the address book is in a new line in the file
	 * Each field in the address entry are seperated by token ###
	 * Each subfield in the postalAddress and name of the address entry are seperated by token ;;
	 * @param filePath
	 * @throws IOException
	 */
	
	public void saveAddressBook(String filePath)throws IOException{
		
			PrintWriter out = new PrintWriter(new FileWriter(filePath)); 

			for(ArrayList<AddressEntry> x:addressBook.values()){ // Traverse each entry of the address book and write it to a file
				for(AddressEntry y: x){
					String temp= y.toString();
					out.println(temp);
				}
			}
			out.close();
	
	
	}
	/***
	 * Reads and constructs the address book from the file
	 * It takes the filepath as an argument and returns the addressbook.
	 * To read the file it uses the pre-defined file format
	 * @param filePath
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	
	public void readAddressBook(String filePath)throws IOException, FileNotFoundException{
		
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			while(in.ready()){
				String text = in.readLine();
				String fields[]=text.split("###"); // Get each field of the address Entry
				String postAddress[]=fields[1].split(";;");// Get the subfields of the postalAddress in Entry
				String fieldName[]=fields[0].split(";;");// Get the subfields of the name in the Entry
				AddressEntry entry = AddressBook.newAddressEntry();
				// create a new entry,construct its field values and add it to the address book
				entry.setName(fieldName[0],fieldName[1],fieldName[2]).setPostalAddress(postAddress[0],postAddress[1],postAddress[2],postAddress[3],postAddress[4]).setPhoneNumber(fields[2]).setEmailAddress(fields[3]).setNote(fields[4]);
				addEntry(entry);// add the entry to the address book
			}
			in.close(); 
}
	/***
	 * This method searches for the information in the address book.
	 * It is a generic search and looks for the information in any field of the address entry
	 * The takes the information as the argument and returns the list of entries that matches the information
	 * @param searchInfo
	 * @return list of result entries
	 */

	public ArrayList<AddressEntry> searchEntry(String searchInfo){
		ArrayList<AddressEntry> result= new ArrayList<AddressEntry>();
		for(ArrayList<AddressEntry> x:addressBook.values()){
			for(AddressEntry y: x){
				String entryTemp = y.toString();
				if(entryTemp.contains(searchInfo)){
						result.add(y);
				}
			}
		}
		return result;
}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressBook == null) ? 0 : addressBook.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AddressBook)) {
			return false;
		}
		AddressBook other = (AddressBook) obj;
		if (addressBook == null) {
			if (other.addressBook != null) {
				return false;
			}
		} else if (!addressBook.equals(other.addressBook)) {
			return false;
		}
		return true;
	}
	
}