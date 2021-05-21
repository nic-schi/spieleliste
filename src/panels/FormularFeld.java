package panels;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * Formt den mitgegebenen Text in ein Formularfeld mit Bezeichner um.
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

        // Füge die Komponenten hinzu
        add(labelPanel);
        add(feld);
    }

}
