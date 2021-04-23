package liste;

import data.Spiele;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import listener.SpieleListeListener;

/**
 * Stellt die Liste dar.<br>
 * Hier werden alle Spiele aufgelistet
 *
 * @author Nichlas
 * @author Steeve
 * @author Jan-Tilo
 */
public class Liste extends JPanel {

    private DefaultListModel dataModel;
    private JList liste;
    private Spiele spiele;

    public Liste(Spiele spiele) {
        this.spiele = spiele;
        this.dataModel = new DefaultListModel();
        this.liste = new JList(dataModel);

        // Liste aktualisieren
        refresh();

        // Setze den Selectionlistener
        liste.addListSelectionListener(new SpieleListeListener());

        // Zeigt mindestens x Spiele in der Liste an
        liste.setVisibleRowCount(12);
        // Setzt das Selektionsmodell auf Einzelselektierung
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Setzt die Hintergrundfarbe
        liste.setSelectionBackground(new Color(146, 159, 238));
        // Setzt die Größe der einzelnen Zellen
        liste.setFixedCellHeight(25);

        // Fügt ein BorderLayout hinzu
        setLayout(new BorderLayout());

        // Platziert die Komponenten auf die jeweilligen BorderLayout stellen
        add(new JLabel("Alle aktuellen Spiele"), BorderLayout.NORTH);
        add(new JScrollPane(liste), BorderLayout.CENTER);
    }

    // Aktualisiert die Liste
    public void refresh() {
        // Löschen der Spiele in dem Datamodel
        dataModel.clear();
        // Fügt alle Spiele hinzu
        dataModel.addAll(this.spiele.getAll());
        // Setzt das Model (aktualisiert auch zeitgleich die Liste)
        liste.setModel(dataModel);
    }

    // Gibt die JList zurück
    public JList getListe() {
        return this.liste;
    }

    // Gibt das ListDataModel zurück
    public DefaultListModel getModel() {
        return this.dataModel;
    }

}
