package liste;

import panels.FormularFeld;
import data.Spiel;
import data.Spiele;
import data.dao.SpieleDAO;
import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import panels.LabelPanel;
import spieleliste.Spieleliste;

/**
 *
 * Verwaltet die Vorschau des gegebenen Spiels.
 *
 * @author Nichlas
 * @author Jan-Tilo
 * @author Steeve
 */

public class ListeVorschau extends JPanel {

    /**
     * Verwaltet die Knöpfe auf der ListeVorschau
     *
     * @author Nichlas
     * @author Jan-Tilo
     * @author Steeve
     */
    public class ButtonPanel extends JPanel implements ActionListener {

        // Alle Knöpfe
        private JButton speicherButton = new JButton("Speichern");
        private JButton löschenButton = new JButton("Löschen");
        private JButton neuButton = new JButton("Neu");

        public ButtonPanel() {
            // Speichern
            speicherButton.setEnabled(false);
            speicherButton.addActionListener(this);
            speicherButton.setToolTipText("Speichern des Ausgewählten Spiels");

            // Löschen
            löschenButton.addActionListener(this);
            löschenButton.setEnabled(false);
            löschenButton.setToolTipText("Ausgewähltes Spiel löschen");

            // Neu
            neuButton.addActionListener(this);
            neuButton.setToolTipText("Neues Spiel hinzufügen");

            // Hinzufügen der Knöpfe
            add(speicherButton);
            add(löschenButton);
            add(neuButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(speicherButton)) {
                // Speichern
                Spiel spiel = getFelderWerte(new Spiel());

                if (spiel != null) {
                    getFelderWerte(aktuellesSpiel);
                    speichern();

                    System.out.println("Spiel gespeichert \t-->\t " + aktuellesSpiel);
                }
            } else if (e.getSource().equals(löschenButton)) {
                // Löschen
                // gibt einen Dialog aus, der aktuell YES und NO anzeigt.
                int returned = JOptionPane.showConfirmDialog(frame, "Wollen Sie das Ausgewählte Spiel wirklich Löschen?\n\n--    \"" + aktuellesSpiel + "\"\n\n", "Sind Sie Sicher?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                // Prüfung der Auswahl
                if (returned == JOptionPane.YES_OPTION) {
                    Spiele spiele = frame.getSpiele();
                    spiele.remove(aktuellesSpiel);

                    speichern();

                    System.out.println("Spiel gelöscht \t-->\t " + aktuellesSpiel);
                }
            } else if (e.getSource().equals(neuButton)) {
                Spiel spiel = getFelderWerte(new Spiel());

                if (spiel != null) {
                    // guck ob das Spiel bereits vorhanden ist
                    if (aktuellesSpiel.getName().equalsIgnoreCase(spiel.getName())) {
                        // Spiel ist bereits vorhanden
                        nameFeld.setBorder(errorBorder);

                        // gibt einen Dialog aus, der aktuell OK und CANCEL anzeigt.
                        JOptionPane.showConfirmDialog(frame, "Dieses Spiel ist bereits vorhanden", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Spiel nicht vorhanden
                        Spiele spiele = frame.getSpiele();
                        spiele.add(spiel);

                        speichern();

                        System.out.println("Neues Spiel \t-->\t " + spiel);
                    }
                }
            }
        }

        // Speichert die Spiele ab
        public void speichern() {
            try {
                DataOutputStream out = Spieleliste.getOutputStream(Spieleliste.FILE_PATH);
                // Inititalisiere neues SpieleDAO objekt. Dabei ist der InputStream null
                SpieleDAO spieleDAO = new SpieleDAO(null, out);
                spieleDAO.write(frame.getSpiele());

                out.close();

                frame.getListe().aktualisieren();
                reset();

//                System.out.println(frame.getSpiele().getAll().size() + " Spiele gespeichert!");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } // ENDE ButtonPanel

    // Felder
    private Spieleliste frame;

    // Alle Formularfelder
    private JTextField nameFeld = new JTextField();
    private JCheckBox durchgespieltFeld = new JCheckBox("Durchgespielt");
    private JSpinner stundenFeld = new JSpinner();
    private JSpinner bewertungsFeld = new JSpinner();

    private ButtonPanel buttonPanel;
    private Spiel aktuellesSpiel;

    // Error
    private int thickness = 3;
    private Color errorFarbe = new Color(169, 98, 98);
    private Border errorBorder = new LineBorder(errorFarbe, thickness);

    public ListeVorschau(Spieleliste frame) {
        this.frame = frame;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5, 15, 5, 15));

        // Formatiert die Felder
        formatiereFelder();

        add(new LabelPanel("Aktuelles Spiel"));

        // Füge felder hinzu
        add(new FormularFeld("Bezeichnung", nameFeld));
        add(durchgespieltFeld);
        add(new FormularFeld("Stunden", stundenFeld));
        add(new FormularFeld("Bewertung", bewertungsFeld));

        this.buttonPanel = new ButtonPanel();
        add(this.buttonPanel);
    }

    // Holt den Inhalt der Formularfelder und speichert diese im mitgegebenen Spiel
    public Spiel getFelderWerte(Spiel spiel) {
        if (istValide()) {
            // Bezeichnung
            spiel.setName(nameFeld.getText());

            // Durchgespielt
            spiel.setDurchgespielt(durchgespieltFeld.isSelected());

            // Stunden
            spiel.setStunden((int) stundenFeld.getValue());

            // Bewertung
            spiel.setBewertung((double) bewertungsFeld.getValue());

            return spiel;
        }
        return null;
    }

    // Prüft ob die eingaben in den Formularfeldern valide sind und gibt jenachdem fehlermeldungen aus
    public boolean istValide() {
        LineBorder border = new LineBorder(errorFarbe, thickness);

        // Bezeichnung
        String bezeichnung = nameFeld.getText();

        if (bezeichnung.length() > 150) {
            // Bezeichnung länger als 150 Zeichen
            nameFeld.setBorder(border);

            JOptionPane.showConfirmDialog(frame, "Die Bezeichnung darf maximal 75 Zeichen betragen.", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (bezeichnung.isEmpty()) {
            // Bezeichnung ist leer
            nameFeld.setBorder(border);

            JOptionPane.showConfirmDialog(frame, "Die Bezeichnung darf nicht leer sein.", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Stunden
        int stunden = (int) stundenFeld.getValue();

        if (stunden < 0) {
            // Stunden sind negativ
            stundenFeld.setBorder(border);

            JOptionPane.showConfirmDialog(frame, "Die Stunden können nicht negativ sein.", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Bewertung
        double bewertung = (double) bewertungsFeld.getValue();

        if (bewertung < 0) {
            // Bewertung ist negativ
            bewertungsFeld.setBorder(border);

            JOptionPane.showConfirmDialog(frame, "Die Bewertung kann nicht negativ sein.", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    // Formatiert die Felder
    private void formatiereFelder() {
        // Stunden (int)
        // Setzt das Maximum, Minimum und die Stepsize des Feldes
        stundenFeld.setModel(new SpinnerNumberModel(/* VALUE */0, /* MIN */ 0, /* MAX */ Integer.MAX_VALUE, /* STEPSIZE */ 10));
        stundenFeld.setToolTipText("Stunden des Spiels");

        // Bewertung (double)
        // Setzt das Maximum, Minimum und die Stepsize des Feldes
        bewertungsFeld.setModel(new SpinnerNumberModel(/* VALUE */0, /* MIN */ 0, /* MAX */ 10, /* STEPSIZE */ 0.10));
        bewertungsFeld.setToolTipText("Bewertung des Spiels");

        // Durchgespielt
        durchgespieltFeld.setToolTipText("Haben Sie das Spiel durchgespielt?");
        durchgespieltFeld.setBorder(new EmptyBorder(0, 0, 15, 0));

        // Bezeichnung
        nameFeld.setToolTipText("Bezeichnung des Spiels");
    }

    // Setzt die Formularfelder zurück
    public void reset() {
        // Inhalte der Felder
        nameFeld.setText("");
        durchgespieltFeld.setSelected(false);
        stundenFeld.setValue(0);
        bewertungsFeld.setValue(0.00);

        // Border
        nameFeld.setBorder(new JTextField().getBorder());
        stundenFeld.setBorder(new JSpinner().getBorder());
        bewertungsFeld.setBorder(new JSpinner().getBorder());

        this.buttonPanel.speicherButton.setEnabled(false);
        this.buttonPanel.löschenButton.setEnabled(false);
    }

    // Stellt das Spiel in der Vorschau dar
    public void zeige(Spiel spiel) {
        // Setze die daten auf die Felder
        nameFeld.setText(spiel.getName());
        durchgespieltFeld.setSelected(spiel.isDurchgespielt());
        stundenFeld.setValue(spiel.getStunden());
        bewertungsFeld.setValue(spiel.getBewertung());

        // Setzte die Border auf Standard zurück
        nameFeld.setBorder(new JTextField().getBorder());
        stundenFeld.setBorder(new JSpinner().getBorder());
        bewertungsFeld.setBorder(new JSpinner().getBorder());

        // Speichert das aktuelle Spiel ab
        this.aktuellesSpiel = spiel;

        this.buttonPanel.speicherButton.setEnabled(true);
        this.buttonPanel.löschenButton.setEnabled(true);

        // Setzt die UI der Vorschau zurück
        // Sorgt dafür, dass die Inhalte perfekt in die Vorschau passen (z.B. Breite)
        this.updateUI();
    }

}
