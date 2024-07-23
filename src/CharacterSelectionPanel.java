import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterSelectionPanel extends JPanel {
    public CharacterSelectionPanel(Game game) {
        JButton selectButton = new JButton("Select Character");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.showPanel("VowelLearning");
            }
        });
        this.add(selectButton);
    }
}