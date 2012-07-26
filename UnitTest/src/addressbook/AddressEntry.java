package addressbook;

/**
 * class to represent the address entry
 * The class in final and cannot be extended
 * The address entry consists of name, postal address,phone number,email address and a note
 *
 */
public final class AddressEntry implements Comparable<AddressEntry> {
	private  Name name;
	private PostalAddress postalAddress;
	private String phoneNumber;
	private String emailAddress;
	private String note;
	
	
	
	AddressEntry(){
		name=new Name();
		postalAddress=new PostalAddress();
	}
	
	/***
	 * 
	 * @param firstName
	 * @param lastName
	 * @param middleName
	 * @return AddressEntry object
	 */
	
	
	public AddressEntry setName(String firstName,String lastName,String middleName){
		this.name.setFirstName(firstName);
		this.name.setLastName(lastName);
		this.name.setMiddleName(middleName);
		return this;
		
	}
	/***
	 * sets the street,city,state,zipcode and country of the postal address
	 * The arguments can be empty
	 * @param street
	 * @param city
	 * @param state
	 * @param zipcode
	 * @param country
	 * @return AddressEntry object
	 */
	public AddressEntry setPostalAddress(String street,String city,String state,String zipcode,String country){
		postalAddress=new PostalAddress(street,city,state,zipcode,country);
		return this;
	}
	/***
	 * sets the value for the phone number of the address entry
	 * @param phoneNumber
	 * @return AddressEntry object
	 */
	public AddressEntry setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
		return this;
	}
	/***
	 * sets the value for the email address of the address entry
	 * @param emailAddress
	 * @return AddressEntry object
	 */
	public AddressEntry setEmailAddress(String emailAddress){
		this.emailAddress=emailAddress;
		return this;
	}
	/***
	 * sets the value for the note of the address entry
	 * @param note
	 * @return AddressEntry object
	 */
	public AddressEntry setNote(String note){
		this.note=note;
		return this;
	}
	/***
	 * returns the name of the address entry
	 * @return name
	 */
	public Name getName(){
		return name;
	}
	/***
	 * returns the postal address of the address entry
	 * @return postalAddress
	 */
	public PostalAddress getPostAddress(){
		return postalAddress;
	}
	/***
	 * return the phone number of the address entry
	 * @return phoneNumber
	 */
	public String getPhoneNumber(){
		return phoneNumber;
	}
	/***
	 * return the email address of the address entry
	 * @return email address
	 */
	public String getEmailAddress(){
		return emailAddress;
	}
	/***
	 * return the note of the address entry
	 * @return note
	 */
	public String getNote(){
		return note;
	}
	
	public String toString(){
		return getName().toString()+"###"+getPostAddress().toString()+"###"+getEmailAddress()+"###"+getPhoneNumber()+"###"+getNote();
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result
				+ ((postalAddress == null) ? 0 : postalAddress.hashCode());
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
		if (!(obj instanceof AddressEntry)) {
			return false;
		}
		AddressEntry other = (AddressEntry) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null) {
				return false;
			}
		} else if (!emailAddress.equals(other.emailAddress)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (note == null) {
			if (other.note != null) {
				return false;
			}
		} else if (!note.equals(other.note)) {
			return false;
		}
		if (phoneNumber == null) {
			if (other.phoneNumber != null) {
				return false;
			}
		} else if (!phoneNumber.equals(other.phoneNumber)) {
			return false;
		}
		if (postalAddress == null) {
			if (other.postalAddress != null) {
				return false;
			}
		} else if (!postalAddress.equals(other.postalAddress)) {
			return false;
		}
		return true;
	}



	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AddressEntry entry) {
		// TODO Auto-generated method stub
		if(name.compareTo(entry.name)<0)
			return -1;
		if(name.compareTo(entry.name)>0)
			return 1;
		if(postalAddress.compareTo(entry.postalAddress)<0)
			return -1;
		if(postalAddress.compareTo(entry.postalAddress)>0)
			return 1;
		if(phoneNumber.compareTo(entry.phoneNumber)<0)
			return -1;
		if(phoneNumber.compareTo(entry.phoneNumber)>0)
			return 1;
		if(emailAddress.compareTo(entry.emailAddress)<0)
			return -1;
		if(emailAddress.compareTo(entry.emailAddress)>0)
			return 1;
		if(note.compareTo(entry.note)<0)
			return -1;
		if(note.compareTo(entry.note)>0)
			return 1;
		return 0;
	}
}



	