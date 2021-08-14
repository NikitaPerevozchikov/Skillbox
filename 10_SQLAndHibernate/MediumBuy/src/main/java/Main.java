import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    private static final Logger logger = LogManager.getLogger("LOGGER");

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "testTEST";

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("" +
                    "SELECT course_name, COUNT(*)/((MAX(MONTH(subscription_date))-MIN(MONTH(subscription_date))+1)) AS medium_count_pay " +
                    "FROM purchaselist " +
                    "WHERE YEAR(subscription_date) = 2018 " +
                    "GROUP BY course_name");

            System.out.format("%-35s%s%n", "Наименование курса", "Среднее кол-во покупок в месяц");

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                Double mediumCountPay = resultSet.getDouble("medium_count_pay");

                System.out.format("%-35s%-10.3f%n", courseName, mediumCountPay);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
