import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Game extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String selectedCharacter;

    public Game() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Agregar paneles a la interfaz
        mainPanel.add(new MenuPanel(this), "Menu");
        mainPanel.add(new CharacterSelectionPanel(this), "CharacterSelection");
        mainPanel.add(new VowelLearningPanel(this), "VowelLearning");
        mainPanel.add(new EvaluationPanel(this), "Evaluation");
        mainPanel.add(new MinigamesPanel(this), "Minigame"); 

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void selectCharacter(String characterName) {
        // Almacena el personaje seleccionado
        this.selectedCharacter = characterName;
        System.out.println("Personaje seleccionado: " + characterName);
        showPanel("VowelLearning");
    }

    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        new Game();
    }
}