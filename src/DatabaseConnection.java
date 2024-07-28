import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    // La conexión a la base de datos
    private Connection connection;
    private String url = "jdbc:mysql://bopntoh687tet4w3pzrq-mysql.services.clever-cloud.com:3306/bopntoh687tet4w3pzrq";
    private String user = "ua7t7fruxl6yoqso";
    private String password = "nZdF9JBWd6rT68kmiNZU";


    // Constructor privado para evitar instanciación directa
    private DatabaseConnection() {
        try {
            // Cargar el driver de JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión a la base de datos
            this.connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la instancia única
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Método para obtener la conexión a la base de datos
    public Connection getConnection() {
        return this.connection;
    }
}