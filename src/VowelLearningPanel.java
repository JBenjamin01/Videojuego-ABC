import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class VowelLearningPanel extends JPanel {
    private Map<String, String> vowelSounds;

    public VowelLearningPanel(Game game) {
        vowelSounds = new LinkedHashMap<>();
        vowelSounds.put("A", "sounds/a.wav");
        vowelSounds.put("E", "sounds/e.wav");
        vowelSounds.put("I", "sounds/i.wav");
        vowelSounds.put("O", "sounds/o.wav");
        vowelSounds.put("U", "sounds/u.wav");

        setLayout(new BorderLayout());

        // Panel para las vocales
        JPanel vowelsPanel = new JPanel(new GridLayout(1, 5));
        for (String vowel : vowelSounds.keySet()) {
            JButton vowelButton = new JButton(vowel);
            vowelButton.setFont(new Font("Serif", Font.PLAIN, 100));
            vowelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    playSound(vowelSounds.get(vowel));
                }
            });
            vowelsPanel.add(vowelButton);
        }

        // Botón "Siguiente"
        JButton nextButton = new JButton("Siguiente");
        nextButton.setFont(new Font("Serif", Font.PLAIN, 30)); // Tamaño de fuente grande
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.showPanel("Evaluation");
            }
        });

        add(vowelsPanel, BorderLayout.CENTER);
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
}