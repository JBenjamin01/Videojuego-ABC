import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQuery {
    public static void main(String[] args) {
        String url = "jdbc:mysql://bopntoh687tet4w3pzrq-mysql.services.clever-cloud.com:3306/bopntoh687tet4w3pzrq";
        String user = "ua7t7fruxl6yoqso";
        String password = "nZdF9JBWd6rT68kmiNZU";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            
            String query = "SELECT * FROM texto WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, 1);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String texto = resultSet.getString("texto");
                    System.out.println(id + " - Texto: " + texto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}