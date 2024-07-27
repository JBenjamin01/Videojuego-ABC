import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;


public class MenuPanel extends JPanel {

    private Image fondo;

    public MenuPanel(Game game) {
        //Añado la imagen
        fondo = new ImageIcon(getClass().getResource("imagenes/fondo.jpg")).getImage();

        //Creando el boton de inicio
        JButton startButton = new JButton("Start Game");
        startButton.setBackground(new Color(255, 220, 180));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.showPanel("Evaluation");
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
