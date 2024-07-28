import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MenuPanel extends JPanel {

    private Image fondo;

    public MenuPanel(Game game) {
        fondo = new ImageIcon(getClass().getResource("imagenes/fondo.jpg")).getImage();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(1, 0, 1, 0); // Espaciado entre los botones

        // Configuración para que los botones estén en el centro y un poco más abajo
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;

        // Tamaño deseado para los botones
        int buttonWidth = 180;
        int buttonHeight = 80;

        // Botón de inicio
        JButton startButton = new JButton(createResizedIcon("imagenes/iniciar.png", buttonWidth, buttonHeight));
        startButton.setBackground(new Color(0, 0, 0, 0));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.showPanel("CharacterSelection");
            }
        });
        gbc.gridy = 1;
        this.add(startButton, gbc);

        // Botón de créditos
        JButton creditsButton = new JButton(createResizedIcon("imagenes/creditos.png", buttonWidth, buttonHeight));
        creditsButton.setBackground(new Color(0, 0, 0, 0));
        creditsButton.setBorderPainted(false);
        creditsButton.setContentAreaFilled(false);
        creditsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.showPanel("Credits");
            }
        });
        gbc.gridy = 2;
        this.add(creditsButton, gbc);

        // Botón de salir
        JButton exitButton = new JButton(createResizedIcon("imagenes/salir.png", buttonWidth, buttonHeight));
        exitButton.setBackground(new Color(0, 0, 0, 0));
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbc.gridy = 3;
        this.add(exitButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    private ImageIcon createResizedIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(path));
        Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}