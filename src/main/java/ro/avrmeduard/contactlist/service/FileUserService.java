package ro.avrmeduard.contactlist.service;

import ro.avrmeduard.contactlist.model.Address;
import ro.avrmeduard.contactlist.model.Company;
import ro.avrmeduard.contactlist.model.PhoneNumber;
import ro.avrmeduard.contactlist.model.User;

import java.util.List;
import java.util.Map;

public class FileUserService implements UserService {
    @Override
    public List <User> getContacts() {
        return null;
    }

    @Override
    public User getContactById(int userId) {
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
