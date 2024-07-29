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
    private Image fondo;

    public ResultsPanel(Game game) {
        this.game = game;
        fondo = new ImageIcon(getClass().getResource("/imagenes/menu.gif")).getImage();

        setLayout(new BorderLayout());
        setOpaque(false);

        // Title
        JLabel scoreLabel = new JLabel("Tu examen ha terminado", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Cooper Black", Font.BOLD, 80));
        scoreLabel.setForeground(Color.BLACK);
        add(scoreLabel, BorderLayout.NORTH);

        // Score Details
        scoreDetails = new JLabel("", SwingConstants.CENTER);
        scoreDetails.setFont(new Font("Cooper Black", Font.PLAIN, 80));
        scoreDetails.setForeground(Color.BLACK);
        add(scoreDetails, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout());

        JButton showScoreButton = new JButton("Mostrar Puntaje");
        showScoreButton.setFont(new Font("Cooper Black", Font.PLAIN, 30));
        showScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateScore();
                //insertScoreIntoDatabase();
            }
        });
        buttonPanel.add(showScoreButton);

        JButton backButton = new JButton("Volver al Menú");
        backButton.setFont(new Font("Cooper Black", Font.PLAIN, 30));
        backButton.addActionListener(e -> game.showPanel("Menu"));
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
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
            statement.setString(1, "PlayerName");
            statement.setInt(2, finalScore);
            statement.executeUpdate();
            System.out.println("Puntaje insertado en la base de datos: " + finalScore);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}