import javax.sound.sampled.*;
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
    private JLabel imageLabel; // Nuevo JLabel para la imagen
    private Map<String, String> exercises;
    private Map<String, String> exerciseImages; // Mapa para las imágenes de los ejercicios
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
        exercises.put("_NSTRUMENTOS", "INSTRUMENTOS");
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

        // Inicializar las imágenes de los ejercicios
        exerciseImages = new HashMap<>();
        exerciseImages.put("_RROZ", "/imagenes/arroz.png");
        exerciseImages.put("_LA", "/imagenes/ala.jpg");
        exerciseImages.put("_VE", "/imagenes/ave.jpg");
        exerciseImages.put("_RO", "/imagenes/aro.jpg");
        exerciseImages.put("_BEJA", "/imagenes/abeja.jpg");
        exerciseImages.put("_GUA", "/imagenes/agua.jpg");
        exerciseImages.put("_NCLA", "/imagenes/ancla.jpg");
        exerciseImages.put("_RAÑA", "/imagenes/araña.jpg");
        exerciseImages.put("_MOR", "/imagenes/amor.jpg");
        exerciseImages.put("_ZUL", "/imagenes/azul.png");

        exerciseImages.put("_LEFANTE", "/imagenes/elefante.jpg");
        exerciseImages.put("_SPEJO", "/imagenes/espejo.jpg");
        exerciseImages.put("_STRELLA", "/imagenes/estrella.jpg");
        exerciseImages.put("_SCALERA", "/imagenes/escalera.jpg");
        exerciseImages.put("_SCUDO", "/imagenes/escudo.jpg");
        exerciseImages.put("_SCUELA", "/imagenes/escuela.png");
        exerciseImages.put("_SCOBA", "/imagenes/escoba.png");
        exerciseImages.put("_NANO", "/imagenes/enano.jpg");
        exerciseImages.put("_STATUA", "/imagenes/estatua.jpg");
        exerciseImages.put("_SPONJA", "/imagenes/esponja.jpg");

        exerciseImages.put("_SLA", "/imagenes/isla.jpg");
        exerciseImages.put("_GLESIA", "/imagenes/iglesia.jpg");
        exerciseImages.put("_ZQUIERDA", "/imagenes/izquierda.png");
        exerciseImages.put("_GLÚ", "/imagenes/iglu.jpg");
        exerciseImages.put("_NSTRUMENTOS", "/imagenes/instrumentos.png");
        exerciseImages.put("_NCENDIO", "/imagenes/incendio.jpg");
        exerciseImages.put("_MPRESORA", "/imagenes/impresora.jpg");
        exerciseImages.put("_NVIERNO", "/imagenes/invierno.jpg");
        exerciseImages.put("_MÁN", "/imagenes/iman.jpg");  
        exerciseImages.put("_GUANA", "/imagenes/iguana.jpg");

        exerciseImages.put("_SO", "/imagenes/oso.jpg");
        exerciseImages.put("_RO", "/imagenes/oro.jpg");
        exerciseImages.put("_REJA", "/imagenes/oreja.jpg");
        exerciseImages.put("_LA", "/imagenes/ola.jpg");
        exerciseImages.put("_JO", "/imagenes/ojo.jpg");
        exerciseImages.put("_VEJA", "/imagenes/oveja.jpg");
        exerciseImages.put("_CHO", "/imagenes/ocho.jpg");
        exerciseImages.put("_LLA", "/imagenes/olla.jpg");
        exerciseImages.put("_RUGA", "/imagenes/oruga.jpg");
        exerciseImages.put("_STRA", "/imagenes/ostra.jpg");

        exerciseImages.put("_VA", "/imagenes/uva.jpg");
        exerciseImages.put("_NO", "/imagenes/uno.jpg");
        exerciseImages.put("_ÑA", "/imagenes/una.jpg");
        exerciseImages.put("_NICORNIO", "/imagenes/unicornio.jpg");
        exerciseImages.put("_RRACA", "/imagenes/urraca.jpg");
        exerciseImages.put("_KELELE", "/imagenes/ukelele.jpg");
        exerciseImages.put("_NIFORME", "/imagenes/uniforme.jpg");
        exerciseImages.put("_TENSILIO", "/imagenes/utensilio.jpg");
        exerciseImages.put("_NIVERSO", "/imagenes/universo.jpg");
        exerciseImages.put("_RNA", "/imagenes/urna.jpg");

        // Área para mostrar los ejercicios
        exerciseArea = new JLabel();
        exerciseArea.setFont(new Font("Serif", Font.PLAIN, 24));
        exerciseArea.setText(getCurrentExercise());
        exerciseArea.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto

        // Área para mostrar la imagen del ejercicio
        imageLabel = new JLabel();
        updateImage(); // Actualizar la imagen según el ejercicio actual
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel para abordar el área de ejercicios y los botones
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false); // Hacer transparente el panel
        contentPanel.add(exerciseArea, BorderLayout.NORTH);
        contentPanel.add(imageLabel, BorderLayout.CENTER); // Añadir el JLabel de la imagen

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
                    updateImage(); // Actualizar la imagen según el nuevo ejercicio
                } else {
                    JOptionPane.showMessageDialog(EvaluationPanel.this, "Felicidades! Completaste la evaluación.");
                    game.showPanel("Menu");
                }
            }
        });
        nextButton.setOpaque(false); // Hacer el botón transparente

        // Añadir el panel de contenido al panel principal
        contentPanel.add(answersPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        add(nextButton, BorderLayout.SOUTH);
    }

    private String getCurrentExercise() {
        if (exercises.isEmpty()) {
            return "No exercises available.";
        }
        return (String) exercises.keySet().toArray()[exerciseIndex];
    }

    private void updateImage() {
        String currentExercise = getCurrentExercise();
        String imagePath = exerciseImages.get(currentExercise);
        if (imagePath != null) {
            imageLabel.setIcon(new ImageIcon(getClass().getResource(imagePath)));
        } else {
            imageLabel.setIcon(null);
        }
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
                    updateImage(); // Actualizar la imagen según el nuevo ejercicio
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
