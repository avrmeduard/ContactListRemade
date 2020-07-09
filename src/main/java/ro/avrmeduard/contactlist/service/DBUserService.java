package ro.avrmeduard.contactlist.service;

import ro.avrmeduard.contactlist.model.Address;
import ro.avrmeduard.contactlist.model.Company;
import ro.avrmeduard.contactlist.model.PhoneNumber;
import ro.avrmeduard.contactlist.model.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
            System.out.println("Wrong input data connection \n" + e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(url+timeZone, user, pass);
        } catch (SQLException e) {
            System.out.println("Open connection failed \n" + e.getMessage());
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

        List <User> u = new ArrayList <>();
        u.add(contact);

//        contact.setUserID(null);
        PreparedStatement pstmtContact = null;
        PreparedStatement pstmtPhone = null;
        PreparedStatement pstmtAddress = null;
        PreparedStatement pstmtCompany = null;

        //for getting contact user id
        ResultSet rsId = null;

        try {
            for (User user : u) {

                connection.setAutoCommit(false);
                String sqlUsers = "INSERT INTO users(user_id, first_name, last_name, email, age, job_title, is_favorite)"
                        + "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
                pstmtContact = connection.prepareStatement(sqlUsers , Statement.RETURN_GENERATED_KEYS);

                pstmtContact.setString(1 , user.getFirstName());
                pstmtContact.setString(2 , user.getLastName());
                pstmtContact.setString(3 , user.getEmail());
                pstmtContact.setInt(4 , user.getAge());
                pstmtContact.setString(5 , user.getJobTitle());
                pstmtContact.setBoolean(6 , user.isFavorite());

                int rowAffected = pstmtContact.executeUpdate();

                //get user id
                rsId = pstmtContact.getGeneratedKeys();
                int userId = 0;
                if (rsId.next()) userId = rsId.getInt(1);

                if (rowAffected == 1) {
                    String sqlPhone = "INSER INTO phonenumbers(user_id, country_code, number, place)"
                            + "VALUES (?, ?, ?, ?)";
                    pstmtPhone = connection.prepareStatement(sqlPhone);

                    // insert phone number
                    for (Map.Entry <String, PhoneNumber> entry : user.getPhoneNumbers().entrySet()) {
                        pstmtPhone.setInt(1 , userId);

                        pstmtPhone.setString(2 , entry.getValue().getCountryCode());
                        pstmtPhone.setString(3 , entry.getValue().getNumber());
                        pstmtPhone.setString(4 , entry.getKey());

                        pstmtPhone.executeUpdate();
                    }

                    // insert home address
                    String sqlAddress = "INSERT INTO address(street_name, street_number, apartment_number, floor, zip_code, city, country)"
                            + "VALUES (?, ?, ? ,? ,? ,? ,?)";
                    pstmtAddress = connection.prepareStatement(sqlAddress);
                    pstmtAddress.setString(1 , user.getAddress().getStreetName());
                    pstmtAddress.setInt(2 , user.getAddress().getStreetNumber());
                    pstmtAddress.setInt(3 , user.getAddress().getApartmentNumber());
                    pstmtAddress.setString(4 , user.getAddress().getFloor());
                    pstmtAddress.setString(5 , user.getAddress().getZipCode());
                    pstmtAddress.setString(6 , user.getAddress().getCity());
                    pstmtAddress.setString(7 , user.getAddress().getCountry());
                    pstmtAddress.executeUpdate();


                    // insert company address
                    String sqlCompany = "INSERT INTO companies(company_name, address_id)"
                            + "VALUES (?, DEFAULT);"
                            + "INSERT INTO addresses(street_name, street_number, apartment_number, floor, zip_code, city, country, id)"
                            + "VALUES (?, ?, ? ,? ,? ,? ,?, LAST_INSERT_ID())";
                    pstmtCompany = connection.prepareStatement(sqlCompany);
                    pstmtCompany.setString(1 , user.getCompany().getName());
                    pstmtCompany.setString(2 , user.getCompany().getAddress().getStreetName());
                    pstmtCompany.setInt(3 , user.getCompany().getAddress().getStreetNumber());
                    pstmtCompany.setInt(4 , user.getCompany().getAddress().getApartmentNumber());
                    pstmtCompany.setString(5 , user.getCompany().getAddress().getFloor());
                    pstmtCompany.setString(6 , user.getCompany().getAddress().getZipCode());
                    pstmtCompany.setString(7 , user.getCompany().getAddress().getCity());
                    pstmtCompany.setString(8 , user.getCompany().getAddress().getCountry());
                    pstmtCompany.executeUpdate();

                    connection.commit();
                } else {
                    connection.rollback();
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {

            try {
                if (rsId != null) rsId.close();
                if (pstmtContact != null) pstmtContact.close();
                if (pstmtPhone != null) pstmtPhone.close();
                if (pstmtAddress != null) pstmtAddress.close();
                if (pstmtCompany != null) pstmtCompany.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    @Override
    public void editContact(int userId , String firstName , String lastName , String email , Integer age , Map <String, PhoneNumber> phoneNumber , Address address , String jobTitle , Company company , boolean isFavorite) {

    }

    public void backUpContacts() {
        String path = "backUpFile" + File.separator;
        ZoneId ro = ZoneId.of("Europe/Bucharest");

        try {
            File source = new File("Contacts.txt");
            File dest = new File(path + LocalDateTime.now(ro));
            Files.copy(source.toPath(), dest.toPath());
        }catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void removeContact(int userId) {


        // daca gasim contactul , backup, sleep, delete
        PreparedStatement pstmtContactDelete = null;

        if (getContactById(userId) != null) {

            // back up contacts before deleting a user
            backUpContacts();
            try {
                Thread.sleep(500);

                try {
                    connection.setAutoCommit(false);
                    String deleteContact = "DELETE u, ph, a, co, a_w " +
                            "FROM users AS u" +
                            "INNER JOIN phonenumbers AS ph ON u.user_id = ph.user_id" +
                            "INNER JOIN addresses AS a ON u.address_id = a.id " +
                            "INNER JOIN companies AS co ON u.company_id = co.company_id" +
                            "INNER JOIN addresses AS a_w ON co.address_id = a_w.id" +
                            "WHERE u.user_id = ?;";

                    pstmtContactDelete = connection.prepareStatement(deleteContact);
                    pstmtContactDelete.setInt(1, userId);

                    int rowAffected = pstmtContactDelete.executeUpdate();

                    System.out.println(rowAffected + "Rows affected.");
                    connection.commit();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (pstmtContactDelete != null) pstmtContactDelete.close();
                }
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }  // end of if condition
    }

    @Override
    public List <User> search(String query) {
        return null;
    }
}
