package ro.avrmeduard.contactlist;

import ro.avrmeduard.contactlist.model.User;
import ro.avrmeduard.contactlist.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    private UserService userService;

    private static Scanner scanner = new Scanner(System.in);
    private static List <User> users = new ArrayList <>();

    public Menu(UserService userService) {
        this.userService = userService;
    }

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

    public void printActionMenu() {

        startPhone();
        printMenu();

        boolean quit = false;
        while (!quit) {

            int action = scanner.nextInt();
            scanner.nextLine();

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
            } while (scanner.hasNextInt());

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
        int indexContacts = scanner.nextInt();
        scanner.nextLine();
        System.out.println(users.get(indexContacts));
        System.out.println("Choose your actions: ");
    }

    private static void favoriteContacts(List<User> userList) {
        System.out.println("Favorite contacts : ");
        userList.stream().filter(User::isFavorite).map(u -> u.getFirstName() + " " + u.getLastName()).forEach(System.out::println);
        System.out.println("Choose your actions: ");
    }


}
