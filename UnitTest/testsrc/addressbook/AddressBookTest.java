package addressbook;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class AddressBookTest {
    private AddressBook addressBook;
    private AddressEntry addressEntry;
    private AddressEntry mockEntry;

    @Before
    public void setUp() {
        addressBook = AddressBook.createNewAddressBook();
        addressEntry = AddressBook.newAddressEntry();
        addressEntry.setName("Howard", "Tink", null);
        mockEntry = AddressBook.newAddressEntry();
    }

    /**
     * Test whether add same entry multiple times are accessible.
     */
    @Test
    public void testaddEntry() {
        addressBook.addEntry(addressEntry);
        addressBook.addEntry(addressEntry);
        int expectedEntrySize = addressBook.searchEntry("Howard").size();

        assertEquals(2, expectedEntrySize);
    }

    /**
     * Test remove null entry, expected a NullPointer Exception.
     */
    @Test(expected = NullPointerException.class)
    public void testremoveNullEntry() {
        addressBook.removeEntry(null);
    }

    /**
     * Test remove an entry existing.
     */
    @Test
    public void testRemoveEntrySuccess() {
        addressBook.addEntry(addressEntry);
        assertTrue(addressBook.removeEntry(addressEntry));

        int expectedEntrySize = addressBook.searchEntry("Howard").size();
        assertEquals(0, expectedEntrySize);
    }

    /**
     * Test remove an entry not exist.
     */
    @Test
    public void testRemoveEntryFailed() {
        addressBook.addEntry(addressEntry);
        mockEntry.setName("Jas", null, null);
        assertFalse(addressBook.removeEntry(mockEntry));

        AddressEntry expectedEntry = addressEntry;
        AddressEntry actualEntry = addressBook.searchEntry("Howard").get(0);
        assertEquals(expectedEntry, actualEntry);
    }

    /**
     * Test remove an entry with null name field. Expected NullPointer
     * Exception.
     */
    @Test(expected = NullPointerException.class)
    public void testremoveEntryNameFieldNull() {
        addressEntry.setName(null, null, null);
        addressBook.removeEntry(addressEntry);
    }

    /**
     * Test save addressbook to a null path.
     * 
     * @throws IOException if path does not exist.
     */
    @Test(expected = NullPointerException.class)
    public void testSaveAddressBooktoNull() throws IOException {
        addressBook.saveAddressBook(null);
    }

    /**
     * Test save addressbook to a actual path. The file will be set up and
     * delete in fixtures Folder.
     * 
     * @throws IOException if the file cannot be saved
     */
    @Test
    public void testSaveAddressBook() throws IOException {
        addressBook.addEntry(addressEntry);
        String workFolder = new File("").getAbsolutePath();
        addressBook.saveAddressBook(workFolder
                + "/testsrc/fixtures/saveTest.txt");
        File file = new File(workFolder + "/testsrc/fixtures/saveTest.txt");

        assertTrue(file.exists());
        file.delete();
    }

    /**
     * Test Read addressbook from a well-formatted file. The file has been
     * pre-setup in fixtures Folder.
     * 
     * @throws IOException if the file cannot be opened.
     */
    @Test
    public void testReadAddressBookSuccess() throws IOException {
        String workFolder = new File("").getAbsolutePath();
        addressBook.readAddressBook(workFolder
                + "/testsrc/fixtures/readTest.txt");

        assertNotNull(addressBook.searchEntry("Howard"));
    }

    /**
     * Test Read addressbook from a corrupt file. The file has been pre-setup in
     * fixtures Folder.
     * 
     * @throws IOException if the file cannot be opened.
     */
    @Test
    public void testReadAddressBookFail() throws IOException {
        String workFolder = new File("").getAbsolutePath();
        try{
        addressBook.readAddressBook(workFolder
                + "/testsrc/fixtures/readTest_Corrupt.txt");
        }catch (Exception e){
            
        }
        assertNull(addressBook.searchEntry("Howard"));
    }

    /**
     * Test search function with null input value. Expected NullPointer
     * Exception.
     */
    @Test(expected = NullPointerException.class)
    public void testSearchEntryNull() {
        addressBook.addEntry(addressEntry);
        addressBook.searchEntry(null);
    }

    /**
     * Test search with both find and not find cases.
     */
    @Test
    public void testSearchEntry() {
        addressBook.addEntry(addressEntry);

        AddressEntry expectedEntry = addressEntry;
        ArrayList<AddressEntry> actualEntry = addressBook.searchEntry("Tink");
        assertEquals(expectedEntry, actualEntry.get(0));

        expectedEntry.setName("Ark", "Jam", null);
        actualEntry = addressBook.searchEntry("Tink");
        assertEquals(0, actualEntry.size());
    }

    /**
     * Test equals() function with those of False output cases.
     */
    @Test
    public void testNotEquals() {
        addressBook.addEntry(addressEntry);
        mockEntry.setName("Jas", null, null);
        AddressBook mockAddressBook = new AddressBook();
        mockAddressBook.addEntry(mockEntry);

        assertFalse(addressBook.equals(mockAddressBook));
        assertFalse(addressBook.equals(null));
        assertFalse(addressBook.equals(new Integer(5)));
    }

    /**
     * Test equal() function with those of True output cases. Line 191 - 193 can
     * not be covered by now.
     */
    @Test
    public void testEquals() {
        addressBook.addEntry(addressEntry);
        mockEntry.setName("Howard", "Tink", null);
        AddressBook mockAddressBook = new AddressBook();
        mockAddressBook.addEntry(mockEntry);

        assertTrue(addressBook.equals(addressBook));
        assertTrue(addressBook.equals(mockAddressBook));
    }

    /**
     * Judge whether two AddressBook Objects have the same hashCode. Line 172
     * can not be covered by now.
     */
    @Test
    public void testHashCode() {
        addressBook.addEntry(addressEntry);
        AddressBook mockaddressBook = new AddressBook();
        mockaddressBook.addEntry(addressEntry);

        int expectedHashCode = mockaddressBook.hashCode();
        int actualHashCode = addressBook.hashCode();
        assertEquals(expectedHashCode, actualHashCode);

        mockaddressBook.removeEntry(addressEntry);
        actualHashCode = mockaddressBook.hashCode();
        boolean isEqual = (expectedHashCode == actualHashCode);
        assertFalse(isEqual);
    }

}
