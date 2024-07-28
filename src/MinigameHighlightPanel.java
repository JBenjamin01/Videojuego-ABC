import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MinigameHighlightPanel extends JPanel {
    private int correctCount;
    private MinigamesPanel parentPanel;
    private JLabel[] letterLabels;

    public MinigameHighlightPanel(MinigamesPanel parentPanel) {
        this.parentPanel = parentPanel;
        setLayout(new GridLayout(1, 0));

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
        label.setPreferredSize(new Dimension(80, 80));
        label.addMouseListener(new HighlightMouseAdapter(label, letter));
        return label;
    }

    private class HighlightMouseAdapter extends MouseAdapter {
        private JLabel label;
        private char letter;

        public HighlightMouseAdapter(JLabel label, char letter) {
            this.label = label;
            this.letter = letter;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (label.getBackground() == Color.WHITE) {
                if (isVowel(letter)) {
                    label.setBackground(Color.GREEN);
                    correctCount++;
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

    private void showResults() {
        JOptionPane.showMessageDialog(this, 
            "Resultados del Minijuego:\n" +
            "Vocales correctas: " + correctCount);
        
        correctCount = 0;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Minigame Highlight Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);
        frame.add(new MinigameHighlightPanel(null));
        frame.setVisible(true);
    }
}