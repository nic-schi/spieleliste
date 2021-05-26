package liste;

import panels.FormularFeld;
import data.Spiel;
import data.Spiele;
import data.dao.SpieleDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
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
 * <br>Beeinhaltet anonyme Klasse: ButtonPanel
 *
 * @author Nichlas
 * @author Jan-Tilo
 * @author Steeve
 */
public class ListeVorschau extends JPanel {

    /**
     * Verwaltet die Knöpfe auf der Listenvorschau.
     * <br>Sorgt auch für die Logik hinter der Speicherung, Löschung und neuen
     * Erstellung.
     *
     * @author Nichlas
     * @author Steeve
     * @author Jan-Tilo
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
            // Speichern
            if (e.getSource().equals(speicherButton)) {
                // Holt sich die Werte aus dem Formular
                // ist null, sollte der Benutzer falsche Eingaben getätigt haben
                Spiel spiel = getFelderWerte(new Spiel());

                if (spiel != null) {
                    // guck nach, ob das Spiel bereits vorhanden ist
                    if (frame.getSpiele().hatSpiel(spiel.getName()) && !spiel.getName().equalsIgnoreCase(aktuellesSpiel.getName())) {
                        nameFeld.setBorder(ListeVorschau.this.errorBorder);

                        // gibt einen Dialog aus, der aktuell OK und CANCEL anzeigt.
                        JOptionPane.showConfirmDialog(frame, "Dieses Spiel ist bereits vorhanden", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Lädt die Formularwerte in das aktuelle Spiel ein
                        getFelderWerte(aktuellesSpiel);

                        // Speichern der Spiele
                        speichern();

                        System.out.println("Spiel gespeichert \t-->\t " + aktuellesSpiel);
                    }
                }
            } else if (e.getSource().equals(löschenButton)) {
                // Löschen

                // gibt einen Dialog aus, der aktuell YES und NO anzeigt.
                int input = JOptionPane.showConfirmDialog(frame, ""
                        + "Wollen Sie das Ausgewählte Spiel wirklich Löschen?\n"
                        + "\n--    \"" + aktuellesSpiel + "\"\n\n",
                        "Sind Sie Sicher?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                // Prüfung der Auswahl
                if (input == JOptionPane.YES_OPTION) {
                    // Hole alle Spiele und entferne das aktuelle Spiel
                    Spiele spiele = frame.getSpiele();
                    spiele.remove(aktuellesSpiel);

                    // Speichern der Spiele
                    speichern();

                    System.out.println("Spiel gelöscht \t-->\t " + aktuellesSpiel);
                }
            } else if (e.getSource().equals(neuButton)) {
                // Neues Spiel

                // Hole die Felderwerte in ein Temporäres Spiel
                // ist null, sollte der Benutzer falsche Eingaben getätigt haben
                Spiel spiel = getFelderWerte(new Spiel());

                if (spiel != null) {
                    // guck nach, ob das Spiel bereits vorhanden ist
                    if (frame.getSpiele().hatSpiel(spiel.getName())) {
                        nameFeld.setBorder(ListeVorschau.this.errorBorder);

                        // gibt einen Dialog aus, der aktuell OK und CANCEL anzeigt.
                        JOptionPane.showConfirmDialog(frame, "Dieses Spiel ist bereits vorhanden", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Hole alle spiele und füge das neue hinzu
                        Spiele spiele = frame.getSpiele();
                        spiele.add(spiel);

                        // Speichern der Spiele
                        speichern();

                        System.out.println("Neues Spiel \t-->\t " + spiel);
                    }
                }
            }
        }

        // Speichert die Spiele ab
        public void speichern() {
            try {
                // Hole Outputstream
                DataOutputStream out = Spieleliste.getOutputStream(Spieleliste.FILE_PATH);

                // Inititalisiere neues SpieleDAO objekt. Dabei ist der InputStream null
                SpieleDAO spieleDAO = new SpieleDAO(null, out);
                spieleDAO.write(frame.getSpiele());

                out.close();

                // Liste aktualisieren
                frame.getListe().aktualisieren();

                // Setzt das Formular auf die Standardwerte zurück
                ListeVorschau.this.zurücksetzen();
            } catch (Exception ex) {
                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    } // ENDE ButtonPanel

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
    private Border errorBorder = new LineBorder(this.errorFarbe, this.thickness);

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
    // ist null, wenn der Benutzer falsche Werte eingegeben hat
    public Spiel getFelderWerte(Spiel spiel) {
        // Prüft auf Validität
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

    // Prüft ob die eingaben in den Formularfeldern valide sind und gibt je nachdem fehlermeldungen aus
    public boolean istValide() {
        // Bezeichnung
        String bezeichnung = nameFeld.getText();

        // Bezeichnung länger als 150 Zeichen
        if (bezeichnung.length() > 150) {
            nameFeld.setBorder(this.errorBorder);

            JOptionPane.showConfirmDialog(frame, "Die Bezeichnung darf maximal 75 Zeichen betragen.", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            return false;
        } else if (bezeichnung.isEmpty()) {
            // Bezeichnung ist leer
            nameFeld.setBorder(this.errorBorder);

            JOptionPane.showConfirmDialog(frame, "Die Bezeichnung darf nicht leer sein.", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            return false;
        }

        // Stunden
        int stunden = (int) stundenFeld.getValue();

        if (stunden < 0) {
            // Stunden sind negativ
            stundenFeld.setBorder(this.errorBorder);

            JOptionPane.showConfirmDialog(frame, "Die Stunden können nicht negativ sein.", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            return false;
        }

        // Bewertung
        double bewertung = (double) bewertungsFeld.getValue();

        if (bewertung < 0) {
            // Bewertung ist negativ
            bewertungsFeld.setBorder(this.errorBorder);

            JOptionPane.showConfirmDialog(frame, "Die Bewertung kann nicht negativ sein.", "Falsche Eingabe!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            return false;
        }

        // Hat die Prüfung überlebt = true
        return true;
    }

    // Formatiert die Formularfelder
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
    public void zurücksetzen() {
        // Inhalte der Felder
        nameFeld.setText("");
        durchgespieltFeld.setSelected(false);
        stundenFeld.setValue(0);
        bewertungsFeld.setValue(0.00);

        // Border auf Standard
        nameFeld.setBorder(new JTextField().getBorder());
        stundenFeld.setBorder(new JSpinner().getBorder());
        bewertungsFeld.setBorder(new JSpinner().getBorder());

        // Deaktiviert die Speicher und Löschen Knöpfe
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

        // Setze das aktuelle Spiel
        this.aktuellesSpiel = spiel;

        // Aktiviert die Speicher und Löschen Knöpfe
        this.buttonPanel.speicherButton.setEnabled(true);
        this.buttonPanel.löschenButton.setEnabled(true);

        // Malt die Vorschau neu
        // Sorgt dafür, dass die Breite der Vorschau sich dem Inhalt anpasst.
        this.updateUI();
    }

}
