
package listener;

import data.Spiel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Veränderungen bei der Listenselektion werden abgefangen.
 *
 * @author Nichlas
 * @author Steeve
 * @author Jan-Tilo
 */
public class SpieleListeListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Dass Event findet auf der Liste statt, sodass e.getSource() die Liste zurückgibt
        JList liste = (JList) e.getSource();
        // Das Selektierte Item der Liste ist vom Typ Spiel
        Spiel spiele = (Spiel) liste.getSelectedValue();

        // Ausgabe der Selektion
        System.out.println(spiele);
    }

}
