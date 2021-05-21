package panels;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jan-Tilo
 * @author Nichlas
 * @author Steeve
 */
public class LabelPanel extends JPanel {

    public LabelPanel(String text) {
        JLabel label = new JLabel(text);

        setBackground(Color.DARK_GRAY);
        label.setForeground(Color.LIGHT_GRAY);

        add(label);
    }

}
