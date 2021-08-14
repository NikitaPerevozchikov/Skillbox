import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    private static final Logger logger = LogManager.getLogger("LOGGER");

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/learn?useSSL=false&serverTimezone=UTC";
        String url2 = "jdbc:mysql://localhost:3306/exerciseplustwo?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "testTEST";

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            Statement statement = connection.createStatement();
            System.out.println("Rjyytrn");
            statement.execute("DELETE names FROM names " +
                    "LEFT JOIN names AS names2 ON names.name=names2.name " +
                    "WHERE names.id > names2.id");
            ResultSet resultSet = statement.executeQuery("SELECT name, id FROM names");
            System.out.format("%-5s%-20s%n", "ID", "NAME");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                System.out.format("%-5s%-15s%n", id, name);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        try (Connection connection = DriverManager.getConnection(url2, user, pass)) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE names FROM names " +
                    "LEFT JOIN names AS names2 ON names.name=names2.name AND (names.manager_id=names2.manager_id OR ((names.manager_id AND names2.manager_id) IS NULL))" +
                    "WHERE names.id > names2.id");
            ResultSet resultSet = statement.executeQuery("SELECT name, id, manager_id FROM names");

            System.out.format("%n%-5s%-15s%-15s%n",  "ID", "NAME", "MANAGER ID");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String managerId = resultSet.getString("manager_id");
                System.out.format("%-5s%-15s%-15s%n", id, name, managerId);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }


    }
}