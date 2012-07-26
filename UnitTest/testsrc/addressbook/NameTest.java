package addressbook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NameTest {
	private Name name;
	private Name mockName;
	
	@Before
	public void setUp() {
		name = new Name();
		mockName = new Name();
	}

	/**
	 * NullPointerException should be thrown out if first name field is null.
	 * Because removeEntry method in AddressBook class executes by getName.
	 */
	@Test (expected=NullPointerException.class)
	public void testConstructorNullValue() {
		new Name(null,"Davis","J");
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(new Name("John","Davis","Middle"));
	}
	
	@Test
	public void testObjectEquals() {
		mockName = new Name("John","Davis","R.");
		name = new Name("John","Davis","R.");
		
		assertTrue(name.equals(mockName));
		assertTrue(name.equals(name));
		assertFalse(name.equals(null));
		assertFalse(name.equals(new Integer(5)));
	}
	
	@Test
	public void testFirstnameEquals() {
		mockName=new Name("John",null,null);
		assertFalse(name.equals(mockName));
		
		name.setFirstName("Dan");
		assertFalse(name.equals(mockName));
	}
	
	@Test
	public void testLastnameEquals() {
		mockName=new Name(null,"Davis",null);
		assertFalse(name.equals(mockName));
		
		name.setLastName("Dan");
		assertFalse(name.equals(mockName));
	}
	
	@Test
	public void testMiddlenameEquals() {
		mockName=new Name(null,null,"R.");
		assertFalse(name.equals(mockName));
		
		name.setMiddleName("Dan");
		assertFalse(name.equals(mockName));
	}
	
	@Test
	public void testSetAndGetFirstname() {	
		name.setFirstName("John");
		assertEquals("John",name.getFirstName());
	}
	
	@Test
	public void testSetAndGetLastname() {
		name.setLastName(null);
		assertEquals(null,name.getLastName());
		
		name.setLastName("Davis");
		assertEquals("Davis",name.getLastName());
	}
	
	@Test
	public void testSetAndGetMiddlename() {
		name.setMiddleName(null);
		assertEquals(null,name.getMiddleName());
		
		name.setMiddleName("R.");
		assertEquals("R.",name.getMiddleName());
	}
	
	/**
	 * Tested Null, Equal and Not equal situations haseCode.
	 */
	@Test
	public void testhashCode() {
		int expectedHashCode = mockName.hashCode();
		int actualHashCode = name.hashCode();
		assertEquals(expectedHashCode,actualHashCode);
		
		mockName.setFirstName("Lee");
		expectedHashCode = mockName.hashCode();
		assertFalse(expectedHashCode==actualHashCode);

		name.setFirstName("Lee");
		actualHashCode = name.hashCode();
		assertEquals(expectedHashCode, actualHashCode);
	}
	
	@Test
	public void testToString(){
		name.setFirstName("John");
		name.setLastName("Davis");
		name.setMiddleName("R.");	
		
		String expectedOutputString = "John;;Davis;;R.";
		String actualOutputString = name.toString();
		assertEquals(expectedOutputString, actualOutputString);
	}
	
	@Test
	public void testCompareTo(){
		mockName.setFirstName("Asher");
		name.setFirstName("Baker");
		assertEquals(1,name.compareTo(mockName));
		
		mockName.setFirstName("Zsher");
		assertEquals(-1,name.compareTo(mockName));
		
		name.setFirstName("Zsher");
		name.setLastName("Asyle");
		mockName.setLastName("Bob");
		assertEquals(-1,name.compareTo(mockName));
		
		name.setLastName("Castor");
		assertEquals(1,name.compareTo(mockName));
		
		name.setLastName("Bob");
		name.setMiddleName("A");
		mockName.setMiddleName("Jr");
		assertEquals(-1,name.compareTo(mockName));
		
		name.setMiddleName("Mr");
		assertEquals(1,name.compareTo(mockName));

		name.setMiddleName("Jr");
		assertEquals(0,name.compareTo(mockName));
	}
}
