/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liste;

import data.Spiele;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import listener.SpieleListeListener;

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
        
        liste.addListSelectionListener(new SpieleListeListener());
        liste.setVisibleRowCount(12);
        liste.setSelectionMode(ListSelectionModel. SINGLE_SELECTION);
        liste.setSelectionBackground(new Color(146, 159, 238));
        liste.setFixedCellHeight(25);
        setLayout(new BorderLayout());
        
        add(new JLabel("Alle aktuellen Spiele"), BorderLayout.NORTH);
        add(new JScrollPane(liste), BorderLayout.CENTER);
    }

}
