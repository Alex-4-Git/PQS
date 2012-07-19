package im.castor.AddressBook;

/**
 * Record structure for the address book.
 * @author Maochen Guan
 *
 */
public class Record {	
	private String Name;
	private String PhoneNo;
	private String Address;
	private String Email;
	private String Note;
	
	/**
	 * Default empty constructor
	 */
	public Record(){
	}
	
	//overload the Record constructor to initialize the value
	/**
	 * Initialize the Record
	 * @param name
	 * @param phone
	 * @param address
	 * @param email
	 * @param note
	 */
	public Record(String name, String phone, String address, String email, String note){
		this.Name = name;
		this.PhoneNo = phone;
		this.Address=address;
		this.Email=email;
		this.Note=note;
	}
	
	/**
	 * @return Name of the record.
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return Phone number of the record.
	 */
	public String getPhoneNo() {
		return PhoneNo;
	}
	/**
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	/**
	 * @return Address of the record.
	 */
	public String getAddress() {
		return Address;
	}
	/**
	 * @param address
	 */
	public void setAddress(String address) {
		Address = address;
	}
	/**
	 * @return Email of the record.
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * @param email
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * @return Note of the record.
	 */
	public String getNote() {
		return Note;
	}
	/**
	 * @param note
	 */
	public void setNote(String note) {
		Note = note;
	}
	
	public boolean isValid(){
		return !this.Name.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public final String toString() {	//return the class object to a string format.
		  return "Name:" +this.getName() + "\tPhone:"+ this.getPhoneNo()+ "\tAddress:"+this.getAddress()+"\tEmail:"+this.getEmail()+"\tNote:"+this.getNote();
	}
}
