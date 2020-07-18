package ro.avrmeduard.contactlist;

import ro.avrmeduard.contactlist.model.User;
import ro.avrmeduard.contactlist.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    private UserService userService;

    private static List <User> users = new ArrayList <>();

    public Menu(UserService userService) {
        this.userService = userService;
    }


    public void startPhone() {
        System.out.println("Starting phone ...");
    }


    // get contact
    // get contact by id
    // add contact
    // edit contact
    // search
    public void printMenu() {
        System.out.println("\nAvailable actions :\n");

        System.out.println("1 - Show contacts\n" +
                           "2 - Add contact\n" +
                           "3 - Edit contact\n" +
                           "3 - Search\n" +
                           "3 - Favorite contacts\n" +
                           "4 - Favorite contacts.");
        System.out.print("Choose your actions :  ");
    }

    public void printActionMenu() {

        startPhone();
        printMenu();

        boolean quit = false;
        while (!quit) {

            int action = Main.scanner.nextInt();
            Main.scanner.nextLine();

            do {
                switch (action) {
                    case 0:
                        System.out.println("Shutting down ...");
                        quit = true;
                        break;
                    case 1:
                        contactsList(users);
                        break;
                    case 2:
                        contactsDetails(users);
                        break;
                    case 3:
                        favoriteContacts(users);
                        break;
                    case 4:
                        editContact();
                        break;
                    case 5:

                }
            } while (Main.scanner.hasNextInt());

        }
    }

    private void editContact() {
    }

    private static void contactsList(List<User> userList) {
        Map <Character, List<User>> naturalOrder = userList.stream().sorted(Comparator.comparing(User::getFirstName).thenComparing(User::getLastName))
                .collect(Collectors.groupingBy(u -> u.getFirstName().charAt(0)));

        int index = 1;
        for (Map.Entry<Character, List<User>> listEntry : naturalOrder.entrySet()) {
            System.out.println("\n" + listEntry.getKey() + "\n" + "---------------");
            for (User header : listEntry.getValue()) {
                System.out.println(index++ + ". " + header.getFirstName() + " " + header.getLastName());
            }
            System.out.println("---------------");
        }
        System.out.println("Choose your actions: ");
    }

    private static void contactsDetails(List<User> users) {
        System.out.println("Select an index to print his contact details ");
        int indexContacts = Main.scanner.nextInt();
        Main.scanner.nextLine();
        System.out.println(users.get(indexContacts));
        System.out.println("Choose your actions: ");
    }

    private static void favoriteContacts(List<User> userList) {
        System.out.println("Favorite contacts : ");
        userList.stream().filter(User::isFavorite).map(u -> u.getFirstName() + " " + u.getLastName()).forEach(System.out::println);
        System.out.println("Choose your actions: ");
    }


}
