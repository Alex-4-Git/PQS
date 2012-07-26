package builder;

public class AddressBookEntryBuilder {

    private String name;
    private String note;
    private String phone;
    private String address;
    private String email;

    public AddressBookEntryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AddressBookEntryBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public AddressBookEntry build() {
        return new AddressBookEntry(name, address, phone, email, note);
    }
}
