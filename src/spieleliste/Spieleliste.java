/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spieleliste;

import java.awt.Dimension;
import javax.swing.JFrame;
import listener.SpieleWindowAdapter;

public class Spieleliste extends JFrame {

    public static Spieleliste spieleListe;

    public Spieleliste() {
        setTitle("Spieleliste");
        // Setzt die Größe des Fenster auf minimum x, y
        setMinimumSize(new Dimension(500, 400));
        // Setzt das Fenster in die Mitte
        setLocationRelativeTo(null);

        // Sorgt dafür, dass das Fenster auch geschlossen wird.
        addWindowListener(new SpieleWindowAdapter());
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        Spieleliste.spieleListe = new Spieleliste();
    }

}
