import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameEntryPanel extends JPanel {
    private Image fondo;
    private JTextField nameField;
    private JButton startButton;
    private Game game;
    private JDialog dialog;

    public NameEntryPanel(Game game) {
        this.game = game;
        fondo = new ImageIcon(getClass().getResource("/imagenes/menu.gif")).getImage();
        setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints inputGbc = new GridBagConstraints();
        inputGbc.insets = new Insets(5, 5, 20, 5);
        inputGbc.anchor = GridBagConstraints.CENTER;

        JLabel nameLabel = new JLabel("Ingresa tu nombre:");
        nameLabel.setFont(new Font("Cooper Black", Font.PLAIN, 70));
        nameLabel.setForeground(Color.BLACK);
        inputGbc.gridx = 0;
        inputGbc.gridy = 0;
        inputPanel.add(nameLabel, inputGbc);

        nameField = new JTextField(30);
        nameField.setFont(new Font("Serif", Font.PLAIN, 45));
        inputGbc.gridy = 1;
        inputPanel.add(nameField, inputGbc);

        startButton = new JButton("Empezar");
        startButton.setFont(new Font("Serif", Font.PLAIN, 40));
        startButton.setFocusPainted(false);
        startButton.setBackground(new Color(0, 102, 204));
        startButton.setForeground(Color.WHITE);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
        startButton.setBorder(BorderFactory.createCompoundBorder(border, raisedBevelBorder));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText().trim();
                if (!playerName.isEmpty()) {
                    game.setPlayerName(playerName);
                    if (dialog != null) {
                        dialog.dispose();
                    }
                    game.showPanel("CharacterSelection");
                } else {
                    JOptionPane.showMessageDialog(NameEntryPanel.this, "Por favor ingresa un nombre.", "Nombre requerido", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        inputGbc.gridy = 2;
        inputPanel.add(startButton, inputGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(inputPanel, gbc);
    }

    public void setDialog(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
