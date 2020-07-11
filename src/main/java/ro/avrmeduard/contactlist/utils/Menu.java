package ro.avrmeduard.contactlist.utils;

import ro.avrmeduard.contactlist.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static List <User> users = new ArrayList <>();

    public void startPhone() {
        System.out.println("Starting phone ...");
    }

    public void printMenu() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0 - to shutdown\n" +
                "1 - to print contacts\n" +
                "2 - to print contacts details\n" +
                "3 - to print favorite list\n" +
                "4 - to print a list of available actions.");
        System.out.println("Choose your actions: ");
    }

}
