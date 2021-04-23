/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spieleliste;

import data.Spiel;
import data.Spiele;
import java.awt.Dimension;
import javax.swing.JFrame;
import liste.Liste;
import listener.SpieleWindowAdapter;

public class Spieleliste extends JFrame {

    public static Spieleliste spieleListe;
    private Spiele spiele = new Spiele();

    public Spieleliste() {
        setTitle("Spieleliste");
        // Setzt die Größe des Fenster auf minimum x, y
        setMinimumSize(new Dimension(500, 400));
        // Setzt das Fenster in die Mitte
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Sorgt dafür, dass das Fenster auch geschlossen wird.
        addWindowListener(new SpieleWindowAdapter(this));
        
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
        
        
        // Liste
        add(new Liste(spiele));
        
        setVisible(true);
    }

    public static void main(String[] args) {
        Spieleliste.spieleListe = new Spieleliste();
    }

}
