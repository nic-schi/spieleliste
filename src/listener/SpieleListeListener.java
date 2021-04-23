/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import data.Spiel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author jtmey
 */
public class SpieleListeListener implements ListSelectionListener{

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList liste=(JList) e.getSource();
        Spiel spiele=(Spiel) liste.getSelectedValue();
        System.out.println(spiele);
    }
    
}
