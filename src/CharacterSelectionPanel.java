import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.File;

public class CharacterSelectionPanel extends JPanel {
    private Image fondo;

    public CharacterSelectionPanel(Game game) {
        // Añado la imagen de fondo
        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg")).getImage();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);

        JLabel messageLabel = new JLabel("Elija un personaje");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Ajusta la fuente del mensaje
        messageLabel.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // centrar
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(messageLabel, gbc);

        gbc.gridwidth = 1; 
        gbc.gridy = 1;
        this.add(createCharacterButton(game, "imagenes/stitch.png", "Stitch"));
        gbc.gridy = 1;
        this.add(createCharacterButton(game, "imagenes/pooh.png", "Pooh"));
        gbc.gridy = 1;
        this.add(createCharacterButton(game, "imagenes/dalmata.png", "Dalmata"));
    }

    private JButton createCharacterButton(Game game, String imagePath, String characterName) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); 
        JButton button = new JButton(new ImageIcon(image));
        button.setPreferredSize(new java.awt.Dimension(160, 160));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.selectCharacter(characterName);
            }
        });
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}


