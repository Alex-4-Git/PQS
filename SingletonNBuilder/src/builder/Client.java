package builder;

public class Client {
    
    @SuppressWarnings("unused")
    public void foo() {

        AddressBookEntry entry = new AddressBookEntry.Builder().withName("Bob")
                .withNote("My note").build();

        AddressBookEntry entry2 = new AddressBookEntry.Builder().withNote(
                "My note").build();

        AddressBookEntry entry3 = new AddressBookEntryBuilder().withName("Bob")
                .withNote("My note").build();

        AddressBookEntry entry4 = new AddressBookEntry("Bob",
                null /* address */, null /* phone */, "my note", null);

    }
}