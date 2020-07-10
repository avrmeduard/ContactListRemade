package ro.avrmeduard.contactlist.service;

import ro.avrmeduard.contactlist.model.Address;
import ro.avrmeduard.contactlist.model.Company;
import ro.avrmeduard.contactlist.model.PhoneNumber;
import ro.avrmeduard.contactlist.model.User;
import ro.avrmeduard.contactlist.utils.UtilsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FileUserService implements UserService {

    private boolean quit;

//    private File contactsFile;
    private List <User> contacts = new ArrayList <>();


//    public FileIUserService(File contactsFile) {
//        this.contactsFile = contactsFile;
//    }
//
//    public FileIUserService(String contactsFileName) {
//        this(new File(contactsFileName));
//    }

    @Override
    public List <User> getContacts() {
        // check if contacts is empty
        if(contacts.isEmpty()) {
            contacts.addAll(UtilsService.readFromFile());
        }
        // else return the curent list of contacts
        return contacts;
    }

    @Override
    public User getContactById(int userId) {
        return contacts.stream().filter(u -> u.getUserID() == userId).findFirst().get();
    }

    @Override
    public void addContact(User contact) {
        contacts.add(contact);
    }

    @Override
    public void editContact(int userId , String firstName , String lastName , String email , Integer age , Map <String, PhoneNumber> phoneNumber , Address address , String jobTitle , Company company , boolean isFavorite) {

    }

    @Override
    public void removeContact(int userId) {

        contacts.remove(getContactById(userId));
    }

    @Override
    public List <User> search(String query) {
        List<String> result = new ArrayList<>();
        String regex = ".*" + query + ".*";

        for (String user : UtilsService.contactToString(contacts)) {
            boolean matches = Pattern.matches(regex, user);
            if (matches) {
                result.add(user);
            }
        }
        // convert list of string to a list of users
        //return a list of users
        return UtilsService.userList(result);
    }


}
