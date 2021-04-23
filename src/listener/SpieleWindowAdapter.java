/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
    // TODO: Nutzer wird befragt, ob er das Fenster wirklich schließen möchte
    public void windowClosing(WindowEvent e) {
        JDialog dialog = new JDialog(frame, "Sind Sie sicher?");
        dialog.setMinimumSize(new Dimension(250, 140));
        dialog.setLocationRelativeTo(frame);

        Container contentPane = dialog.getContentPane();
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel buttonPanel = new JPanel();

        // ja
        JButton jaButton = new JButton("Ja");
        jaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // nein
        JButton neinButton = new JButton("Nein");
        neinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });

        buttonPanel.add(jaButton);
        buttonPanel.add(neinButton);

        // label
        JLabel label = new JLabel("Es könnten Daten verloren gehen.");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        content.add(label);
        content.add(buttonPanel);
        contentPane.add(content);

        dialog.setVisible(true);
    }

}
