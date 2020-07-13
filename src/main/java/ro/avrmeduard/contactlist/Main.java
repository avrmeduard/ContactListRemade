package ro.avrmeduard.contactlist;

import ro.avrmeduard.contactlist.service.DBUserService;
import ro.avrmeduard.contactlist.service.UserService;

public class Main {

    public static void main(String[] args) {
        // initialize db
        UserService service = new DBUserService();

        Menu menu = new Menu();
        // print menu
        menu.printActionMenu();


        //print db contacts
        service.getContacts()/*.forEach(System.out::println)*/;
        //get db contact by id
        System.out.println(service.getContactById(4).toString());


    }

}
