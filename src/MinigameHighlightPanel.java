import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MinigameHighlightPanel extends JPanel {
    private Image fondo;
    private int correctCount;
    private MinigamesPanel parentPanel;
    private JLabel[] letterLabels;
    private Map<Character, String> vowelSounds;

    public MinigameHighlightPanel(MinigamesPanel parentPanel) {
        this.parentPanel = parentPanel;

        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")).getImage();

        vowelSounds = new LinkedHashMap<>();
        vowelSounds.put('A', "sounds/a.wav");
        vowelSounds.put('E', "sounds/e.wav");
        vowelSounds.put('I', "sounds/i.wav");
        vowelSounds.put('O', "sounds/o.wav");
        vowelSounds.put('U', "sounds/u.wav");

        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        String word = "ELEFANTE";
        letterLabels = new JLabel[word.length()];

        for (int i = 0; i < word.length(); i++) {
            letterLabels[i] = createLetterLabel(word.charAt(i));
            add(letterLabels[i]);
        }

        JButton checkButton = new JButton("Check Points");
        checkButton.addActionListener(e -> showResults());
        add(checkButton);
    }

    private JLabel createLetterLabel(char letter) {
        JLabel label = new JLabel(String.valueOf(letter), SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 48));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setPreferredSize(new Dimension(70, 70));
        label.addMouseListener(new HighlightMouseAdapter(label, letter));
        return label;
    }

    private class HighlightMouseAdapter extends MouseAdapter {
        private JLabel label;
        private char letter;

        public HighlightMouseAdapter(JLabel label, char letter) {
            this.label = label;
            this.label.setOpaque(true);
            this.letter = letter;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (label.getBackground() == Color.WHITE) {
                if (isVowel(letter)) {
                    label.setBackground(Color.GREEN);
                    correctCount++;
                    playSound(vowelSounds.get(letter));
                } else {
                    label.setBackground(Color.RED);
                }
                label.removeMouseListener(this);
            }
        }
    }

    private boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    private void playSound(String soundFile) {
        try {
            File audioFile = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    private void showResults() {
        JOptionPane.showMessageDialog(this, 
            "Resultados del Minijuego:\n" +
            "Vocales correctas: " + correctCount);

        correctCount = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            // Dibuja la imagen de fondo sólo si está disponible
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}