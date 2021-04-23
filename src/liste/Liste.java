/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liste;

import data.Spiele;
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author Nichl
 */
public class Liste extends JPanel {

    private DefaultListModel dataModel;
    private JList liste;

    public Liste(Spiele spiele) {
        dataModel = new DefaultListModel();
        dataModel.addAll(spiele.getAll());
        liste = new JList(dataModel);
        
        setLayout(new BorderLayout());
        
        add(new JLabel("Alle aktuellen Spiele"), BorderLayout.NORTH);
        add(liste, BorderLayout.CENTER);
    }

}
