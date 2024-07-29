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
        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")).getImage();

        //Almacenar ruta de los archivos de sonido
        sonidosVocales = new LinkedHashMap<>();
        sonidosVocales.put("A", "sounds/a.wav");
        sonidosVocales.put("E", "sounds/e.wav");
        sonidosVocales.put("I", "sounds/i.wav");
        sonidosVocales.put("O", "sounds/o.wav");
        sonidosVocales.put("U", "sounds/u.wav");

        setLayout(new BorderLayout());

        //Panel para las vocales
        JPanel panelVocales = new JPanel(new GridLayout(1, 5));
        panelVocales.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); //Ajustar espacio entre botones
        panelVocales.setOpaque(false); //Hacer transparente el panel
        for (String vocal : sonidosVocales.keySet()) {
            JButton vocalesBtn = new JButton(vocal);
            vocalesBtn.setFont(new Font("Cooper Black", Font.PLAIN, 100)); //Establecemos el decorado del texto
            vocalesBtn.setPreferredSize(new Dimension(200, 200)); //Ajustar el tamaño de los botones
            vocalesBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    playSound(sonidosVocales.get(vocal)); //Al ser presionados se ejecuta el archivo de sonido
                }
            });
            panelVocales.add(vocalesBtn);
            
        }

        //Botón Siguiente
        JButton siguienteBtn = new JButton("Siguiente");
        siguienteBtn.setFont(new Font("Cooper Black", Font.PLAIN, 30)); //Establecemos el decorado del texto
        siguienteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.showPanel("Evaluation");
            }
        });
        add(panelVocales, BorderLayout.NORTH);
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