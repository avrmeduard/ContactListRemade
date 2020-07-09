package ro.avrmeduard.contactlist.utils;

import ro.avrmeduard.contactlist.model.Address;
import ro.avrmeduard.contactlist.model.Company;
import ro.avrmeduard.contactlist.model.PhoneNumber;
import ro.avrmeduard.contactlist.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UtilsService {

    public static List<User> userList(List<String> stringList) {

        List<User> userList = new ArrayList <>();

        for (User u : userList) {

            for (String s : stringList) {

                String[] userProperties = s.split("\\|");

                u.setUserID(Integer.parseInt(userProperties[0]));
                u.setFirstName(userProperties[1]);
                u.setLastName(userProperties[2]);
                u.setEmail(userProperties[3]);
                u.setAge(Integer.parseInt(userProperties[4]));

                String[] phoneArr = userProperties[5].split(",");
                for (String phoneNo : phoneArr) {
                    String[] phoneNoArr = phoneNo.split("_");
                    PhoneNumber ph = new PhoneNumber(phoneNoArr[1] , phoneArr[2]);

                    u.getPhoneNumbers().put(phoneArr[0] , ph);
                }

                String[] addressProp = userProperties[6].split("_");
                Address address = new Address(addressProp[0] , Integer.parseInt(addressProp[1]) , Integer.parseInt(addressProp[2]) ,
                        addressProp[3] , addressProp[4] , addressProp[5] , addressProp[6]);


                String[] companyProp = userProperties[8].split("_");
                Company company = new Company(companyProp[0] , new Address(companyProp[1] , Integer.parseInt(companyProp[2]) ,
                        Integer.parseInt(companyProp[3]) , companyProp[4] , companyProp[5] , companyProp[6] , companyProp[7]));

                u.setAddress(address);
                u.setJobTitle(userProperties[7]);
                u.setCompany(company);


                String favorite = userProperties[9];

                boolean isFavorite = false;
                if (favorite.equals("Favorite")) {
                    isFavorite = true;
                } else if (favorite.equals("Not favorite")) {
                    isFavorite = false;
                }

                u.setFavorite(isFavorite);

                userList.add(u);
            }
        }
        return userList;
    }

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

    public static void writeToFile(List<String> stringList) {

        BufferedWriter out = null;
        try {
            // the second parameter append set to false,
            // will overwrite the file (clear it and write to it again).
            out = new BufferedWriter(new FileWriter("Contacts.txt" , false));

            for (String s : stringList) {
                out.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
