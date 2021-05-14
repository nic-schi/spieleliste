package liste;

import data.Spiel;
import data.Spiele;
import data.dao.SpieleDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import spieleliste.Spieleliste;

/**
 *
 * Verwaltet die Vorschau des gegebenen Spiels.
 *
 * @author Nichlas
 * @author Jan-Tilos
 * @author Steeve
 */
public class ListeVorschau extends JPanel {

    public class ButtonPanel extends JPanel implements ActionListener {

        private JButton speichern = new JButton("Speichern");
        private JButton löschen = new JButton("Löschen");
        private JButton neu = new JButton("Neu");
        private Spieleliste frame;

        public ButtonPanel(Spieleliste frame) {
            this.frame = frame;

            this.speichern.setEnabled(false);
            this.speichern.addActionListener(this);

            this.löschen.addActionListener(this);
            this.löschen.setEnabled(false);

            this.neu.addActionListener(this);

            add(speichern);
            add(löschen);
            add(neu);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(speichern)) {
                aktuellesSpiel.setName(nameFeld.getText());
                aktuellesSpiel.setDurchgespielt(durchgespieltFeld.isSelected());
                aktuellesSpiel.setBewertung((double) bewertungsFeld.getValue());
                aktuellesSpiel.setStunden((int) stundenFeld.getValue());

                System.out.println("Spiel gespeichert \t-->\t " + aktuellesSpiel);

                speichern();
                frame.getListe().aktualisieren();
                reset();
            } else if (e.getSource().equals(löschen)) {
                Spiele spiele = frame.getSpiele();
                spiele.remove(aktuellesSpiel);

                System.out.println("Spiel gelöscht \t-->\t " + aktuellesSpiel);

                speichern();
                frame.getListe().aktualisieren();
                reset();
            } else if (e.getSource().equals(neu)) {
                Spiel spiel = new Spiel();

                spiel.setName(nameFeld.getText());
                spiel.setDurchgespielt(durchgespieltFeld.isSelected());
                spiel.setBewertung((double) bewertungsFeld.getValue());
                spiel.setStunden((int) stundenFeld.getValue());

                Spiele spiele = frame.getSpiele();
                spiele.add(spiel);

                System.out.println("Neues Spiel \t-->\t " + spiel);

                speichern();
                frame.getListe().aktualisieren();
                reset();
            }
        }

        public void speichern() {
            try {
                DataOutputStream out = Spieleliste.getOutputStream(Spieleliste.FILE_PATH);
                // Inititalisiere neues SpieleDAO objekt. Dabei ist der Outputstream null, da wir die Datei nicht leeren wollen
                SpieleDAO spieleDAO = new SpieleDAO(null, out);
                spieleDAO.write(frame.getSpiele());

                out.close();
                System.out.println(frame.getSpiele().getAll().size() + " Spiele gespeichert!");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    // Felder
    private JTextField nameFeld = new JTextField();
    private JCheckBox durchgespieltFeld = new JCheckBox("Durchgespielt");
    private JSpinner stundenFeld = new JSpinner();
    private JSpinner bewertungsFeld = new JSpinner();

    private Spiel aktuellesSpiel;
    private ButtonPanel buttonPanel;

    public ListeVorschau(Spieleliste frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5, 15, 5, 15));

        // Formatiert die Felder
        formatiereFelder();

        add(new JLabel("Aktuelles Spiel"));
        // Füge felder hinzu
        add(new FormularFeld("Bezeichnung", nameFeld));
        add(durchgespieltFeld);
        add(new FormularFeld("Stunden", stundenFeld));
        add(new FormularFeld("Bewertung", bewertungsFeld));

        this.buttonPanel = new ButtonPanel(frame);
        add(buttonPanel);
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

    public void reset() {
        nameFeld.setText("");
        durchgespieltFeld.setSelected(false);
        stundenFeld.setValue(0);
        bewertungsFeld.setValue(0.00);

        this.buttonPanel.speichern.setEnabled(false);
        this.buttonPanel.löschen.setEnabled(false);
    }
    
    // Stellt das Spiel in der Vorschau dar
    public void zeige(Spiel spiel) {
        // Setze die daten auf die Felder
        nameFeld.setText(spiel.getName());
        durchgespieltFeld.setSelected(spiel.isDurchgespielt());
        stundenFeld.setValue(spiel.getStunden());
        bewertungsFeld.setValue(spiel.getBewertung());

        this.aktuellesSpiel = spiel;
        this.buttonPanel.speichern.setEnabled(true);
        this.buttonPanel.löschen.setEnabled(true);

        // Setzt die UI der Vorschau zurück
        // Sorgt dafür, dass die Inhalte perfekt in die Vorschau passen (z.B. Breite)
        this.updateUI();
    }

}
