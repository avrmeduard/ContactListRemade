package ro.avrmeduard.contactlist.service;

import ro.avrmeduard.contactlist.model.Address;
import ro.avrmeduard.contactlist.model.Company;
import ro.avrmeduard.contactlist.model.PhoneNumber;
import ro.avrmeduard.contactlist.model.User;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DBUserService implements UserService{

    private List<User> contacts;

    private String url, timeZone, user, pass;
    Connection connection;


    public DBUserService() {

        Properties properties = new Properties();

        try(InputStream input = DBUserService.class.getResourceAsStream("/applicationProperties")) {
            properties.load(input);
            url = properties.getProperty("db.url");
            timeZone = properties.getProperty("db.timeZone");
            user = properties.getProperty("db.user");
            pass = properties.getProperty("db.password");

        } catch (IOException e) {
            System.out.println("Wrong input data connection \n" + e);
        }

        try {
            connection = DriverManager.getConnection(url+timeZone, user, pass);
        } catch (SQLException e) {
            System.out.println("Open connection failed \n" + e);
        }

        System.out.println("Connection succeed. \n");
    }



    @Override
    public List <User> getContacts() {
        contacts = new ArrayList <>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = null;
            try {
                String sqlGetContacts = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.age, \n" +
                                        "       ph.place, ph.country_code, ph.number,\n" +
                                        "       a.street_name, a.street_number, a.apartment_number, a.floor, a.zip_code, a.city, a.country,\n" +
                                        "       u.job_title, co.company_name, a_w.street_name, a_w.street_number, a_w.apartment_number, a_w.floor, a_w.zip_code, a_w.city, a_w.country,\n" +
                                        "       u.is_favorite\n" +
                                        "FROM users u, phonenumbers ph, addresses a, companies co, addresses a_w\n" +
                                        "WHERE u.user_id = ph.user_id\n" +
                                        "AND u.address_id = a.id\n" +
                                        "AND u.company_id = co.company_id\n" +
                                        "AND a_w.id = co.address_id;";


                resultSet = statement.executeQuery(sqlGetContacts);

                while (resultSet.next()) {

                    Integer user_id = resultSet.getInt("user_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    Integer age = resultSet.getInt("age");

                    HashMap<String, PhoneNumber> phoneNumberHashMap = new HashMap <>();
                    phoneNumberHashMap.put(resultSet.getString("place"), new PhoneNumber(
                                           resultSet.getString("country_code"),resultSet.getString("number")));

                    Address address = new Address(
                                 resultSet.getString("street_name"), resultSet.getInt("street_number"),
                                 resultSet.getInt("apartment_number"), resultSet.getString("floor"),
                                 resultSet.getString("zip_code"), resultSet.getString("city"),
                                 resultSet.getString("country"));

                    String jobTitle = resultSet.getString("job_title");
                    Company company = new Company(resultSet.getString("company_name"),
                                      new Address(resultSet.getString("street_name"), resultSet.getInt("street_number"),
                                                  resultSet.getInt("apartment_number"), resultSet.getString("floor"),
                                                  resultSet.getString("zip_code"), resultSet.getString("city"),
                                                  resultSet.getString("country")));
                    boolean isFavorite = resultSet.getBoolean("is_favorite");

                    User user = new User(user_id, firstName, lastName, email, age, phoneNumberHashMap, address, jobTitle, company, isFavorite);
                    contacts.add(user);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public User getContactById(int userId) {
        Statement statement = null;
        Integer id = userId;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = null;
            try {
                String sqlGetContactsById = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.age, " +
                        "ph.place, ph.country_code, ph.number, " +
                        "a.street_name, a.street_number, a.apartment_number, a.floor, a.zip_code, a.city, a.country, " +
                        "u.job_title, " +
                        "co.company_name, a_w.street_name, a_w.street_number, a_w.apartment_number, a_w.floor, a_w.zip_code, a_w.city, a_w.country, " +
                        "u.is_favorite " +
                        "FROM users u, phonenumbers ph, addresses a, companies co, addresses a_w " +
                        "WHERE u.user_id = " + id + " " +
                        "AND u.user_id = ph.user_id " +
                        "AND u.address_id = a.id " +
                        "AND u.company_id = co.company_id " +
                        "AND a_w.id = co.address_id;";


                resultSet = statement.executeQuery(sqlGetContactsById);

                while (resultSet.next()) {

                    Integer user_id = resultSet.getInt("user_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    Integer age = resultSet.getInt("age");

                    HashMap<String, PhoneNumber> phoneNumberHashMap = new HashMap <>();
                    phoneNumberHashMap.put(resultSet.getString("place"), new PhoneNumber(
                            resultSet.getString("country_code"),resultSet.getString("number")));

                    Address address = new Address(
                            resultSet.getString("street_name"), resultSet.getInt("street_number"),
                            resultSet.getInt("apartment_number"), resultSet.getString("floor"),
                            resultSet.getString("zip_code"), resultSet.getString("city"),
                            resultSet.getString("country"));

                    String jobTitle = resultSet.getString("job_title");
                    Company company = new Company(resultSet.getString("company_name"),
                            new Address(resultSet.getString("street_name"), resultSet.getInt("street_number"),
                                    resultSet.getInt("apartment_number"), resultSet.getString("floor"),
                                    resultSet.getString("zip_code"), resultSet.getString("city"),
                                    resultSet.getString("country")));
                    boolean isFavorite = resultSet.getBoolean("is_favorite");

                    User userById = new User(user_id, firstName, lastName, email, age, phoneNumberHashMap, address, jobTitle, company, isFavorite);
                    return userById;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User not found. ");
        return null;
    }

    @Override
    public void addContact(User contact) {

    }

    @Override
    public void editContact(int userId , String firstName , String lastName , String email , Integer age , Map <String, PhoneNumber> phoneNumber , Address address , String jobTitle , Company company , boolean isFavorite) {

    }

    @Override
    public void removeContact(int userId) {

    }

    @Override
    public List <User> search(String query) {
        return null;
    }
}
