import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class CharacterSelectionPanel extends JPanel {
    private Image fondo;
    public CharacterSelectionPanel(Game game) {
        //Añado la imagen
        fondo = new ImageIcon(getClass().getResource("imagenes/fondo.jpg")).getImage();

        JButton selectButton = new JButton("Select Character");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.showPanel("VowelLearning");
            }
        });
        this.add(selectButton);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}