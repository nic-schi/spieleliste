package liste;

import data.Spiel;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

/**
 *
 * Verwaltet die Vorschau des gegebenen Spiels.
 *
 * @author Nichlas
 * @author Steeve
 * @author Jan-Tilos
 */

public class ListeVorschau extends JPanel {

    // Felder
    private JTextField nameFeld = new JTextField();
    private JCheckBox durchgespieltFeld = new JCheckBox("Durchgespielt");
    private JSpinner stundenFeld = new JSpinner();
    private JSpinner bewertungsFeld = new JSpinner();

    public ListeVorschau() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5, 15, 5, 15));

        // Formatiert die Felder
        formatiereFelder();

        add(new JLabel("Aktuelles Spiel"));
        // Füge felder hinzu
        add(nameFeld);
        add(durchgespieltFeld);
        add(stundenFeld);
        add(bewertungsFeld);
    }

    // Formatiert die Felder
    private void formatiereFelder() {
        // Stunden (int)
        // Setzt das Maximum, Minimum und die Stepsize des Feldes
        stundenFeld.setModel(new SpinnerNumberModel(/* VALUE */0, /* MIN */ 0, /* MAX */ Integer.MAX_VALUE, /* STEPSIZE */ 10));

        // Bewertung (double)
        // Setzt das Maximum, Minimum und die Stepsize des Feldes
        bewertungsFeld.setModel(new SpinnerNumberModel(/* VALUE */0, /* MIN */ 0, /* MAX */ 10, /* STEPSIZE */ 0.10));
    }

    // Stellt das Spiel in der Vorschau dar
    public void zeige(Spiel spiel) {
        // Setze die daten auf die Felder
        nameFeld.setText(spiel.getName());
        durchgespieltFeld.setSelected(spiel.isDurchgespielt());
        stundenFeld.setValue(spiel.getStunden());
        bewertungsFeld.setValue(spiel.getBewertung());

        // Setzt die UI der Vorschau zurück
        // Sorgt dafür, dass die Inhalte perfekt in die Vorschau passen (z.B. Breite)
        this.updateUI();
    }

}
