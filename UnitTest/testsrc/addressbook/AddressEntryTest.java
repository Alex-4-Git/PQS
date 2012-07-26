package addressbook;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;


public class AddressEntryTest {
	private AddressEntry addressEntry;
	private AddressEntry mockAddressEntry;
	
	@Before
	public void setUp() {
		addressEntry = new AddressEntry();
		mockAddressEntry = new AddressEntry();
	}
	
	/**
	 * Test SetName function.
	 * NullPointerException should be thrown out if the firstname field is null.
	 * Because removeEntry method in AddressBook class executes by getName.
	 */
	@Test (expected=NullPointerException.class)
	public void testSetName(){
		addressEntry.setName(null, "John", "R");
	}
	
	/**
	 * test setter and getter of PhoneNumber.
	 */
	@Test
	public void testSetAndGetPhoneNumber() {
		addressEntry.setPhoneNumber(null);
		assertNull(addressEntry.getPhoneNumber());
		
		addressEntry.setPhoneNumber("312314");
		String expected = "312314";
		String actual = addressEntry.getPhoneNumber();
		assertEquals(expected, actual);
		
	}

	/**
	 * test setter and getter of Email.
	 */
	@Test
	public void testSetAndGetEmail() {
		addressEntry.setEmailAddress(null);
		assertNull(addressEntry.getEmailAddress());

		addressEntry.setEmailAddress("john@me.com");
		String expected = "john@me.com";
		String actual = addressEntry.getEmailAddress();
		assertEquals(expected, actual);
	}
	
	/**
	 * test setter and getter of PostAddress.
	 * Input fields are all null.
	 */
	@Test
	public void testSetAndGetNullPostAddress() {
		addressEntry.setPostalAddress(null, null, null, null, null);
		assertNotNull(addressEntry.getPostAddress());
	}

	/**
	 * test setter and getter of PostAddress.
	 * Input fields are all not null.
	 */
	@Test
	public void testSetAndGetPostAddress() {
		addressEntry.setPostalAddress("18", "NYC", "NY", "11220", "USA");
		PostalAddress actualAddress = addressEntry.getPostAddress();
		PostalAddress expectedAddress = new PostalAddress("18", "NYC", "NY", "11220", "USA");
		assertEquals(expectedAddress, actualAddress);
	}

	/**
	 * test setter and getter of Note.
	 */
	@Test
	public void testSetAndGetNote() {
		addressEntry.setNote(null);
		assertNull(addressEntry.getNote());
		
		addressEntry.setNote("Note Example");
		String expectedNote = "Note Example";
		String actualNote = addressEntry.getNote();
		assertEquals(expectedNote, actualNote);
	}
	
	/**
	 * Test equal() function with those False returns.
	 * Line 169 - 170 , 190 - 191 can not be covered by now.
	 */
	@Test
	public void testObjectEquals(){
		mockAddressEntry.setName("John", "Davis", null);
		mockAddressEntry.setPostalAddress(null, null, null, null, null);
		addressEntry.setName("John", "Davis", null);
		addressEntry.setPostalAddress(null, null, null, null, null);
		
		assertEquals(addressEntry,mockAddressEntry);
		assertEquals(addressEntry,addressEntry);
		assertFalse(addressEntry.equals(null));
		assertFalse(addressEntry.equals(new Integer(5)));
	}
	
	/**
	 * Test equals() function with email judgment parts.
	 */
	@Test
	public void testEmailEquals(){
		mockAddressEntry.setEmailAddress("john@me.com");
		assertFalse(addressEntry.equals(mockAddressEntry));
		
		addressEntry.setEmailAddress("back@me.com");
		assertFalse(addressEntry.equals(mockAddressEntry));
		
		addressEntry.setEmailAddress("john@me.com");
		assertTrue(addressEntry.equals(mockAddressEntry));
	}
	
	/**
	 * Test equals() function with name judgment parts.
	 */
	@Test
	public void testNameEquals(){
		mockAddressEntry.setName("John", null, null);
		assertFalse(addressEntry.equals(mockAddressEntry));	
		
	}
	
	/**
	 * Test equals() function with notes judgment parts.
	 */
	@Test
	public void testNoteEquals(){
		mockAddressEntry.setNote("Note");
		assertFalse(addressEntry.equals(mockAddressEntry));
		
		addressEntry.setNote("addressNote");
		assertFalse(addressEntry.equals(mockAddressEntry));
		
		addressEntry.setNote("Note");
		assertTrue(addressEntry.equals(mockAddressEntry));
	}
	
	/**
	 * Test equals() function with Phone number judgment parts.
	 */
	@Test
	public void testPhoneNoEquals(){
		mockAddressEntry.setPhoneNumber("3123123");
		assertFalse(addressEntry.equals(mockAddressEntry));
		
		addressEntry.setPhoneNumber("908401283");
		assertFalse(addressEntry.equals(mockAddressEntry));
		
		addressEntry.setPhoneNumber("3123123");
		assertTrue(addressEntry.equals(mockAddressEntry));
	}
	
	/**
	 * Test equals() function with Post address judgment parts.
	 */
	@Test
	public void testPostAddressEquals(){
		mockAddressEntry.setPostalAddress("1231", "NYC", "NY", "11220", "USA");
		assertFalse(addressEntry.equals(mockAddressEntry));	
	}
	
	/**
	 * Judge whether two AddressEntry Objects have the same hashCode.
	 * 
	 */
	@Test
	public void testHashCode() {
		addressEntry.setName("a", "b", "c");
		mockAddressEntry.setName("a", "b", "c");
		
		int expectedHashCode = addressEntry.hashCode();
		int actualHashCode = mockAddressEntry.hashCode();
		assertEquals(expectedHashCode, actualHashCode);	
	}
	
	private void testHashCodeWithEmailAddressJudgeHelper(AddressEntry addressEntry, String value){
		addressEntry.setEmailAddress(value);
		mockAddressEntry.setEmailAddress(value);
		int expectedHashCode = addressEntry.hashCode();
		int actualHashCode = mockAddressEntry.hashCode();
		
		assertEquals(expectedHashCode, actualHashCode);	
	}
	
	/**
	 * Judge whether two AddressEntry Objects have the same hashCode it the emailAddress field is null/not null.
	 * 
	 */
	@Test
	public void testHashCodeWithEmailAddressJudge() {
		testHashCodeWithEmailAddressJudgeHelper(addressEntry, null);
		testHashCodeWithEmailAddressJudgeHelper(addressEntry, "castor@gmail.com");		
	}
	
	private void testHashCodeWithNoteJudgeHelper(AddressEntry addressEntry, String value){
		addressEntry.setNote(value);
		mockAddressEntry.setNote(value);
		int expectedHashCode = addressEntry.hashCode();
		int actualHashCode = mockAddressEntry.hashCode();
		
		assertEquals(expectedHashCode, actualHashCode);	
	}
	
	/**
	 * Judge whether two AddressEntry Objects have the same hashCode it the Note field is null/not null.
	 * 
	 */
	@Test
	public void testHashCodeWithNoteJudge() {
		testHashCodeWithNoteJudgeHelper(addressEntry, null);
		testHashCodeWithNoteJudgeHelper(addressEntry, "Example Note.");		
	}
	
	private void testHashCodeWithPhoneJudgeHelper(AddressEntry addressEntry, String value){
		addressEntry.setPhoneNumber(value);
		mockAddressEntry.setPhoneNumber(value);
		int expectedHashCode = addressEntry.hashCode();
		int actualHashCode = mockAddressEntry.hashCode();
		
		assertEquals(expectedHashCode, actualHashCode);	
	}
	
	/**
	 * Judge whether two AddressEntry Objects have the same hashCode it the PhoneNumber field is null/not null.
	 * 
	 */
	@Test
	public void testHashCodeWithPhoneJudge() {
		testHashCodeWithPhoneJudgeHelper(addressEntry, null);
		testHashCodeWithPhoneJudgeHelper(addressEntry, "Example Note.");		
	}
	
	/**
	 * Test output toString function.
	 */
	@Test
	public void testToString(){
		addressEntry.setName("John", null, null);
		addressEntry.setEmailAddress("John@me.com");
		addressEntry.setNote(null);
		addressEntry.setPhoneNumber("1234");
		addressEntry.setPostalAddress("1110", "NYC", "NY", "11220", "USA");
		
		String expectedOutputString = "John;;null;;null###1110;;NYC;;NY;;11220;;USA###John@me.com###1234###null";
		String actualOutputString = addressEntry.toString();
		assertEquals(expectedOutputString, actualOutputString);
	}
	
	/**
	 * Test compartTo function.
	 */
	@Test
	public void testCompareTo(){
		mockAddressEntry.setName("John", "Davis", "J");
		
		addressEntry.setName("Asher", "Davis", "J");
		assertEquals(-1,addressEntry.compareTo(mockAddressEntry));
		
		addressEntry.setName("Zerphy", "Davis", "J");
		assertEquals(1,addressEntry.compareTo(mockAddressEntry));
		
		addressEntry.setName("John","Davis","J");
		
		addressEntry.setPostalAddress("1110", "NYC", "NY", "11220", "USA");
		mockAddressEntry.setPostalAddress("2220", "NYC", "NY", "11220", "USA");
		assertEquals(-1,addressEntry.compareTo(mockAddressEntry));
		
		addressEntry.setPostalAddress("9110", "NYC", "NY", "11220", "USA");
		assertEquals(1,addressEntry.compareTo(mockAddressEntry));
		
		addressEntry.setPostalAddress("2220", "NYC", "NY", "11220", "USA");

		addressEntry.setPhoneNumber("1234");
		mockAddressEntry.setPhoneNumber("2134");
		assertEquals(-1,addressEntry.compareTo(mockAddressEntry));
		
		addressEntry.setPhoneNumber("9999");
		assertEquals(1, addressEntry.compareTo(mockAddressEntry));
		
		addressEntry.setPhoneNumber("2134");
		
		addressEntry.setEmailAddress("aaa@gmail.com");
		mockAddressEntry.setEmailAddress("bbb@gmail.com");
		assertEquals(-1,addressEntry.compareTo(mockAddressEntry));
		
		addressEntry.setEmailAddress("zzz@gmail.com");
		assertEquals(1,addressEntry.compareTo(mockAddressEntry));
		
		addressEntry.setEmailAddress("bbb@gmail.com");
		
		addressEntry.setNote("A Note");
		mockAddressEntry.setNote("B Note");
		assertEquals(-1,addressEntry.compareTo(mockAddressEntry));
		
		addressEntry.setNote("Z Note");
		assertEquals(1,addressEntry.compareTo(mockAddressEntry));

		addressEntry.setNote("B Note");
		
		assertEquals(0,addressEntry.compareTo(mockAddressEntry));
	}
}
