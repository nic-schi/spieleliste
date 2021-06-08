package menubar.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import menubar.SpieleMenuBar;
import spieleliste.Spieleliste;

/**
 *
 * Stellt den Überuns-Menüpunkt dar.
 *
 * @author Steeve
 * @author Nichlas
 * @author Jan-Tilo
 */
public class ÜberUnsMenu extends JMenu implements ActionListener {

    private Spieleliste frame;

    private JMenuItem überUns = new JMenuItem("Über uns");

    public ÜberUnsMenu(Spieleliste frame) {
        super("Hilfe");
        this.frame = frame;

        // Ermöglicht pressen von "ALT + das jeweillige Zeichen" zum selektieren
        this.setMnemonic('H');
        überUns.setMnemonic('Ü');

        this.setBorder(SpieleMenuBar.border);
        überUns.setBorder(SpieleMenuBar.border);

        überUns.addActionListener(this);

        this.add(überUns);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Gibt Dialog aus
        // Dient nur zur Information
        JOptionPane.showMessageDialog(frame, "Wir sind ein kleines Team aus Wirtschaftsinformatik Studen-\n"
                + "ten, dass im Rahmen der Programmieren II Vorlesung der Jade-\n"
                + "hochschule ein Programm in Java zur Verwaltung von Spielen erstellt hat."
                + "\n"
                + "\nAutoren\n"
                + ">     Nichlas Schipper\n"
                + ">     Steeve Junior\n"
                + ">     Jan-Tilo Meyer\n"
                + "\n"
                + "Entwickelt auf Java 16.0\n"
                + "Benutzte Frameworks: Swing\n"
                + "Github-Link: github.com/nic-schi/spieleliste\n"
                + "Aktuelle Datenbank: \"" + new File(Spieleliste.FILE_PATH).getPath() + "\"", "Über uns", JOptionPane.INFORMATION_MESSAGE);
    }

}
