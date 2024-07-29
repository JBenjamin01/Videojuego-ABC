import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Game extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String personajeEscojido;
    private String playerName;

    public Game() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new NameEntryPanel(this), "NameEntry");

        mainPanel.add(new MenuPanel(this), "Menu");
        mainPanel.add(new CharacterSelectionPanel(this), "CharacterSelection");
        mainPanel.add(new VowelLearningPanel(this), "VowelLearning");
        mainPanel.add(new EvaluationPanel(this), "Evaluation");
        mainPanel.add(new MinigamesPanel(this), "Minigame");
        mainPanel.add(new ResultsPanel(this), "Results"); 
        
        try {
            File audioFile = new File("sounds/bg.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Obtener el control de ganancia para ajustar el volumen
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Bajar el volumen (los dB son logarítmicos, cada -6dB es aproximadamente la mitad del volumen)
            float volume = -28.0f; // Ajusta este valor según sea necesario (0.0f es el volumen original)
            gainControl.setValue(volume);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void selectCharacter(String characterName, String ruta) {
        // Almacena el personaje seleccionado
        this.personajeEscojido = ruta;
        System.out.println("Personaje seleccionado: " + characterName);
        showPanel("VowelLearning");
    }

    public String getSelectedCharacter() {
        return personajeEscojido;
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public static void main(String[] args) {
        new Game();
    }
}