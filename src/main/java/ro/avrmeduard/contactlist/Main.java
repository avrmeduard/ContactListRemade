package ro.avrmeduard.contactlist;

import ro.avrmeduard.contactlist.model.Address;
import ro.avrmeduard.contactlist.model.Company;
import ro.avrmeduard.contactlist.model.PhoneNumber;
import ro.avrmeduard.contactlist.model.User;
import ro.avrmeduard.contactlist.service.DBUserService;
import ro.avrmeduard.contactlist.service.FileUserService;
import ro.avrmeduard.contactlist.service.UserService;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        UserService service = new DBUserService();


        Menu menuFile = new Menu(new FileUserService("Contacts.txt"));
        Menu menuDb = new Menu(new DBUserService());

        User userTest = new User(null,"Bruce", "Tyron", "brucetyron@aol.org", 33, new HashMap<>(),
                new Address("T15 John Eliot", 15, 723, "5th", "CA 45574", "San Diego", "United States"),"Barber",
                new Company("Barber's Shop", new Address("Daemon St", 11, 12, "6th", "LV 22014", "San Diego", "United States")), false);


        userTest.getPhoneNumbers().put("work", new PhoneNumber("01", "36876554"));

        //service.addContact(userTest);





        // print db contacts
        // service.getContacts().forEach(System.out::println);
        // get db contact by id
         System.out.println(service.getContactById(30).toString());


    }

}