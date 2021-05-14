
package listener;

import data.Spiel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import liste.ListeVorschau;

/**
 * Veränderungen bei der Listenselektion werden abgefangen.
 *
 * @author Nichlas
 * @author Steeve
 * @author Jan-Tilo
 */
public class SpieleListeListener implements ListSelectionListener {

    private ListeVorschau listeVorschau;
    
    public SpieleListeListener(ListeVorschau listeVorschau) {
        this.listeVorschau = listeVorschau;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Dass Event findet auf der Liste statt, sodass e.getSource() die Liste zurückgibt
        JList liste = (JList) e.getSource();
        // Das Selektierte Item der Liste ist vom Typ Spiel
        Spiel spiel = (Spiel) liste.getSelectedValue();

        if (spiel != null) {
            listeVorschau.zeige(spiel);
        }
    }

}
