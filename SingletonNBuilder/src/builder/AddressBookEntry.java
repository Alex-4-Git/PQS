package builder;

public class AddressBookEntry {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String note;

    public AddressBookEntry() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNote() {
        return note;
    }

    AddressBookEntry(String name, String address, String phone, String email,
            String note) {
        super();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.note = note;
    }

    public static class Builder {

        private String name;
        private String note;
        private String phone;
        private String address;
        private String email;

        public Builder() {
        }

        public Builder fromEntry(AddressBookEntry oldEntry) {
            this.name = oldEntry.getName();
            this.note = oldEntry.getNote();
            return this;
        }

        public Builder(AddressBookEntry oldEntry) {
            this.name = oldEntry.getName();
            this.note = oldEntry.getNote();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withNote(String note) {
            this.note = note;
            return this;
        }

        public AddressBookEntry build() {
            return new AddressBookEntry(name, address, phone, email, note);
        }
    }

}