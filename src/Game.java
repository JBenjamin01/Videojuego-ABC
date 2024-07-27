import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Game extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Game() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Agregar paneles a la interfaz
        mainPanel.add(new MenuPanel(this), "Menu");
        mainPanel.add(new VowelLearningPanel(this), "VowelLearning");
        mainPanel.add(new EvaluationPanel(this), "Evaluation");
        mainPanel.add(new MinigamePanel(this), "MinigamePanel"); 

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        new Game();
    }
}