import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        // La base de datos está en Clever Cloud
        String url = "jdbc:mysql://bopntoh687tet4w3pzrq-mysql.services.clever-cloud.com:3306/bopntoh687tet4w3pzrq";
        String user = "ua7t7fruxl6yoqso";
        String password = "nZdF9JBWd6rT68kmiNZU";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("La conexión a la base de datos funciona!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}