import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clase que representa un panel de minijuego en el que los jugadores deben identificar y marcar las vocales en palabras.
 * El panel muestra una palabra en letras grandes, y el niño debe hacer clic en las letras que son vocales. 
 * Las vocales correctas se resaltan en verde y se reproduce un sonido asociado a la vocal. Las letras no vocales se 
 * resaltan en rojo. Al final de cada palabra, el jugador puede avanzar a la siguiente palabra con un botón. Si todas 
 * las vocales han sido marcadas correctamente, se suma un puntaje. Al completar todas las palabras, se muestran los resultados.
 */
public class MinigameHighlightPanel extends JPanel {
    // Variables para el panel
    private Image fondo; // Imagen de fondo
    private int totalScore; // Puntaje total acumulado
    private MinigamesPanel parentPanel; // Referencia al panel padre
    private JLabel[] letterLabels; // Etiquetas para las letras de la palabra
    private Map<Character, String> vowelSounds; // Mapa de sonidos para las vocales
    private static final String[] WORDS = {"ELEFANTE", "UKELELE", "IGLESIA", "OSCURO", "ANILLO"}; // Palabras para el minijuego
    private int currentWordIndex = 0; // Índice de la palabra actual
    private JButton nextButton; // Botón para pasar a la siguiente palabra
    private JButton soundButton; // Botón para reproducir el sonido
    private int wordScore; // Puntaje para la palabra actual
    private int totalVowels; // Total de vocales en la palabra actual
    private int markedVowels; // Total de vocales marcadas por el usuario
    private JPanel contentPanel; // Panel para el contenido principal

    public MinigameHighlightPanel(MinigamesPanel parentPanel) {
        this.parentPanel = parentPanel;

        // Cargar la imagen de fondo
        fondo = new ImageIcon(getClass().getResource("/imagenes/menu.gif")).getImage();

        // Inicializar el mapa de sonidos para cada vocal
        vowelSounds = new LinkedHashMap<>();
        vowelSounds.put('A', "sounds/a.wav");
        vowelSounds.put('E', "sounds/e.wav");
        vowelSounds.put('I', "sounds/i.wav");
        vowelSounds.put('O', "sounds/o.wav");
        vowelSounds.put('U', "sounds/u.wav");

        // Configurar el diseño del panel
        setLayout(new BorderLayout());

        // Crear el botón para reproducir el sonido
        soundButton = new JButton("Reproducir sonido");
        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("sounds/Cuarto-mini-juego.wav"); // Reproducir sonido del minijuego
            }
        });

        // Panel para el botón de sonido
        JPanel soundPanel = new JPanel();
        soundPanel.setOpaque(false);
        soundPanel.add(soundButton);

        // Agregar el panel de sonido al panel principal
        add(soundPanel, BorderLayout.NORTH);

        // Crear el panel para el contenido principal
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        add(contentPanel, BorderLayout.CENTER);

        // Crear el botón para pasar a la siguiente palabra
        nextButton = new JButton(new ImageIcon(getClass().getResource("/imagenes/siguiente.png")));
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.addActionListener(e -> goToNextWord()); // Acción para pasar a la siguiente palabra

        // Mostrar la primera palabra
        displayCurrentWord();
    }

    /**
     * Muestra la palabra actual en el panel, desglosada en letras individuales.
     * Las letras son configuradas para que el usuario pueda hacer clic en ellas.
     */
    private void displayCurrentWord() {
        contentPanel.removeAll(); // Limpiar el panel de contenido
        String word = WORDS[currentWordIndex]; // Obtener la palabra actual
        letterLabels = new JLabel[word.length()]; // Crear una etiqueta por cada letra
        wordScore = 0; // Resetear el puntaje de la palabra
        totalVowels = 0; // Resetear el conteo de vocales
        markedVowels = 0; // Resetear el conteo de vocales marcadas

        // Configurar restricciones de diseño para el GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        // Crear y agregar etiquetas de letras al panel de contenido
        for (int i = 0; i < word.length(); i++) {
            letterLabels[i] = createLetterLabel(word.charAt(i));
            if (isVowel(word.charAt(i))) {
                totalVowels++; // Contar las vocales
            }
            gbc.gridx = i;
            contentPanel.add(letterLabels[i], gbc);
        }

        // Configurar el botón de siguiente y agregarlo al panel
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(30, 10, 10, 10);
        contentPanel.add(nextButton, gbc);

        contentPanel.revalidate(); // Actualizar el panel de contenido
        contentPanel.repaint(); // Redibujar el panel de contenido
    }

    /**
     * Crea una etiqueta para una letra con un formato específico.
     * La etiqueta muestra la letra y responde a eventos de clic del mouse.
     */
    private JLabel createLetterLabel(char letter) {
        JLabel label = new JLabel(String.valueOf(letter), SwingConstants.CENTER);
        label.setFont(new Font("Cooper Black", Font.PLAIN, 70));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setPreferredSize(new Dimension(150, 150));
        label.addMouseListener(new HighlightMouseAdapter(label, letter)); // Agregar listener para eventos de clic
        return label;
    }

    /**
     * Adaptador de mouse para resaltar letras al hacer clic.
     */
    private class HighlightMouseAdapter extends MouseAdapter {
        private JLabel label; // Etiqueta de la letra
        private char letter; // Letra asociada

        public HighlightMouseAdapter(JLabel label, char letter) {
            this.label = label;
            this.label.setOpaque(true);
            this.letter = letter;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (label.getBackground() == Color.WHITE) { // Si la letra no ha sido marcada
                if (isVowel(letter)) {
                    label.setBackground(Color.GREEN); // Marcar vocales en verde
                    playSound(vowelSounds.get(letter)); // Reproducir sonido de la vocal
                    markedVowels++; // Incrementar el conteo de vocales marcadas
                } else {
                    label.setBackground(Color.RED); // Marcar letras no vocales en rojo
                }
                label.removeMouseListener(this); // Remover el listener para evitar marcar la misma letra varias veces
            }
        }
    }

    /**
     * Verifica si un carácter es una vocal.
     *
     * @param c El carácter a verificar
     * @return true si el carácter es una vocal, false en caso contrario
     */
    private boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    /**
     * Reproduce un archivo de sonido especificado.
     */
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

    /**
     * Avanza a la siguiente palabra o muestra los resultados si se han completado todas.
     */
    private void goToNextWord() {
        if (markedVowels == totalVowels) {
            wordScore = 1; // Puntaje completo si todas las vocales están marcadas
        } else {
            wordScore = 0; // Puntaje cero si no se han marcado todas las vocales
        }

        totalScore += wordScore; // Acumular puntaje total

        if (currentWordIndex < WORDS.length - 1) {
            currentWordIndex++; // Avanzar al siguiente índice de palabra
            displayCurrentWord(); // Mostrar la siguiente palabra
        } else {
            showResults(); // Mostrar resultados al completar todas las palabras
        }
    }

    /**
     * Muestra los resultados del minijuego y reinicia el índice de palabras.
     */
    private void showResults() {
        ScoreManager.getInstance().increaseScore(totalScore); // Incrementar el puntaje total en el gestor de puntajes
        JOptionPane.showMessageDialog(this,
            "Resultados del Minijuego:\n" +
            "Puntaje del ejercicio: " + totalScore); // Mostrar mensaje con el puntaje
        currentWordIndex = 0; // Reiniciar el índice de palabras
        parentPanel.getGame().showPanel("Results"); // Mostrar el panel de resultados en el juego
    }

    /**
     * Dibuja la imagen de fondo en el panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this); // Dibujar la imagen de fondo
        }
    }
}