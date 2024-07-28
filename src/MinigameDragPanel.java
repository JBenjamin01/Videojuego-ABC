import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MinigameDragPanel extends JPanel {
    private MinigamesPanel parentPanel; // Referencia al panel de minijuegos
    private JPanel gridPanel;
    private JPanel dragPanel;
    private Map<String, JLabel> gridLabels;

    public MinigameDragPanel(MinigamesPanel parentPanel) {
        this.parentPanel = parentPanel; // Asignar la referencia

        setLayout(new BorderLayout());

        // Panel para las vocales
        dragPanel = new JPanel();
        dragPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        dragPanel.setBorder(BorderFactory.createTitledBorder("Vocales"));

        // Panel para la cuadrícula
        gridPanel = new JPanel(new GridLayout(1, 5, 20, 20));
        gridPanel.setBorder(BorderFactory.createTitledBorder("Arrastra las vocales aquí"));

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
                }
            });
            dragPanel.add(dragLabel);
        }

        add(dragPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}