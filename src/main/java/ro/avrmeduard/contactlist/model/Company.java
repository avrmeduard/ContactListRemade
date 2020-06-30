package ro.avrmeduard.contactlist.model;

public class Company{

    private String name;

    private Address address;

    public Company(String name) {
        this.name = name;
    }

    public Company(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        if (name == null) {
            return null;
        }
        return name;
    }

    public Address getAddress() {
        if (address == null) {
            return null;
        }
        return address;
    }

    @Override
    public String toString() {
        return this.name + "_" + this.address.toString();
    }
}
