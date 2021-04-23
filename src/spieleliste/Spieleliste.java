package spieleliste;

import data.Spiel;
import data.Spiele;
import java.awt.Dimension;
import javax.swing.JFrame;
import liste.Liste;
import listener.SpieleWindowAdapter;

/**
 * Hauptklasse.<br>
 * Baut das Anwendungsfenster auf.
 *
 * @author Nichlas
 * @author Steeve
 * @author Jan-Tilos
 */
public class Spieleliste extends JFrame {

    public static Spieleliste spieleListe;
    private Spiele spiele = new Spiele();

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
        addWindowListener(new SpieleWindowAdapter(this));

        // Beispieldaten
        spiele.add(new Spiel("Mario bros. II", true, 400, 9.5));
        spiele.add(new Spiel("Rocket League", false, 230, 6.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Stalker Call of Pripiyat", true, 100, 10));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));
        spiele.add(new Spiel("Cyberpunk", true, 20, 2.5));

        // Füge die Liste hinzu
        add(new Liste(spiele));

        // Zeige die Anwendung
        setVisible(true);
    }

    public static void main(String[] args) {
        // Initialisiert die Anwendung
        Spieleliste.spieleListe = new Spieleliste();
    }

    public Spiele getSpiele() {
        return this.spiele;
    }

}
