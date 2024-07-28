import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

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

        // Contenedor vertical para evitar el estiramiento vertical de los paneles
        JPanel verticalContainer = new JPanel();
        verticalContainer.setLayout(new BoxLayout(verticalContainer, BoxLayout.Y_AXIS));
        verticalContainer.setOpaque(false);

        // Panel para envolver el panel de las vocales
        JPanel dragPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        dragPanelContainer.setOpaque(false);

        // Panel para envolver el panel de la cuadrícula
        JPanel gridPanelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        gridPanelContainer.setOpaque(false);

        // Panel para las vocales
        dragPanel = new JPanel();
        dragPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        dragPanel.setOpaque(false);
        dragPanel.setPreferredSize(new Dimension(500, 100));

        // Panel para la cuadrícula
        gridPanel = new JPanel(new GridLayout(1, 5, 20, 20));
        gridPanel.setOpaque(false);
        gridPanel.setPreferredSize(new Dimension(750, 150));

        gridLabels = new HashMap<>();
        Dimension labelSize = new Dimension(50, 160);
        Font labelFont = new Font("Serif", Font.BOLD, 40);

        for (int i = 0; i < 5; i++) {
            JLabel gridLabel = new JLabel("", SwingConstants.CENTER);
            gridLabel.setFont(labelFont);
            gridLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            gridLabel.setPreferredSize(labelSize);
            gridLabel.setOpaque(true);
            gridLabel.setBackground(new Color(230, 230, 230));
            gridLabel.setTransferHandler(new TransferHandler("text"));
            gridLabels.put("label" + i, gridLabel);
            gridPanel.add(gridLabel);
        }

        java.util.List<String> vowels = Arrays.asList("A", "E", "I", "O", "U");
        Collections.shuffle(vowels);

        for (String vowel : vowels) {
            JLabel dragLabel = new JLabel(vowel, SwingConstants.CENTER);
            dragLabel.setFont(new Font("Serif", Font.BOLD, 24));
            dragLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
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

        JButton validateButton = new JButton(new ImageIcon(getClass().getResource("/imagenes/siguiente.png")));
        validateButton.setBorderPainted(false);
        validateButton.setContentAreaFilled(false);
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateOrder();
            }
        });

        dragPanelContainer.add(dragPanel);
        gridPanelContainer.add(gridPanel);

        verticalContainer.add(Box.createVerticalGlue());
        verticalContainer.add(dragPanelContainer);
        verticalContainer.add(gridPanelContainer);
        verticalContainer.add(Box.createVerticalGlue());

        add(verticalContainer, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        buttonPanel.add(validateButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void validateOrder() {
        String[] correctOrder = {"A", "E", "I", "O", "U"};
        int score = 0;
    
        for (int i = 0; i < correctOrder.length; i++) {
            JLabel label = gridLabels.get("label" + i);
            if (!label.getText().isEmpty() && label.getText().equals(correctOrder[i])) {
                score++;
            }
        }

        ScoreManager.getInstance().increaseScore(score);
    
        JOptionPane.showMessageDialog(this, "Puntuación: " + score + " puntos.");
        parentPanel.showMinigame("Paint");
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