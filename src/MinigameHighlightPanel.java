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
    private int totalScore;
    private MinigamesPanel parentPanel;
    private JLabel[] letterLabels;
    private Map<Character, String> vowelSounds;
    private static final String[] WORDS = {"ELEFANTE", "UKELELE", "IGLESIA", "OSCURO", "ANILLO"};
    private int currentWordIndex = 0;
    private JButton nextButton;
    private int wordScore;
    private int totalVowels;
    private int markedVowels;

    public MinigameHighlightPanel(MinigamesPanel parentPanel) {
        this.parentPanel = parentPanel;

        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")).getImage();

        vowelSounds = new LinkedHashMap<>();
        vowelSounds.put('A', "sounds/a.wav");
        vowelSounds.put('E', "sounds/e.wav");
        vowelSounds.put('I', "sounds/i.wav");
        vowelSounds.put('O', "sounds/o.wav");
        vowelSounds.put('U', "sounds/u.wav");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        nextButton = new JButton(new ImageIcon(getClass().getResource("/imagenes/siguiente.png")));
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.addActionListener(e -> goToNextWord());

        displayCurrentWord(gbc);
    }

    private void displayCurrentWord(GridBagConstraints gbc) {
        removeAll();
        String word = WORDS[currentWordIndex];
        letterLabels = new JLabel[word.length()];
        wordScore = 0;
        totalVowels = 0;
        markedVowels = 0;
    
        gbc.gridwidth = 1;
        gbc.gridy = 0;
    
        for (int i = 0; i < word.length(); i++) {
            letterLabels[i] = createLetterLabel(word.charAt(i));
            if (isVowel(word.charAt(i))) {
                totalVowels++;
            }
            gbc.gridx = i;
            add(letterLabels[i], gbc);
        }
    
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(nextButton, gbc);
    
        revalidate();
        repaint();
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
                    playSound(vowelSounds.get(letter));
                    markedVowels++;
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

    private void goToNextWord() {
        if (markedVowels == totalVowels) {
            wordScore = 1;
        } else {
            wordScore = 0;
        }
    
        totalScore += wordScore;
    
        if (currentWordIndex < WORDS.length - 1) {
            currentWordIndex++;
            displayCurrentWord(new GridBagConstraints());
        } else {
            showResults();
        }
    }

    private void showResults() {
        ScoreManager.getInstance().increaseScore(totalScore);
        JOptionPane.showMessageDialog(this,
            "Resultados del Minijuego:\n" +
            "Puntaje total: " + totalScore);

        totalScore = 0;
        currentWordIndex = 0;
        parentPanel.getGame().showPanel("Menu");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
