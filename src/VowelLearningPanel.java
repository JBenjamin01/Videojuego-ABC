import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class VowelLearningPanel extends JPanel {
    private Map<String, String> vowelSounds;
    private JTextArea wordArea;
    private String currentWord;
    private final String[] words = {"_S_", "_RR_R", "V_C_"};
    private int wordIndex = 0;

    public VowelLearningPanel(Game game) {
        vowelSounds = new HashMap<>();
        vowelSounds.put("A", "sounds/a.wav");
        vowelSounds.put("E", "sounds/e.wav");
        vowelSounds.put("I", "sounds/i.wav");
        vowelSounds.put("O", "sounds/o.wav");
        vowelSounds.put("U", "sounds/u.wav");

        setLayout(new BorderLayout());

        // Area para mostrar la palabra con vocales difuminadas
        wordArea = new JTextArea(5, 20);
        wordArea.setEditable(false);
        wordArea.setFont(new Font("Serif", Font.PLAIN, 24));
        wordArea.setText(words[wordIndex]);

        // Botones para las vocales
        JPanel vowelsPanel = new JPanel();
        for (String vowel : vowelSounds.keySet()) {
            JButton vowelButton = new JButton(vowel);
            vowelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    replaceVowel(vowel);
                    playSound(vowelSounds.get(vowel));
                }
            });
            vowelsPanel.add(vowelButton);
        }

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wordIndex++;
                if (wordIndex < words.length) {
                    wordArea.setText(words[wordIndex]);
                } else {
                    game.showPanel("Evaluation");
                }
            }
        });

        add(new JScrollPane(wordArea), BorderLayout.CENTER);
        add(vowelsPanel, BorderLayout.NORTH);
        add(nextButton, BorderLayout.SOUTH);
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

    private void replaceVowel(String vowel) {
        if (wordIndex < words.length) {
            String currentWord = words[wordIndex];
            String newWord = currentWord.replaceFirst("_", vowel);
            wordArea.setText(newWord);
            words[wordIndex] = newWord;  // Actualiza la palabra actual
        }
    }
}