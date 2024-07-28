import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EvaluationPanel extends JPanel {
    private Image fondo;
    private JLabel exerciseArea;
    private JLabel imageLabel; // Nuevo JLabel para la imagen
    private Map<String, String> exercises;
    private Map<String, String> exerciseImages; // Mapa para las imágenes de los ejercicios
    private Map<String, String> completeWordSounds;
    private int exerciseIndex = 0;

    public EvaluationPanel(Game game) {
        // Añadir la imagen de fondo
        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")).getImage();

        completeWordSounds = new HashMap<>();
        completeWordSounds.put("ARROZ", "sounds/arroz.wav");
        completeWordSounds.put("ALA", "sounds/ala.wav");
        completeWordSounds.put("AVE", "sounds/ave.wav");
        completeWordSounds.put("ARO", "sounds/aro.wav");
        completeWordSounds.put("ABEJA", "sounds/abeja.wav");
        completeWordSounds.put("AGUA", "sounds/agua.wav");
        completeWordSounds.put("ANCLA", "sounds/ancla.wav");
        completeWordSounds.put("ARAÑA", "sounds/arana.wav");
        completeWordSounds.put("AMOR", "sounds/amor.wav");
        completeWordSounds.put("AZUL", "sounds/azul.wav");
        
        completeWordSounds.put("ELEFANTE", "sounds/elefante.wav");
        completeWordSounds.put("ESPEJO", "sounds/espejo.wav");
        completeWordSounds.put("ESTRELLA", "sounds/estrella.wav");
        completeWordSounds.put("ESCALERA", "sounds/escalera.wav");
        completeWordSounds.put("ESCUDO", "sounds/escudo.wav");
        completeWordSounds.put("ESCUELA", "sounds/escuela.wav");
        completeWordSounds.put("ESCOBA", "sounds/escoba.wav");
        completeWordSounds.put("ENANO", "sounds/enano.wav");
        completeWordSounds.put("ESTATUA", "sounds/estatua.wav");
        completeWordSounds.put("ESPONJA", "sounds/esponja.wav");
        
        completeWordSounds.put("ISLA", "sounds/isla.wav");
        completeWordSounds.put("IGLESIA", "sounds/iglesia.wav");
        completeWordSounds.put("IZQUIERDA", "sounds/izquierda.wav");
        completeWordSounds.put("IGLU", "sounds/iglu.wav");
        completeWordSounds.put("INSTRUMENTOS", "sounds/instrumentos.wav");
        completeWordSounds.put("INCENDIO", "sounds/incendio.wav");
        completeWordSounds.put("IMPRESORA", "sounds/impresora.wav");
        completeWordSounds.put("INVIERNO", "sounds/invierno.wav");
        completeWordSounds.put("IMAN", "sounds/iman.wav");
        completeWordSounds.put("IGUANA", "sounds/iguana.wav");
        
        completeWordSounds.put("OSO", "sounds/oso.wav");
        completeWordSounds.put("ORO", "sounds/oro.wav");
        completeWordSounds.put("OREJA", "sounds/oreja.wav");
        completeWordSounds.put("OLA", "sounds/ola.wav");
        completeWordSounds.put("OJO", "sounds/ojo.wav");
        completeWordSounds.put("OVEJA", "sounds/oveja.wav");
        completeWordSounds.put("OCHO", "sounds/ocho.wav");
        completeWordSounds.put("OLLA", "sounds/olla.wav");
        completeWordSounds.put("ORUGA", "sounds/oruga.wav");
        completeWordSounds.put("OSTRA", "sounds/ostra.wav");
        
        completeWordSounds.put("UVA", "sounds/uva.wav");
        completeWordSounds.put("UNO", "sounds/uno.wav");
        completeWordSounds.put("UÑA", "sounds/una.wav");
        completeWordSounds.put("UNICORNIO", "sounds/unicornio.wav");
        completeWordSounds.put("URRACA", "sounds/urraca.wav");
        completeWordSounds.put("UKELELE", "sounds/ukelele.wav");
        completeWordSounds.put("UNIFORME", "sounds/uniforme.wav");
        completeWordSounds.put("UTENSILIOS", "sounds/utensilios.wav");
        completeWordSounds.put("UNIVERSO", "sounds/universo.wav");
        completeWordSounds.put("URNA", "sounds/urna.wav");

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
        exercises.put("_GLU", "IGLU");
        exercises.put("_NSTRUMENTOS", "INSTRUMENTOS");
        exercises.put("_NCENDIO", "INCENDIO");
        exercises.put("_MPRESORA", "IMPRESORA");
        exercises.put("_NVIERNO", "INVIERNO");
        exercises.put("_MAN", "IMAN");  
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
        exercises.put("_TENSILIOS", "UTENSILIOS");
        exercises.put("_NIVERSO", "UNIVERSO");
        exercises.put("_RNA", "URNA");

        // Inicializar las imágenes de los ejercicios
        exerciseImages = new HashMap<>();
        exerciseImages.put("_RROZ", "/imagenes/arroz.png"); //Archivos png
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
        exerciseImages.put("_GLU", "/imagenes/iglu.jpg");
        exerciseImages.put("_NSTRUMENTOS", "/imagenes/instrumentos.png");
        exerciseImages.put("_NCENDIO", "/imagenes/incendio.jpg");
        exerciseImages.put("_MPRESORA", "/imagenes/impresora.jpg");
        exerciseImages.put("_NVIERNO", "/imagenes/invierno.jpg");
        exerciseImages.put("_MAN", "/imagenes/iman.jpg");  
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

        exerciseImages.put("_VA", "/imagenes/uva.png");
        exerciseImages.put("_NO", "/imagenes/uno.jpg");
        exerciseImages.put("_ÑA", "/imagenes/una.jpg");
        exerciseImages.put("_NICORNIO", "/imagenes/unicornio.jpg");
        exerciseImages.put("_RRACA", "/imagenes/urraca.jpg");
        exerciseImages.put("_KELELE", "/imagenes/ukelele.jpg");
        exerciseImages.put("_NIFORME", "/imagenes/uniforme.png");
        exerciseImages.put("_TENSILIOS", "/imagenes/utensilio.jpg");
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
                    checkAnswer(vowel);
                }
            });
            answersPanel.add(vowelButton);
        }
        answersPanel.setOpaque(false); // Hacer transparente el panel de respuestas

        // Cargar la imagen para el botón "Siguiente Ejercicio"
        ImageIcon nextIcon = new ImageIcon(getClass().getResource("/imagenes/siguiente.png"));
        Image nextImage = nextIcon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH);
        JButton nextButton = new JButton(new ImageIcon(nextImage));
        nextButton.setOpaque(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);

        // Acción para el botón "Siguiente Ejercicio"
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exerciseIndex++;
                if (exerciseIndex < exercises.size()) {
                    exerciseArea.setText(getCurrentExercise());
                    updateImage(); // Actualizar la imagen según el nuevo ejercicio
                } else {
                    JOptionPane.showMessageDialog(EvaluationPanel.this, "Felicidades! Completaste la evaluación.");
                    game.showPanel("Minigame");
                }
            }
        });

        // Panel para el botón "Siguiente Ejercicio" en la esquina superior derecha
        JPanel topRightPanel = new JPanel(new BorderLayout());
        topRightPanel.setOpaque(false); // Hacer transparente el panel
        topRightPanel.add(nextButton, BorderLayout.EAST);

        // Añadir el panel de contenido al panel principal
        contentPanel.add(answersPanel, BorderLayout.SOUTH);

        add(topRightPanel, BorderLayout.NORTH); // Añadir el panel en la parte superior
        add(contentPanel, BorderLayout.CENTER);
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
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setIcon(null);
        }
    }

    private void checkAnswer(String vowel) {
        String exerciseText = exerciseArea.getText();
        String correctAnswer = exercises.get(getCurrentExercise());
    
        if (correctAnswer != null && correctAnswer.startsWith(vowel)) {
            // Reemplazar el primer "_" con la vocal seleccionada
            exerciseText = exerciseText.replaceFirst("_", vowel);
            exerciseArea.setText(exerciseText);
    
            // Verificar si el ejercicio está completo
            if (!exerciseText.contains("_")) {
                playSound(completeWordSounds.get(correctAnswer)); // Reproducir el sonido de la palabra completa
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
            JOptionPane.showMessageDialog(this, "Incorrecto! Inténtalo de nuevo.");
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