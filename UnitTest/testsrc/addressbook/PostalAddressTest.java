package addressbook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PostalAddressTest {
	private PostalAddress address;
	private PostalAddress mockPostalAddress;
	
	@Before
	public void setUp() throws Exception {
		address=new PostalAddress();
		mockPostalAddress=new PostalAddress();
	}

	@Test
	public void testConstructor() {
		assertNotNull(new PostalAddress(null,null,null,null,null));
		assertNotNull(new PostalAddress("1111", "NYC", "NY", "11220", "USA"));
	}
	
	@Test
	public void testDefaultEquals() {
		mockPostalAddress = new PostalAddress("1111","NYC","NY", "11220","USA");		
		address = new PostalAddress("1111","NYC","NY", "11220","USA");

		assertTrue(address.equals(mockPostalAddress));
		assertTrue(address.equals(address));
		assertFalse(address.equals(null));
		assertFalse(address.equals(new Integer(5)));
	}
	
	@Test
	public void testStreetEquals() {
		mockPostalAddress = new PostalAddress("1111",null,null,null,null);
		assertFalse(address.equals(mockPostalAddress));
		
		address.setStreet("fads");
		assertFalse(address.equals(mockPostalAddress));
		
	}
	
	@Test
	public void testCityEquals() {
		mockPostalAddress=new PostalAddress(null, "NYC", null, null, null);
		assertFalse(address.equals(mockPostalAddress));
		
		address.setCity("Boston");
		assertFalse(address.equals(mockPostalAddress));
	}
	
	@Test
	public void testCountryEquals() {
		mockPostalAddress=new PostalAddress(null, null, null, null, "USA");
		assertFalse(address.equals(mockPostalAddress));
		
		address.setCountry("China");
		assertFalse(address.equals(mockPostalAddress));
	}
	
	@Test
	public void testStateEquals() {
		mockPostalAddress=new PostalAddress(null, null, "NY", null, null);
		assertFalse(address.equals(mockPostalAddress));
		
		address.setState("CA");
		assertFalse(address.equals(mockPostalAddress));
	}
	
	@Test
	public void testZipcodeEquals() {
		mockPostalAddress=new PostalAddress(null, null, null, "11220", null);
		assertFalse(address.equals(mockPostalAddress));
		
		address.setZipcode("31232");
		assertFalse(address.equals(mockPostalAddress));
	}
	
	@Test
	public void testSetAndGetStreet() {
		address.setStreet(null);
		assertEquals(null,address.getStreet());
		
		address.setStreet("fads");
		String expectedStreet = "fads";
		String actualStreet = address.getStreet();
		assertEquals(expectedStreet, actualStreet);
	}
	
	@Test
	public void testSetAndGetCity() {
		address.setCity(null);
		assertEquals(null,address.getCity());
		
		address.setCity("NYC");
		String expectedCity = "NYC";
		String actualCity = address.getCity();
		assertEquals(expectedCity, actualCity);
	}
	
	@Test
	public void testSetAndGetState() {
		address.setState(null);
		assertEquals(null,address.getState());

		address.setState("NY");
		String expectedState = "NY";
		String actualState = address.getState();
		assertEquals(expectedState, actualState);
	}
	
	@Test
	public void testSetAndGetZipcode() {
		address.setZipcode(null);
		assertEquals(null,address.getZipcode());
		
		address.setZipcode("11220");
		String expectedZipcode = "11220";
		String actualZipcode = address.getZipcode();
		assertEquals(expectedZipcode, actualZipcode);
	}
	
	@Test
	public void testSetAndGetCountry() {
		address.setCountry(null);
		assertEquals(null,address.getCountry());
		
		address.setCountry("USA");
		String expectedCountry = "USA";
		String actualCountry = address.getCountry();
		assertEquals(expectedCountry, actualCountry);
	}
	
	@Test
	public void testHashCode() {
		int expectedHashCode = mockPostalAddress.hashCode();
		int actualHashCode = address.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}
	
	private void testHashCodeWithCityJudgeHelper(PostalAddress address, String setValue) {
		address.setCity(setValue);
		mockPostalAddress.setCity(setValue);
		int expectedHashCode = mockPostalAddress.hashCode();
		int actualHashCode = address.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}
	
	@Test
	public void testHashCodeWithCityJudge() {
		testHashCodeWithCityJudgeHelper(address, null);
		testHashCodeWithCityJudgeHelper(address, "NYC");	
	}
	
	private void testHashCodeWithCountryJudgeHelper(PostalAddress address, String setValue) {
		address.setCountry(setValue);
		mockPostalAddress.setCountry(setValue);
		int expectedHashCode = mockPostalAddress.hashCode();
		int actualHashCode = address.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}
	
	@Test
	public void testHashCodeWithCountryJudge() {
		testHashCodeWithCountryJudgeHelper(address, null);
		testHashCodeWithCountryJudgeHelper(address, "USA");
	}
	
	private void testHashCodeWithStateJudgeHelper(PostalAddress address, String setValue) {
		address.setState(setValue);
		mockPostalAddress.setState(setValue);
		int expectedHashCode = mockPostalAddress.hashCode();
		int actualHashCode = address.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}
	
	@Test
	public void testHashCodeWithStateJudge() {
		testHashCodeWithStateJudgeHelper(address, null);
		testHashCodeWithStateJudgeHelper(address, "NY");
	}
	
	private void testHashCodeWithStreetJudgeHelper(PostalAddress address, String setValue) {
		address.setStreet(setValue);
		mockPostalAddress.setStreet(setValue);
		int expectedHashCode = mockPostalAddress.hashCode();
		int actualHashCode = address.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}
	
	@Test
	public void testHashCodeWithStreetJudge() {
		testHashCodeWithStreetJudgeHelper(address, null);
		testHashCodeWithStreetJudgeHelper(address, "1111 57st");
	}
	
	private void testHashCodeWithZipcodeJudgeHelper(PostalAddress address, String setValue) {
		address.setZipcode(setValue);
		mockPostalAddress.setZipcode(setValue);
		int expectedHashCode = mockPostalAddress.hashCode();
		int actualHashCode = address.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}
	
	@Test
	public void testHashCodeWithZipcodeJudge() {
		testHashCodeWithZipcodeJudgeHelper(address, null);
		testHashCodeWithZipcodeJudgeHelper(address, "11220");
	}
	
	@Test
	public void testToString(){
		address.setStreet("1111");
		address.setCity("NYC");
		address.setState("NY");
		address.setZipcode("11220");
		address.setCountry("USA");
		String expectedOutputString = "1111;;NYC;;NY;;11220;;USA";
		String actualOutputString = address.toString();
		assertEquals(expectedOutputString, actualOutputString);
	}
	
	@Test
	public void testcompareTo(){
		mockPostalAddress=new PostalAddress("2000", null, null, null, null);
		address.setStreet("1000");
		assertEquals(-1,address.compareTo(mockPostalAddress));
		
		address.setStreet("9000");
		assertEquals(1, address.compareTo(mockPostalAddress));
		
		address.setStreet("2000");
		
		address.setCity("Boston");
		mockPostalAddress.setCity("NYC");
		assertEquals(-1,address.compareTo(mockPostalAddress));
		
		address.setCity("Tampa");
		assertEquals(1,address.compareTo(mockPostalAddress));
		
		address.setCity("NYC");
		
		address.setState("CA");
		mockPostalAddress.setState("NY");
		assertEquals(-1,address.compareTo(mockPostalAddress));
		
		address.setState("TX");		
		assertEquals(1,address.compareTo(mockPostalAddress));
		
		address.setState("NY");
		
		address.setZipcode("11219");
		mockPostalAddress.setZipcode("11220");
		assertEquals(-1,address.compareTo(mockPostalAddress));

		address.setZipcode("11221");
		assertEquals(1,address.compareTo(mockPostalAddress));
		
		address.setZipcode("11220");
		
		address.setCountry("China");
		mockPostalAddress.setCountry("USA");
		assertEquals(-1,address.compareTo(mockPostalAddress));

		address.setCountry("Zimbabwe");
		assertEquals(1,address.compareTo(mockPostalAddress));

		address.setCountry("USA");
		assertEquals(0,address.compareTo(mockPostalAddress));
	}
}
