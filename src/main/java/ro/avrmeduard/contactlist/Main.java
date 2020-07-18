package ro.avrmeduard.contactlist;

import ro.avrmeduard.contactlist.service.DBUserService;
import ro.avrmeduard.contactlist.service.FileUserService;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        Menu menuFile = new Menu(new FileUserService("Contacts.txt"));
        Menu menuDb = new Menu(new DBUserService());





//        UserService service = new DBUserService();


//        Menu menu = new Menu();
//        // print menu
//        menu.printActionMenu();



        //print db contacts
        //service.getContacts()/*.forEach(System.out::println)*/;
        //get db contact by id
        //System.out.println(service.getContactById(4).toString());


    }

}