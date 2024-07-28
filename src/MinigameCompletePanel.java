import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MinigameCompletePanel extends JPanel {
    private Image fondo;
    private JLabel exerciseArea;
    private Map<String, String> exercises;
    private Map<String, String> vowelSounds;
    private int exerciseIndex = 0;
    private int correctAnswers = 0; // Contador de respuestas correctas
    private int incorrectAnswers = 0; // Contador de respuestas incorrectas
    private MinigamesPanel parentPanel; // Referencia al panel de minijuegos

    public MinigameCompletePanel(MinigamesPanel parentPanel) {
        this.parentPanel = parentPanel; // Asignar la referencia

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
        exercises = new LinkedHashMap<>();
        exercises.put("_MIG_", "AMIGO");
        exercises.put("S_NT_MIENT_", "SENTIMIENTO");
        exercises.put("_BUEL_", "ABUELO");
        exercises.put("C_RAZ_N", "CORAZON");
        exercises.put("C_L_GI_", "COLEGIO");

        // Área para mostrar los ejercicios
        exerciseArea = new JLabel();
        exerciseArea.setBorder(BorderFactory.createEmptyBorder(50, 40, 0, 0));
        exerciseArea.setFont(new Font("Serif", Font.PLAIN, 60));
        exerciseArea.setText(getCurrentExercise());
        exerciseArea.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel para abordar el área de ejercicios y los botones
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false); // Hacer transparente el panel
        contentPanel.add(exerciseArea, BorderLayout.NORTH);

        // Panel para botones de respuestas
        JPanel answersPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        answersPanel.setBorder(BorderFactory.createEmptyBorder(70, 40, 130, 40));
        String[] vowels = {"A", "E", "I", "O", "U"};
        Font buttonFont = new Font("Serif", Font.BOLD, 30);
        for (String vowel : vowels) {
            JButton vowelButton = new JButton(vowel);
            vowelButton.setFont(buttonFont);
            vowelButton.setPreferredSize(new Dimension(80, 80));
            vowelButton.setBackground(Color.WHITE);
            vowelButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
            vowelButton.setFocusPainted(false);
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

        // Añadir el panel de contenido al panel principal
        contentPanel.add(answersPanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
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
        boolean correct = false;

        for (int i = 0; i < exerciseText.length(); i++) {
            if (exerciseText.charAt(i) == '_' && correctAnswer.charAt(i) == vowel.charAt(0)) {
                exerciseText = exerciseText.substring(0, i) + vowel + exerciseText.substring(i + 1);
                correct = true;
            }
        }

        if (correct) {
            exerciseArea.setText(exerciseText);
            if (!exerciseText.contains("_")) {
                JOptionPane.showMessageDialog(this, "¡Correcto! Vamos al siguiente.");
                correctAnswers++; // Incrementar respuestas correctas
                nextExercise();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Incorrecto. Pasando al siguiente.");
            incorrectAnswers++; // Incrementar respuestas incorrectas
            nextExercise();
        }
    }

    private void nextExercise() {
        exerciseIndex++;
        if (exerciseIndex < exercises.size()) {
            exerciseArea.setText(getCurrentExercise());
        } else {
            showResults();
            parentPanel.showMinigame("Highlight");
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
        exerciseIndex = 0; // Reiniciar el índice del ejercicio
        exerciseArea.setText(getCurrentExercise()); // Reiniciar el área del ejercicio
        parentPanel.showMinigame("Highlight");
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