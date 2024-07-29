import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoresDialog extends JDialog {
    private JTable scoresTable;

    public ScoresDialog() {
        setTitle("Puntuaciones");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        scoresTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(scoresTable);
        add(scrollPane, BorderLayout.CENTER);

        loadScores();
    }

    private void loadScores() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Nombre", "Puntuación", "Fecha"}, 0);
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String query = "SELECT nombre, puntaje, date FROM puntuacion ORDER BY puntaje DESC";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int puntaje = resultSet.getInt("puntaje");
                String date = resultSet.getString("date");
                model.addRow(new Object[]{nombre, puntaje, date});
            }

            scoresTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}