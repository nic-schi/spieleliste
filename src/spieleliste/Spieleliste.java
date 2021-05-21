package spieleliste;

import data.Spiel;
import data.Spiele;
import data.dao.SpieleDAO;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import liste.Liste;
import liste.ListeVorschau;
import listener.SpieleWindowAdapter;
import menubar.SpieleMenuBar;

/**
 * Hauptklasse.<br>
 * Baut das Anwendungsfenster auf.
 *
 * @author Nichlas
 * @author Jan-Tilo
 * @author Steeve
 */
public class Spieleliste extends JFrame {

    public static Spieleliste spieleListe;

    private Spiele spiele = new Spiele();
    private Liste listePanel;

    // Standard Datenbankpfad
    public static String FILE_PATH = "./data/datenbank.db";

    public Spieleliste(String filePath) {
        // Setzt den Datenbankpfad
        Spieleliste.FILE_PATH = filePath;

        setTitle("Spieleliste");

        // Setzt die Größe des Fenster auf minimum x, y
        setMinimumSize(new Dimension(600, 500));

        // Setzt das Fenster in die Mitte
        setLocationRelativeTo(null);

        // Soll standardmäßig nichts beim schließen tun.
        // Das tatsächliche schließen wird durch den SpieleWindowAdapter implementiert
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Stellt das Menu
        setJMenuBar(new SpieleMenuBar(this));

        // Sorgt dafür, dass das Fenster auch geschlossen wird.
        addWindowListener(new SpieleWindowAdapter(this));

        try {
            // Erstelle und Lade die Datenbankdatei
            erstelleDatei();
            ladeDatei();
        } catch (IOException ex) {
            Logger.getLogger(Spieleliste.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Erstellt Beispieldaten zum Testen
//        erstelleBeispielDaten();
//
        // Hole die Contentpane
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        // Panel hinufügen
        // Listevorschau
        ListeVorschau vorschau = new ListeVorschau(this);

        // Liste
        this.listePanel = new Liste(spiele, vorschau);

        c.add(listePanel, BorderLayout.CENTER);
        c.add(vorschau, BorderLayout.EAST);

        // Zeige die Anwendung
        setVisible(true);
    }

    public void ladeDatei() throws IOException {
        // Löschen der vorhandenden spiele
        this.spiele.clear();

        // Lese alle Spiele
        DataInputStream in = Spieleliste.getInputStream(FILE_PATH);

        // Inititalisiere neues SpieleDAO objekt. Dabei ist der Outputstream null, da wir die Datei nicht leeren wollen
        SpieleDAO spieleDAO = new SpieleDAO(in, null);
        spieleDAO.read(spiele);

        System.out.println("Lade Datei: \"" + FILE_PATH + "\"" + "\t-->\t" + spiele.getAll().size() + " Spiele geladen!");

        // Schließen des Inputstreams
        in.close();
    }

    // Erstellt die Datenbankdatei die zur Speicherung benötigt wird
    public void erstelleDatei() throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();
    }

    // Erstellt Beispieldaten zur Veranschaulichung
    private void erstelleBeispielDaten() {
        spiele.add(new Spiel("Super Mario Galaxy", true, 85, 8.5));
        spiele.add(new Spiel("GTA V", true, 210, 9.3));
        spiele.add(new Spiel("The Witcher III", false, 14, 7));
        spiele.add(new Spiel("Half Life II", true, 92, 10));
        spiele.add(new Spiel("Minecraft", true, 1000, 10));
        spiele.add(new Spiel("Portal II", false, 26, 9));
        spiele.add(new Spiel("WoW", false, 26789, 10));
    }

    // Gibt alle aktuellen Spiele zurück
    public Spiele getSpiele() {
        return this.spiele;
    }

    // Gibt die Liste zurück
    public Liste getListe() {
        return this.listePanel;
    }

    // Gibt einen Outputsream zurück
    public static DataOutputStream getOutputStream(String path) throws FileNotFoundException {
        // Outputstreams
        FileOutputStream fileOut = new FileOutputStream(path);
        BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
        DataOutputStream dataOut = new DataOutputStream(buffOut);

        return dataOut;
    }

    // Gibt einen Inputstream zurück
    public static DataInputStream getInputStream(String path) throws FileNotFoundException {
        // Inputsreams
        FileInputStream fileIn = new FileInputStream(path);
        BufferedInputStream buffIn = new BufferedInputStream(fileIn);
        DataInputStream dataIn = new DataInputStream(buffIn);

        return dataIn;
    }

    // Main methode die von Java ausgeführt wird
    public static void main(String[] args) {
        // Initialisiert die Anwendung
        Spieleliste.spieleListe = new Spieleliste("./data/datenbank.db");
    }

}
