import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameEntryPanel extends JPanel {
    private Image fondo;
    private JTextField nameField;
    private JButton startButton;
    private Game game;

    public NameEntryPanel(Game game) {
        this.game = game;
        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")).getImage();
        setLayout(new BorderLayout());
        setOpaque(false); // Hacer el panel transparente para mostrar el fondo

        // Panel para la entrada del nombre
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Ingresa tu nombre:");
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        nameLabel.setForeground(Color.WHITE);
        inputPanel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setFont(new Font("Serif", Font.PLAIN, 20));
        inputPanel.add(nameField);

        startButton = new JButton("Empezar");
        startButton.setFont(new Font("Serif", Font.PLAIN, 20));
        startButton.setFocusPainted(false);
        startButton.setBackground(new Color(0, 102, 204));
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(BorderFactory.createRaisedBevelBorder());
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText().trim();
                if (!playerName.isEmpty()) {
                    // Guardar el nombre del jugador en el juego
                    game.setPlayerName(playerName);
                    // Mostrar el panel de menú
                    game.showPanel("Menu");
                } else {
                    JOptionPane.showMessageDialog(NameEntryPanel.this, "Por favor ingresa un nombre.", "Nombre requerido", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        inputPanel.add(startButton);

        add(inputPanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}