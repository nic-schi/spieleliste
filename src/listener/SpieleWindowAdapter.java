/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import spieleliste.Spieleliste;

/**
 *
 * @author Nichl
 */
public class SpieleWindowAdapter extends WindowAdapter {

    private Spieleliste frame;

    public SpieleWindowAdapter(Spieleliste frame) {
        this.frame = frame;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //        JDialog dialog = new JDialog(frame, "Sind Sie sicher?");
        //        dialog.setMinimumSize(new Dimension(250, 140));
        //        dialog.setLocationRelativeTo(frame);
        //
        //        Container contentPane = dialog.getContentPane();
        //        JPanel content = new JPanel();
        //        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        //        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        //
        //        JPanel buttonPanel = new JPanel();
        //
        //        // ja
        //        JButton jaButton = new JButton("Ja");
        //        jaButton.addActionListener(new ActionListener() {
        //            @Override
        //            public void actionPerformed(ActionEvent e) {
        //                System.exit(0);
        //            }
        //        });
        //
        //        // nein
        //        JButton neinButton = new JButton("Nein");
        //        neinButton.addActionListener(new ActionListener() {
        //            @Override
        //            public void actionPerformed(ActionEvent e) {
        //                dialog.setVisible(false);
        //            }
        //        });
        //
        //        buttonPanel.add(jaButton);
        //        buttonPanel.add(neinButton);
        //
        //        // label
        //        JLabel label = new JLabel("Es könnten Daten verloren gehen.");
        //        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        //
        //        content.add(label);
        //        content.add(buttonPanel);
        //        contentPane.add(content);

        //        dialog.setVisible(true);

        
        // gibt einen Dialog aus, der aktuell YES und NO anzeigt.
        int returned = JOptionPane.showConfirmDialog(frame, "Es könnten Daten verloren gehen.", "Sind Sie Sicher?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        // Prüfung der Auswahl
        if (returned == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
