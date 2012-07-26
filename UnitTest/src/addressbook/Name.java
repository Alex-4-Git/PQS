package addressbook;

/***
 * class for representing name and it cannot be extended
 * name is made up of First Name, Last Name and Middle Name
 *
 */
final class Name implements Comparable<Name>{
	 private String firstName;
	 private String lastName;
	 private String middleName;
	 
	 Name(){}
	 
	 Name(String firstName,String lastName,String middleName){
		 setFirstName(firstName);
		 setLastName(lastName);
		 setMiddleName(middleName);
	 }
	 
	 /***
	  * sets the value for the First Name of Name
	  * @param firstName
	  * @return the object of Name
	  */
	 Name setFirstName(String firstName){
		 this.firstName=firstName;
		 return this;
	 }
	 /***
	  * sets the value for Middle Name of Name
	  * @param middleName
	  * @return the object of Name
	  */
	 Name setMiddleName(String middleName){
		 this.middleName=middleName;
		 return this;
	 }
	 /**
	  * sets the Last Name for the Name
	  * @param lastName
	  * @return the object of Name
	  */
	 Name setLastName(String lastName){
		 this.lastName=lastName;
		 return this;
	 }
	/***
	 * Returns the First Name of Name
	 * @return firstName
	 */
	 
	String getFirstName(){
		return firstName;
	}
	/***
	 * Returns the Last Name of Name
	 * @return lastName
	 */
	String getLastName(){
		return lastName;
	}
	/***
	 * Returns the Middle Name of Name
	 * @return middleName
	 */
	String getMiddleName(){
		return middleName;
	}

	@Override
	public String toString() {
		return firstName+";;"+lastName+";;"+middleName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
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
		if (!(obj instanceof Name)) {
			return false;
		}
		Name other = (Name) obj;
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (middleName == null) {
			if (other.middleName != null) {
				return false;
			}
		} else if (!middleName.equals(other.middleName)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Name name) {
		// TODO Auto-generated method stub
		if(firstName.compareTo(name.firstName)<0)
			return -1;
		if(firstName.compareTo(name.firstName)>0)
			return 1;
		if(lastName.compareTo(name.lastName)<0)
			return -1;
		if(lastName.compareTo(name.lastName)>0)
			return 1;
		if(middleName.compareTo(name.middleName)<0)
			return -1;
		if(middleName.compareTo(name.middleName)>0)
			return 1;
		return 0;
			
			
		
	}

	

}
