package panels;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * Stellt ein JLabel in einem eigenem JPanel dar.
 *
 * @author Jan-Tilo
 * @author Nichlas
 * @author Steeve
 */
public class LabelPanel extends JPanel {

    public LabelPanel(String text) {
        JLabel label = new JLabel(text);

        // Farben
        setBackground(new Color(0, 0, 0));
        label.setForeground(Color.LIGHT_GRAY);

        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        add(label);
    }

}
