package ro.avrmeduard.contactlist.utils;

import ro.avrmeduard.contactlist.model.PhoneNumber;
import ro.avrmeduard.contactlist.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UtilsService {

    public static List <String> contactToString(List<? extends User> objects) {

        List<String> userInLine = new ArrayList <>();
        StringBuilder line = new StringBuilder();

        for (User u : objects) {

            line.append(u.getUserID() + "|" + u.getFirstName() + "|" + u.getLastName() + "|" + u.getEmail() + "|" + u.getAge() + "|");
            int count = 1;

            for (Map.Entry<String, PhoneNumber> entry : u.getPhoneNumbers().entrySet()) {
                line.append(entry.getKey() + "_" + entry.getValue().toString());

                if (count < u.getPhoneNumbers().entrySet().size()) {
                    line.append(";");
                }
                count++;
            }

            line.append("|" + u.getAddress().toString());
            line.append("|" + u.getCompany().getName() + "_" + u.getCompany().getAddress().toString());
            line.append("|" + u.getIsFavorite());
            userInLine.add(line.toString());
            line = new StringBuilder();
        }
        return userInLine;
    }

}
