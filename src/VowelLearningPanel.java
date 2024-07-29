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
    private Map<String, String> sonidosVocales;

    public VowelLearningPanel(Game game) {
        // Añadir la imagen de fondo
        fondo = new ImageIcon(getClass().getResource("/imagenes/menu.gif")).getImage();

        //Almacenar ruta de los archivos de sonido
        sonidosVocales = new LinkedHashMap<>();
        sonidosVocales.put("A", "sounds/a.wav");
        sonidosVocales.put("E", "sounds/e.wav");
        sonidosVocales.put("I", "sounds/i.wav");
        sonidosVocales.put("O", "sounds/o.wav");
        sonidosVocales.put("U", "sounds/u.wav");

        setLayout(new BorderLayout());

        // Panel para el título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setOpaque(false); // Hacer transparente el panel
        ImageIcon tituloIcon = new ImageIcon(getClass().getResource("/imagenes/vocalesTitulo.png"));
        JLabel tituloLabel = new JLabel(tituloIcon);
        panelTitulo.add(tituloLabel);
        
        // Panel para las vocales
        JPanel panelVocales = new JPanel();
        panelVocales.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Ajustar espacio entre botones
        panelVocales.setOpaque(false); // Hacer transparente el panel
        for (String vocal : sonidosVocales.keySet()) {
            JButton vocalesBtn = new JButton(vocal);
            vocalesBtn.setFont(new Font("Cooper Black", Font.PLAIN, 100)); // Establecemos el decorado del texto
            vocalesBtn.setPreferredSize(new Dimension(200, 200)); // Ajustar el tamaño de los botones
            vocalesBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    playSound(sonidosVocales.get(vocal)); // Al ser presionados se ejecuta el archivo de sonido
                }
            });
            panelVocales.add(vocalesBtn);
        }

        // Panel intermedio para centrar el panelVocales verticalmente
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setOpaque(false); // Hacer transparente el panel
        panelCentro.add(Box.createVerticalGlue()); // Añadir espacio elástico arriba
        panelCentro.add(panelVocales); // Añadir panelVocales al panelCentro
        panelCentro.add(Box.createVerticalGlue()); // Añadir espacio elástico abajo

        // Botón Siguiente
        JButton siguienteBtn = new JButton("Siguiente");
        siguienteBtn.setFont(new Font("Cooper Black", Font.PLAIN, 30)); // Establecemos el decorado del texto
        siguienteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.showPanel("Evaluation");
            }
        });

        // Añadir paneles al panel principal
        add(panelTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(siguienteBtn, BorderLayout.SOUTH);
    }

    private void playSound(String soundFile) { //Método para reproducir sonidos
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