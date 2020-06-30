package ro.avrmeduard.contactlist;

import ro.avrmeduard.contactlist.service.DBUserService;
import ro.avrmeduard.contactlist.service.UserService;

public class Main {

    public static void main(String[] args) {

        UserService service = new DBUserService();

        //print db contacts
        service.getContacts()/*.forEach(System.out::println)*/;

        System.out.println("\n\n\n\n\n\n\n\n");


        //get db contact by id
        System.out.println(service.getContactById(4).toString());


    }

}
