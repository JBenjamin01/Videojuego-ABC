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

    private Image fondo;
    private Map<String, String> vowelSounds;

    public VowelLearningPanel(Game game) {
        // Añadir la imagen de fondo
        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")).getImage();

        vowelSounds = new LinkedHashMap<>();
        vowelSounds.put("A", "sounds/a.wav");
        vowelSounds.put("E", "sounds/e.wav");
        vowelSounds.put("I", "sounds/i.wav");
        vowelSounds.put("O", "sounds/o.wav");
        vowelSounds.put("U", "sounds/u.wav");

        setLayout(new BorderLayout());

        // Panel para las vocales
        JPanel vowelsPanel = new JPanel(new GridLayout(1, 5));
        vowelsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Ajustar espaciado entre botones
        vowelsPanel.setOpaque(false); // Hacer transparente el panel de botones
        for (String vowel : vowelSounds.keySet()) {
            JButton vowelButton = new JButton(vowel);
            vowelButton.setFont(new Font("Serif", Font.PLAIN, 25)); // Tamaño de fuente ajustado
            vowelButton.setPreferredSize(new Dimension(70, 70)); // Tamaño de botón ajustado
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
                game.showPanel("CharacterSelection");
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}