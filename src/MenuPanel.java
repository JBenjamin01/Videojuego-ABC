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
import java.awt.Font;
import java.io.File;


public class MenuPanel extends JPanel {

    private Image fondo;

    public MenuPanel(Game game) {
        
        fondo = new ImageIcon(getClass().getResource("imagenes/fondo.jpg")).getImage();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        //boton de inicio
        JButton startButton = new JButton("Start Game");
        startButton.setBackground(new Color(255, 200, 0));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setPreferredSize(new java.awt.Dimension(200, 80));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.showPanel("CharacterSelection");
            }
        });
        this.add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}
