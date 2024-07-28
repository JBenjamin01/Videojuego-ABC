import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResultsPanel extends JPanel {
    private Game game;
    private static final int MAX_SCORE = 20;
    private JLabel scoreDetails;

    public ResultsPanel(Game game) {
        this.game = game;

        setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("Tu exámen ha terminado", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(scoreLabel, BorderLayout.NORTH);

        // Inicialmente, no mostramos el puntaje
        scoreDetails = new JLabel("", SwingConstants.CENTER);
        scoreDetails.setFont(new Font("Serif", Font.PLAIN, 20));
        add(scoreDetails, BorderLayout.CENTER);

        JButton showScoreButton = new JButton("Mostrar Puntaje");
        showScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateScore();
                insertScoreIntoDatabase();
            }
        });
        add(showScoreButton, BorderLayout.EAST);

        JButton backButton = new JButton("Volver al Menú");
        backButton.addActionListener(e -> game.showPanel("Menu"));
        add(backButton, BorderLayout.SOUTH);
    }

    private void updateScore() {
        int finalScore = ScoreManager.getInstance().getScore();
        scoreDetails.setText(String.format("Puntaje final: %d/%d", finalScore, MAX_SCORE));

        revalidate();
        repaint();
    }

    private void insertScoreIntoDatabase() {
        int finalScore = ScoreManager.getInstance().getScore();
        Connection connection = DatabaseConnection.getInstance().getConnection();

        String sql = "INSERT INTO .....";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, finalScore);
            statement.executeUpdate();
            System.out.println("Puntaje insertado en la base de datos: " + finalScore);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
