package ro.avrmeduard.contactlist.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    private static final String DEFAULT_PHONE_NUMBER_GROUP = "home";

    private Integer userID;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String jobTitle;
    private boolean isFavorite;

    private Map <String, PhoneNumber> phoneNumbers;

    private Address address;

    private Company company;


    public User(Integer userID , String firstName , String lastName , String email , Integer age , Map <String, PhoneNumber> phoneNumbers , Address address , String jobTitle , Company company , boolean isFavorite) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
        this.jobTitle = jobTitle;
        this.company = company;
        this.isFavorite = isFavorite;
    }


    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public User(Integer userId , String firstName , String lastName , String email , Integer age , Map <String, PhoneNumber> phoneNumbers , Address address , String jobTitle , Company company) {
        this(userId , firstName , lastName , email , age , phoneNumbers , address , jobTitle , company , false);
    }

    public User(Integer userID , String firstName , String lastName , PhoneNumber phoneNumber , boolean isFavorite) {
        this(userID , firstName , lastName , null , null , new HashMap <>() , null , null , null , isFavorite);

        this.phoneNumbers.put(DEFAULT_PHONE_NUMBER_GROUP , phoneNumber); // phone number added to a default group
    }

    public User(Integer userID , String firstName , String lastName , PhoneNumber phoneNumber) { // simple constructor, but require a PhoneNumber object
        this(userID , firstName , lastName , phoneNumber , false);
    }

    public User(Integer userID , String firstName , String lastName , String phoneNumber) {   // simplest constructor, requiring only the minimal necessary information in literal form
        this(userID , firstName , lastName , new PhoneNumber(phoneNumber) , false);
    }

    public Integer getUserID() {
        return userID;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if (age < 0 || age > 105) {
            throw new IllegalArgumentException("Age doesn't fit, try again : ");
        }
        this.age = age;
    }

    public Map <String, PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Map <String, PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String getIsFavorite() {
        return isFavorite ? "Favorite" : "Not favorite";
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    @Override
    public String toString() {
        return "\nId : " + this.userID + "\nName : " + this.firstName + " " + this.lastName +
                "\nAge : " + this.age +
                "\nEmail : " + this.email +
                "\nPhoneNumbers : " + this.phoneNumbers.entrySet().toString().replaceAll("\\[|\\]" , "").replace("=" , " ").replace("_" , "-") +
                "\nAddress : " + this.address.toString().replace("_" , ", ") +
                "\nJobTitle : " + this.jobTitle +
                "\nCompany : " + this.company.getName() + " | Address : " + this.company.getAddress().toString().replaceAll("_" , ", ") +
                "\nFavorites : " + this.isFavorite;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isFavorite == user.isFavorite &&
                Objects.equals(userID , user.userID) &&
                Objects.equals(firstName , user.firstName) &&
                Objects.equals(lastName , user.lastName) &&
                Objects.equals(email , user.email) &&
                Objects.equals(age , user.age) &&
                Objects.equals(phoneNumbers , user.phoneNumbers) &&
                Objects.equals(address , user.address) &&
                Objects.equals(jobTitle , user.jobTitle) &&
                Objects.equals(company , user.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID , firstName , lastName , email , age , phoneNumbers , address , jobTitle , company , isFavorite);
    }

}
