import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MinigameDragPanel extends JPanel {
    private MinigamesPanel parentPanel; // Referencia al panel de minijuegos
    private JPanel gridPanel;
    private JPanel dragPanel;
    private Map<String, JLabel> gridLabels;
    private Image fondo;
    private Map<String, String> vowelSounds;

    public MinigameDragPanel(MinigamesPanel parentPanel) {
        this.parentPanel = parentPanel;

        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")).getImage();

        vowelSounds = new LinkedHashMap<>();
        vowelSounds.put("A", "sounds/a.wav");
        vowelSounds.put("E", "sounds/e.wav");
        vowelSounds.put("I", "sounds/i.wav");
        vowelSounds.put("O", "sounds/o.wav");
        vowelSounds.put("U", "sounds/u.wav");

        setLayout(new BorderLayout());

        // Panel para las vocales
        dragPanel = new JPanel();
        dragPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        dragPanel.setBorder(BorderFactory.createTitledBorder("Vocales"));
        dragPanel.setOpaque(false);

        // Panel para la cuadrícula
        gridPanel = new JPanel(new GridLayout(1, 5, 20, 20));
        gridPanel.setBorder(BorderFactory.createTitledBorder("Arrastra las vocales aquí"));
        gridPanel.setOpaque(false);

        gridLabels = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            JLabel gridLabel = new JLabel("", SwingConstants.CENTER);
            gridLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            gridLabel.setPreferredSize(new Dimension(80, 80));
            gridLabel.setOpaque(true);
            gridLabel.setBackground(Color.LIGHT_GRAY);
            gridLabel.setTransferHandler(new TransferHandler("text"));
            gridLabels.put("label" + i, gridLabel);
            gridPanel.add(gridLabel);
        }

        String[] vowels = {"A", "E", "I", "O", "U"};
        for (String vowel : vowels) {
            JLabel dragLabel = new JLabel(vowel, SwingConstants.CENTER);
            dragLabel.setFont(new Font("Serif", Font.BOLD, 24));
            dragLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            dragLabel.setOpaque(true);
            dragLabel.setBackground(Color.WHITE);
            dragLabel.setPreferredSize(new Dimension(80, 80));
            dragLabel.setTransferHandler(new TransferHandler("text"));
            dragLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JComponent c = (JComponent) e.getSource();
                    TransferHandler handler = c.getTransferHandler();
                    handler.exportAsDrag(c, e, TransferHandler.COPY);
                    playSound(vowelSounds.get(vowel)); // Reproducir sonido
                }
            });
            dragPanel.add(dragLabel);
        }

        // Botón de validación
        JButton validateButton = new JButton("Validar Orden");
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateOrder();
            }
        });

        add(dragPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        add(validateButton, BorderLayout.SOUTH);
    }

    private void validateOrder() {
        String[] correctOrder = {"A", "E", "I", "O", "U"};
        boolean isCorrect = true;
        for (int i = 0; i < correctOrder.length; i++) {
            JLabel label = gridLabels.get("label" + i);
            if (label.getText().isEmpty() || !label.getText().equals(correctOrder[i])) {
                isCorrect = false;
                break;
            }
        }

        if (isCorrect) {
            JOptionPane.showMessageDialog(this, "¡Correcto! Las vocales están en el orden correcto.");
            parentPanel.showMinigame("Complete");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrecto.");
            parentPanel.showMinigame("Complete");
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
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}