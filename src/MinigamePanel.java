import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MinigamePanel extends JPanel {
    private Image fondo;
    private JLabel exerciseArea;
    private Map<String, String> exercises;
    private Map<String, String> vowelSounds;
    private int exerciseIndex = 0;
    private int correctAnswers = 0; // Contador de respuestas correctas
    private int incorrectAnswers = 0; // Contador de respuestas incorrectas
    private Game game; // Referencia al objeto Game

    public MinigamePanel(Game game) {
        this.game = game; // Asignar la referencia

        // Añadir la imagen de fondo
        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")).getImage();

        vowelSounds = new LinkedHashMap<>();
        vowelSounds.put("A", "sounds/a.wav");
        vowelSounds.put("E", "sounds/e.wav");
        vowelSounds.put("I", "sounds/i.wav");
        vowelSounds.put("O", "sounds/o.wav");
        vowelSounds.put("U", "sounds/u.wav");

        setLayout(new BorderLayout());

        // Inicializar los ejercicios
        exercises = new HashMap<>();
        exercises.put("_RROZ", "ARROZ");
        exercises.put("_LA", "ALA");
        exercises.put("_VE", "AVE");
        exercises.put("_RO", "ARO");
        exercises.put("_BEJA", "ABEJA");
        exercises.put("_GUA", "AGUA");
        exercises.put("_NCLA", "ANCLA");
        exercises.put("_RAÑA", "ARAÑA");
        exercises.put("_MOR", "AMOR");
        exercises.put("_ZUL", "AZUL");

        exercises.put("_LEFANTE", "ELEfANTE");
        exercises.put("_SPEJO", "ESPEJO");
        exercises.put("_STRELLA", "ESTRELLA");
        exercises.put("_SCALERA", "ESCALERA");
        exercises.put("_SCUDO", "ESCUDO");
        exercises.put("_SCUELA", "ESCUELA");
        exercises.put("_SCOBA", "ESCOBA");
        exercises.put("_NANO", "ENANO");
        exercises.put("_STATUA", "ESTATUA");
        exercises.put("_SPONJA", "ESPONJA");

        // Área para mostrar los ejercicios
        exerciseArea = new JLabel();
        exerciseArea.setFont(new Font("Serif", Font.PLAIN, 24));
        exerciseArea.setText(getCurrentExercise());
        exerciseArea.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto

        // Panel para abordar el área de ejercicios y los botones
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false); // Hacer transparente el panel
        contentPanel.add(exerciseArea, BorderLayout.NORTH);

        // Panel para botones de respuestas
        JPanel answersPanel = new JPanel(new GridLayout(1, 5));
        String[] vowels = {"A", "E", "I", "O", "U"};
        for (String vowel : vowels) {
            JButton vowelButton = new JButton(vowel);
            vowelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playSound(vowelSounds.get(vowel));
                    checkAnswer(vowel);
                }
            });
            answersPanel.add(vowelButton);
        }
        answersPanel.setOpaque(false); // Hacer transparente el panel de respuestas

        // Botón para pasar al siguiente ejercicio
        JButton nextButton = new JButton("Siguiente Ejercicio");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exerciseIndex++;
                if (exerciseIndex < exercises.size()) {
                    exerciseArea.setText(getCurrentExercise());
                } else {
                    showResults();
                }
            }
        });
        nextButton.setOpaque(false); // Hacer el botón transparente

        // Añadir el panel de contenido al panel principal
        contentPanel.add(answersPanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.NORTH);
        add(nextButton, BorderLayout.SOUTH);
    }

    private String getCurrentExercise() {
        if (exercises.isEmpty()) {
            return "No exercises available.";
        }
        return (String) exercises.keySet().toArray()[exerciseIndex];
    }

    private void checkAnswer(String vowel) {
        String exerciseText = exerciseArea.getText();
        String correctAnswer = exercises.get(getCurrentExercise());

        if (correctAnswer != null && correctAnswer.contains(vowel)) {
            // Update the exercise text with the selected vowel
            exerciseText = exerciseText.replaceFirst("_", vowel);
            exerciseArea.setText(exerciseText);

            // Check if the exercise is complete
            if (!exerciseText.contains("_")) {
                JOptionPane.showMessageDialog(this, "Correcto! Vamos al siguiente.");
                correctAnswers++; // Incrementar respuestas correctas
                exerciseIndex++;
                if (exerciseIndex < exercises.size()) {
                    exerciseArea.setText(getCurrentExercise());
                } else {
                    showResults();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Incorrecto! Intentalo de nuevo.");
            incorrectAnswers++; // Incrementar respuestas incorrectas
        }
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
        int totalExercises = correctAnswers + incorrectAnswers;
        double successRate = (correctAnswers / (double) totalExercises) * 100;

        JOptionPane.showMessageDialog(this, 
            "Resultados del Minijuego:\n" +
            "Aciertos: " + correctAnswers + "\n" +
            "Errores: " + incorrectAnswers + "\n" +
            "Porcentaje de efectividad: " + String.format("%.2f", successRate) + "%");

        correctAnswers = 0; // Reiniciar el contador de respuestas correctas
        incorrectAnswers = 0; // Reiniciar el contador de respuestas incorrectas
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