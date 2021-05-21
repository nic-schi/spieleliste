package panels;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * Verwaltet ein einzelnes Formularfeld
 *
 * @author Steeve
 * @author Nichlas
 * @author Jan-Tilo
 */
public class FormularFeld extends JPanel {

    public FormularFeld(String titel, JComponent feld) {
        JPanel labelPanel = new JPanel();
        labelPanel.add(new JLabel(titel));

        labelPanel.setBackground(Color.lightGray);

        setBorder(new EmptyBorder(0, 0, 15, 0));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(labelPanel);
        add(feld);
    }

}
