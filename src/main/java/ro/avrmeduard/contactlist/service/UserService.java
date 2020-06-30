package ro.avrmeduard.contactlist.service;

import ro.avrmeduard.contactlist.model.Address;
import ro.avrmeduard.contactlist.model.Company;
import ro.avrmeduard.contactlist.model.PhoneNumber;
import ro.avrmeduard.contactlist.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List <User> getContacts();

    User getContactById(int userId);

    void addContact(User contact);

    void editContact(int userId, String firstName,
                     String lastName,
                     String email,
                     Integer age, Map <String, PhoneNumber> phoneNumber,
                     Address address,
                     String jobTitle,
                     Company company,
                     boolean isFavorite);

    void removeContact(int userId);

    List<User> search(String query);

}
