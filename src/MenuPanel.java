import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {

    private ImageIcon fondo;
    private Image titulo;

    public MenuPanel(Game game) {
        fondo = new ImageIcon(getClass().getResource("imagenes/menu.gif"));
        titulo = new ImageIcon(getClass().getResource("imagenes/aeiou.png")).getImage();

        // Tamaño deseado para los botones
        int buttonWidth = 180;
        int buttonHeight = 80;

        // Tamaño deseado para la imagen de título
        int tituloWidth = 1000;  // Ajusta este valor al tamaño deseado
        int tituloHeight = 500; // Ajusta este valor al tamaño deseado

        // Botón de inicio
        JButton startButton = new JButton(createResizedIcon("imagenes/iniciar.png", buttonWidth, buttonHeight));
        startButton.setBackground(new Color(0, 0, 0, 0));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.showPanel("NameEntry");
            }
        });

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

        // Configuración del layout principal
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado alrededor de los componentes

        // Configuración del JLabel de imagen con tamaño ajustado
        ImageIcon resizedTituloIcon = createResizedIcon("imagenes/aeiou.png", tituloWidth, tituloHeight);
        JLabel image = new JLabel(resizedTituloIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(image, gbc);

        // Crear y configurar el panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Hacer transparente el panel de botones
        buttonPanel.add(startButton);
        buttonPanel.add(creditsButton);
        buttonPanel.add(exitButton);

        // Configuración del panel de botones
        gbc.gridy = 1;
        add(buttonPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    private ImageIcon createResizedIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(path));
        Image resizedImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}