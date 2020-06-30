package ro.avrmeduard.contactlist.service;

public class DBPreparedStatement {

    public String getDBContacts() {
        String sql = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.age, " +
                            "ph.place, ph.country_code, ph.number, " +
                            "a.street_name, a.street_number, a.apartment_number, a.floor, a.zip_code, a.city, a.country, " +
                            "u.job_title, " +
                            "co.company_name, a_w.street_name, a_w.street_number, a_w.apartment_number, a_w.floor, a_w.zip_code, a_w.city, a_w.country, " +
                            "u.is_favorite " +
                     "FROM users u, phonenumbers ph, addresses a, companies co, addresses a_w " +
                     "WHERE u.user_id = ph.user_id " +
                     "AND u.address_id = a.id " +
                     "AND u.company_id = co.company_id " +
                     "AND a_w.id = co.address_id;";

        return sql;
    }


    public String getDBContactsById(int userId) {
        int id = userId;

        String sql = "SELECT u.user_id, u.first_name, u.last_name, u.email, u.age, " +
                            "ph.place, ph.country_code, ph.number, " +
                            "a.street_name, a.street_number, a.apartment_number, a.floor, a.zip_code, a.city, a.country, " +
                            "u.job_title, " +
                            "co.company_name, a_w.street_name, a_w.street_number, a_w.apartment_number, a_w.floor, a_w.zip_code, a_w.city, a_w.country, " +
                            "u.is_favorite " +
                "FROM users u, phonenumbers ph, addresses a, companies co, addresses a_w " +
                "WHERE u.user_id = " + id +
                "AND u.user_id = ph.user_id " +
                "AND u.address_id = a.id " +
                "AND u.company_id = co.company_id " +
                "AND a_w.id = co.address_id;";

        return sql;
    }


}
