package addressbook;

/**
 * class for representing the postal address as a composite value made up of street,city,state,zipcode and country
 * The class is final and cannot be extended
 *
 */
final class PostalAddress implements Comparable<PostalAddress> {
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	PostalAddress(){}
	
	PostalAddress(String street,String city,String state,String zipcode,String country){
		setStreet(street);
		setCity(city);
		setState(state);
		setZipcode(zipcode);
		setCountry(country);
		
	}
	/***
	 * sets the value for street of the  address
	 * @param street
	 * @return object of PostalAddress
	 */
	
	PostalAddress setStreet(String street){
		this.street=street;
		return this;
	}
	/***
	 * sets the value for city of the address
	 * @param city
	 * @return object of PostalAddress
	 */
	PostalAddress setCity(String city){
		this.city=city;
		return this;
	}
	/***
	 * sets the value for the state of the address
	 * @param state
	 * @return object of PostalAddress
	 */
	PostalAddress setState(String state){
		this.state=state;
		return this;
	}
	/***
	 * sets the value for the zipcode of the address
	 * @param zipcode
	 * @return object of PostalAddress
	 */
	PostalAddress setZipcode(String zipcode){
		this.zipcode=zipcode;
		return this;
	}
	/***
	 * sets the value for the country of the address
	 * @param country
	 * @return object of PostalAddress
	 */
	PostalAddress setCountry(String country){
		this.country=country;
		return this;
	}
	/***
	 * return the street of the address
	 * @return street
	 */
	String getStreet(){
		return street;
	}
	/***
	 * return the city of the address
	 * @return city
	 */
	String getCity(){
		return city;
	}
	/***
	 * return the state of the address
	 * @return state
	 */
	String getState(){
		return state;
	}
	/***
	 * return the zipcode of the address
	 * @return zipcode
	 */
	String getZipcode(){
		return zipcode;
	}
	/***
	 * return the country of the address
	 * @return country
	 */
	String getCountry(){
		return country;
	}
	
	@Override
	public String toString(){
		return street+";;"+city+";;"+state+";;"+zipcode+";;"+country;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
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
		if (!(obj instanceof PostalAddress)) {
			return false;
		}
		PostalAddress other = (PostalAddress) obj;
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		if (zipcode == null) {
			if (other.zipcode != null) {
				return false;
			}
		} else if (!zipcode.equals(other.zipcode)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PostalAddress address) {
		// TODO Auto-generated method stub
		if(street.compareTo(address.street)<0)
			return -1;
		if(street.compareTo(address.street)>0)
			return 1;
		if(city.compareTo(address.city)<0)
			return -1;
		if(city.compareTo(address.city)>0)
			return 1;
		if(state.compareTo(address.state)<0)
			return -1;
		if(state.compareTo(address.state)>0)
			return 1;
		if(zipcode.compareTo(address.zipcode)<0)
			return -1;
		if(zipcode.compareTo(address.zipcode)>0)
			return 1;
		if(country.compareTo(address.country)<0)
			return -1;
		if(country.compareTo(address.country)>0)
			return 1;
		return 0;
	}
	

}
