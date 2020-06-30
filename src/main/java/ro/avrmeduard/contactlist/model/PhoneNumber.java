package ro.avrmeduard.contactlist.model;

public class PhoneNumber {

    private String countryCode; // ex: +40
    private String number; // ex: 740123456


    public PhoneNumber(String countryCode, String number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public PhoneNumber(String number) {
        this.countryCode = "+40";    // default country code
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return this.countryCode + "_" + this.number;
    }

}
