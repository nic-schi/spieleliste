/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Nichl
 */
public class SpieleWindowAdapter extends WindowAdapter {
    
    @Override
    // TODO: Nutzer wird befragt, ob er das Fenster wirklich schließen möchte
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
    
}
