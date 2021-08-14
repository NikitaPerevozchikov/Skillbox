import java.sql.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DBConnection {
    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "testtest";
    private static StringBuilder insertQuery = new StringBuilder();
    private static ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

    public static ThreadPoolExecutor getService() {
        return service;
    }
    public static StringBuilder getInsertQuery() {
        return insertQuery;
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?allowPublicKeyRetrieval=true" +
                                "&useSSL=false" +
                                "&serverTimezone=UTC" +
                                "&user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert(String insert) {
        Runnable runnable = () -> {
            try (Statement statement = connection.createStatement()) {
                    String sql = "INSERT INTO voter_count(name, birthDate, `count`) VALUES" +
                            insert;
                    statement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
        service.execute(runnable);
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        String partQuery = "('" + name + "', '" + birthDay + "', 1)";
        if (insertQuery.length() > 10_000_000) {
            String insert = insertQuery.toString();
            executeMultiInsert(insert);
            insertQuery = new StringBuilder();
        }
        insertQuery.append((insertQuery.length() == 0 ? "" : ", ") + partQuery);
    }


    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, SUM(count) AS counts FROM voter_count GROUP BY name, birthDate " +
                "HAVING `counts` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("counts"));
        }
    }

}
