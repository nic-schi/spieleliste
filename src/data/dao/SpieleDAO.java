package data.dao;

import data.Spiel;
import data.Spiele;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Verwaltet die Speicherung und Lesung von allen Spielen.
 *
 * @author Nichlas
 * @author Jan-Tilo
 * @author Steeve
 */
public class SpieleDAO implements DAO<Spiele> {

    private DataInputStream in;
    private DataOutputStream out;

    public SpieleDAO(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void write(Spiele spiele) {
        // Gehe alle vorhandenen Spiele durch
        for (Spiel spiel : spiele.getAll()) {
            // schreibt jedes Spiel einzelnd in den Outputstream
            SpielDAO spielDAO = new SpielDAO(in, out);
            spielDAO.write(spiel);
        }
    }

    @Override
    public Spiele read(Spiele spiele) {
        try {
            // Lese solange, bis keine Bytes mehr zum lesen vorhanden sind
            while (in.available() != 0) {
                // Liest ein einzelnes Spiel
                SpielDAO spielDAO = new SpielDAO(in, out);
                // Erstellung des Platzhalters
                Spiel spiel = new Spiel();
                spielDAO.read(spiel);

                // Füge das neue Spiel in die Liste hinzu
                spiele.add(spiel);
            }
        } catch (IOException ex) {
            Logger.getLogger(SpieleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return spiele;
    }

}
