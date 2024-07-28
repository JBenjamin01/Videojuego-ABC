import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsPanel extends JPanel {
    private Game game;
    private static final int MAX_SCORE = 20;

    public ResultsPanel(Game game) {
        this.game = game;
        int finalScore = ScoreManager.getInstance().getScore();

        setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("Tu exámen ha terminado", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(scoreLabel, BorderLayout.NORTH);

        JLabel scoreDetails = new JLabel(String.format("Puntaje final: %d/%d", finalScore, MAX_SCORE), SwingConstants.CENTER);
        scoreDetails.setFont(new Font("Serif", Font.PLAIN, 20));
        add(scoreDetails, BorderLayout.CENTER);

        JButton backButton = new JButton("Volver al Menú");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.showPanel("Menu");
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }
}