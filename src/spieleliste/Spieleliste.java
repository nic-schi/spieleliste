package spieleliste;

import data.Spiel;
import data.Spiele;
import data.dao.SpieleDAO;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.MenuBar;
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import liste.Liste;
import liste.ListeVorschau;
import listener.SpieleWindowAdapter;

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
    private Liste liste;

    public static final String FILE_PATH = "./data/datenbank.db";

    public Spieleliste() {
        setTitle("Spieleliste");
        // Setzt die Größe des Fenster auf minimum x, y
        setMinimumSize(new Dimension(500, 400));
        // Setzt das Fenster in die Mitte
        setLocationRelativeTo(null);

        // Soll standardmäßig nichts beim schließen tun.
        // Das tatsächliche schließen wird durch den SpieleWindowAdapter implementiert
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Sorgt dafür, dass das Fenster auch geschlossen wird.
        setJMenuBar(createMenuBar());
        // Stellt das Menu
        addWindowListener(new SpieleWindowAdapter(this));

        // Erstelle beispieldaten
//        erstelleBeispielDaten();
        // Lese alle Spiele ein
        try {
            // Erstelle die Datenbankdatei
            erstelleDatei();

            // Lese alle Spiele
            DataInputStream in = Spieleliste.getInputStream(FILE_PATH);
            // Inititalisiere neues SpieleDAO objekt. Dabei ist der Outputstream null, da wir die Datei nicht leeren wollen
            SpieleDAO spieleDAO = new SpieleDAO(in, null);
            spieleDAO.read(spiele);

            System.out.println(spiele.getAll().size() + " Spiele geladen!");

            // Schließen des Inputstreams
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Spieleliste.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Hole die Contentpane
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        // Panel hinufügen
        // Listevorschau
        ListeVorschau listeVorschau = new ListeVorschau(this);

        // Liste
        this.liste = new Liste(spiele, listeVorschau);

        c.add(liste, BorderLayout.CENTER);
        c.add(listeVorschau, BorderLayout.EAST);

        // Zeige die Anwendung
        setVisible(true);
    }

    public static void main(String[] args) {
        // Initialisiert die Anwendung
        Spieleliste.spieleListe = new Spieleliste();
    }

    // Erstellt die Datenbankdatei die zur Speicherung benötigt wird
    private void erstelleDatei() throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();
    }

    // Gibt alle aktuellen Spiele zurück
    public Spiele getSpiele() {
        return this.spiele;
    }

    // Gibt die Liste zurück
    public Liste getListe() {
        return this.liste;
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

    // Erstellt Beispieldaten zur Veranschaulichung
    private void erstelleBeispielDaten() {
        spiele.add(new Spiel("Mario bros. II", true, 400, 9.5));
        spiele.add(new Spiel("Rocket League", false, 240, 6.5));
        spiele.add(new Spiel("Cyberpunk", true, 28, 2.5));
        spiele.add(new Spiel("Stalker Call of Pripiyat", true, 100, 10));
    }

    private JMenuBar createMenuBar() { 
       JMenuBar menuBar = new JMenuBar ();
       // Setz Datei 
        JMenu File = new JMenu( "Datei..." );
        File.setMnemonic( 'D' );

        JMenuItem mnuNewFile = new JMenuItem( "Neu" );
        mnuNewFile.setMnemonic( 'N' );
        File.add(mnuNewFile);

        File.addSeparator();
        JMenuItem mnuOpenFile = new JMenuItem( "Öffnen" );
        mnuOpenFile.setMnemonic( 'Ö' );       
        File.add(mnuOpenFile);
        menuBar.add(File);
        
        JMenuItem mnuBeenden = new JMenuItem("Beenden");
        mnuBeenden.setMnemonic('B');
        File.add(mnuBeenden);
        
        JMenu UberUns = new JMenu( "Über uns" );
        UberUns.setMnemonic( 'Ü' );        
        menuBar.add( UberUns );
        
        return menuBar;
    }

}
