package menubar;

import javax.swing.JMenuBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import menubar.menus.DateiMenu;
import menubar.menus.ÜberUnsMenu;
import spieleliste.Spieleliste;

/**
 *
 * Beinhaltet die JMenuBar die auf dem JFrame dargestellt wird.
 *
 * @author Steeve
 * @author Nichlas
 * @author Jan-Tilo
 */
public class SpieleMenuBar extends JMenuBar {

    // Border die bei jedem JMenuItem und JMenu benutzt wird
    public static final Border border = new EmptyBorder(3, 7, 3, 7);

    public SpieleMenuBar(Spieleliste frame) {
        // Menüs
        add(new DateiMenu(frame));
        add(new ÜberUnsMenu(frame));
    }

}
