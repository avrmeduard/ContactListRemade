package ro.avrmeduard.contactlist.db.connection;
import java.sql.*;

public class DBConnection {

    private static Connection connection = null;

    private DBConnection() { }

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost/contactlist?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "bazadedate";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,pass);

            try (Statement statement = connection.createStatement()){
                String sql = "SELECT * FROM users LIMIT 10";

                try (ResultSet resultSet = statement.executeQuery(sql)){
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt("user_id") + " : " + resultSet.getString("first_name") + " " +
                                           resultSet.getString("last_name") + " " + resultSet.getInt("age") + " " +
                                           resultSet.getBoolean("is_favorite"));
                    }
                }
            } catch (SQLException e) {
                System.out.println("Sql failed \n" + e);
            }

        } catch (Exception e) {
            System.out.println("Connection failed \n" + e);
        }
        return connection;
    }
}
