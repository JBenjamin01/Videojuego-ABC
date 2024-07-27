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
        // Añado la imagen de fondo
        fondo = new ImageIcon(getClass().getResource("imagenes/fondo.jpg")).getImage();

        // Botón para el primer personaje
        JButton characterButton1 = new JButton(new ImageIcon(getClass().getResource("imagenes/Figura1.png")));
        characterButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.selectCharacter("Figura1");
            }
        });
        this.add(characterButton1);

        // Botón para el segundo personaje
        JButton characterButton2 = new JButton(new ImageIcon(getClass().getResource("imagenes/Figura2.png")));
        characterButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.selectCharacter("Figura2");
            }
        });
        this.add(characterButton2);

        // Botón para el tercer personaje
        JButton characterButton3 = new JButton(new ImageIcon(getClass().getResource("imagenes/Figura3.png")));
        characterButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.selectCharacter("Figura3");
            }
        });
        this.add(characterButton3);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }
}
