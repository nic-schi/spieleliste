package menubar.menus;

import data.dao.SpieleDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;
import listener.SpieleWindowAdapter;
import menubar.SpieleMenuBar;
import spieleliste.Spieleliste;

/**
 *
 * Stellt den Datei-Menüpunkt dar.
 *
 * @author Steeve
 * @author Nichlas
 * @author Jan-Tilo
 */
public class DateiMenu extends JMenu implements ActionListener {

    private Spieleliste frame;

    // Menu items
    private JMenuItem newFile = new JMenuItem("Neu");
    private JMenuItem openFile = new JMenuItem("Öffnen");
    private JMenuItem saveFile = new JMenuItem("Speichern als...");
    private JMenuItem exitFile = new JMenuItem("Beenden");

    public DateiMenu(Spieleliste frame) {
        super("Datei");
        this.frame = frame;

        // Ermöglicht pressen von "ALT + das jeweillige Zeichen" zum selektieren
        this.setMnemonic('D');
        newFile.setMnemonic('N');
        exitFile.setMnemonic('B');
        openFile.setMnemonic('Ö');
        saveFile.setMnemonic('S');

        this.setBorder(SpieleMenuBar.border);
        newFile.setBorder(SpieleMenuBar.border);
        exitFile.setBorder(SpieleMenuBar.border);
        openFile.setBorder(SpieleMenuBar.border);
        saveFile.setBorder(SpieleMenuBar.border);

        newFile.addActionListener(this);
        exitFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        this.add(newFile);
        this.add(openFile);
        this.add(saveFile);
        this.add(new JSeparator());
        this.add(exitFile);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Filterung der Dateiendungen die ausgewählt werden können und akzeptiert werden
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Datenbank .db", "db");

        // Exit
        if (e.getSource().equals(exitFile)) {

            // Rufe den WindowAdapter auf und schließe das Fenster
            new SpieleWindowAdapter(frame).windowClosing(null);

        } else if (e.getSource().equals(newFile)) {
            // Neue Datei

            // Erstelle den FileChooser
            JFileChooser fileChooser = new JFileChooser();

            // Setze Filter und Titel
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogTitle("Neue Datei");

            // Zeige Speicherdialog
            int input = fileChooser.showSaveDialog(frame);

            // Wenn die Filter akzeptiert werden, dann führe fort
            if (input == JFileChooser.APPROVE_OPTION) {

                // Zeige Dialog
                input = JOptionPane.showConfirmDialog(frame, "Es könnten evtl. Daten verloren gehen.", "Sind Sie Sicher?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (input == JOptionPane.YES_OPTION) {
                    // Holt die vom Benutzer ausgewählte Datei
                    File file = fileChooser.getSelectedFile();

                    // Holt den Pfad der Datei
                    String newPath = file.getAbsolutePath();

                    // Füge Dateiendung hinzu, wenn nicht vorhanden
                    if (!newPath.endsWith(".db")) {
                        newPath = newPath.concat(".db");
                    }

                    // Initialisiere neues Anwendungsfenster
                    new Spieleliste(newPath);

                    // Schließen des aktuellen Fensters
                    frame.setVisible(false);
                    frame = null;
                }
            }
        } else if (e.getSource().equals(openFile)) {
            // Öffne Datei

            // Erstelle den FileChooser
            JFileChooser fileChooser = new JFileChooser();

            // Setze Filter und Titel
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogTitle("Datei Öffnen");

            // Zeige Öffnungsdialog
            int input = fileChooser.showOpenDialog(frame);

            // Wenn die Filter akzeptiert werden, dann führe fort
            if (input == JFileChooser.APPROVE_OPTION) {

                // Zeige Dialog
                input = JOptionPane.showConfirmDialog(frame, "Es könnten evtl. Daten verloren gehen.", "Sind Sie Sicher?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (input == JOptionPane.YES_OPTION) {
                    // Holt die vom Benutzer ausgewählte Datei
                    File file = fileChooser.getSelectedFile();

                    // Füge Dateiendung hinzu, wenn nicht vorhanden
                    String newPath = file.getAbsolutePath();
                    if (!newPath.endsWith(".db")) {
                        newPath = newPath.concat(".db");
                    }

                    // Initialisiere neues Anwendungsfenster
                    new Spieleliste(newPath);

                    // Schließen des aktuellen Fensters
                    frame.setVisible(false);
                    frame = null;
                }
            }
        } else if (e.getSource().equals(saveFile)) {
            // Speichern der Datei

            // Erstelle den FileChooser
            JFileChooser fileChooser = new JFileChooser();

            // Setze Filter und Titel
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogTitle("Datei speichern");

            // Zeige Speicherdialog
            int input = fileChooser.showSaveDialog(frame);

            // Wenn die Filter akzeptiert werden, dann führe fort
            if (input == JFileChooser.APPROVE_OPTION) {
                // Holt die vom Benutzer ausgewählte Datei
                File file = fileChooser.getSelectedFile();

                // Füge Dateiendung hinzu, wenn nicht vorhanden
                String newPath = file.getAbsolutePath();
                if (!newPath.endsWith(".db")) {
                    newPath = newPath.concat(".db");
                }

                try {
                    // Erstellt die Datei, falls diese noch nicht vorhanden ist
                    File savedfile = new File(newPath);

                    int returned = JOptionPane.YES_OPTION;
                    if (savedfile.exists()) {
                        // gibt einen Dialog aus, der aktuell YES und NO anzeigt.
                        returned = JOptionPane.showConfirmDialog(frame, "Die Datei ist bereits vorhanden!\nMöchten Sie die Datei überschreiben?", "Sind Sie Sicher?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    }
                    savedfile.createNewFile();

                    // Prüfung der Auswahl
                    if (returned == JOptionPane.YES_OPTION) {
                        // Hole Outputstream
                        DataOutputStream out = Spieleliste.getOutputStream(newPath);

                        // Inititalisiere neues SpieleDAO objekt. Dabei ist der InputStream null
                        SpieleDAO spieleDAO = new SpieleDAO(null, out);
                        spieleDAO.write(frame.getSpiele());

                        // Ausgabe
                        System.out.println("Speichere Datei: \"" + Spieleliste.FILE_PATH + "\"" + "\t-->\t" + frame.getSpiele().getAll().size() + " Spiele gespeichert!");

                        out.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DateiMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
