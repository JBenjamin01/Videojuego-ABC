import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.IOException;

public class CharacterSelectionPanel extends JPanel {
    private Image fondo;

    public CharacterSelectionPanel(Game game) {
        
        fondo = new ImageIcon(getClass().getResource("imagenes/fondo.jpg")).getImage();

        // botones de personaje
        try {
            this.add(createCharacterButton(game, "imagenes/stitch.png", "Stitch"));
            this.add(createCharacterButton(game, "imagenes/pooh.png", "Pooh"));
            this.add(createCharacterButton(game, "imagenes/dalmata.png", "Dalmata"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JButton createCharacterButton(Game game, String imagePath, String characterName) throws IOException {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // tamaño de la imagen 
        JButton button = new JButton(new ImageIcon(image));
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

