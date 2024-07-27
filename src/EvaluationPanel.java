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

public class EvaluationPanel extends JPanel {
    private Image fondo;
    private JLabel exerciseArea;
    private Map<String, String> exercises;
    private Map<String, String> vowelSounds;
    private int exerciseIndex = 0;

    public EvaluationPanel(Game game) {
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

        exercises.put("_LEFANTE", "ELEFANTE");
        exercises.put("_SPEJO", "ESPEJO");
        exercises.put("_STRELLA", "ESTRELLA");
        exercises.put("_SCALERA", "ESCALERA");
        exercises.put("_SCUDO", "ESCUDO");
        exercises.put("_SCUELA", "ESCUELA");
        exercises.put("_SCOBA", "ESCOBA");
        exercises.put("_NANO", "ENANO");
        exercises.put("_STATUA", "ESTATUA");
        exercises.put("_SPONJA", "ESPONJA");

        exercises.put("_SLA", "ISLA");
        exercises.put("_GLESIA", "IGLESIA");
        exercises.put("_ZQUIERDA", "IZQUIERDA");
        exercises.put("_GLÚ", "IGLÚ");
        exercises.put("_NSTRUMENTO", "INSTRUMENTO");
        exercises.put("_NCENDIO", "INCENDIO");
        exercises.put("_MPRESORA", "IMPRESORA");
        exercises.put("_NVIERNO", "INVIERNO");
        exercises.put("_MÁN", "IMÁN");  
        exercises.put("_GUANA", "IGUANA");

        exercises.put("_SO", "OSO");
        exercises.put("_RO", "ORO");
        exercises.put("_REJA", "OREJA");
        exercises.put("_LA", "OLA");
        exercises.put("_JO", "OJO");
        exercises.put("_VEJA", "OVEJA");
        exercises.put("_CHO", "OCHO");
        exercises.put("_LLA", "OLLA");
        exercises.put("_RUGA", "ORUGA");
        exercises.put("_STRA", "OSTRA");

        exercises.put("_VA", "UVA");
        exercises.put("_NO", "UNO");
        exercises.put("_ÑA", "UÑA");
        exercises.put("_NICORNIO", "UNICORNIO");
        exercises.put("_RRACA", "URRACA");
        exercises.put("_KELELE", "UKELELE");
        exercises.put("_NIFORME", "UNIFORME");
        exercises.put("_TENSILIO", "UTENSILIO");
        exercises.put("_NIVERSO", "UNIVERSO");
        exercises.put("_RNA", "URNA");

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
        answersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
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
                    JOptionPane.showMessageDialog(EvaluationPanel.this, "Congratulations! You've completed the evaluation.");
                    game.showPanel("MinigamePanel");
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
                exerciseIndex++;
                if (exerciseIndex < exercises.size()) {
                    exerciseArea.setText(getCurrentExercise());
                } else {
                    JOptionPane.showMessageDialog(this, "Felicidades! Completaste la evaluación.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Incorrecto! Intentalo de nuevo.");
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            // Dibuja la imagen de fondo sólo si está disponible
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}