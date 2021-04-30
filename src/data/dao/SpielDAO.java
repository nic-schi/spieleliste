package data.dao;

import data.Spiel;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Verwaltet die Speicherung und Lesung von einem einzelnen Spiel.
 * 
 * @author Nichlas
 * @author Jan-Tilo
 * @author Steeve
 */
public class SpielDAO implements DAO<Spiel> {

    private DataInputStream in;
    private DataOutputStream out;

    public SpielDAO(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void write(Spiel spiel) {
        try {
            // Schreibt alle Daten des Spiels in den Outputstream
            out.writeUTF(spiel.getName());
            out.writeBoolean(spiel.isDurchgespielt());
            out.writeInt(spiel.getStunden());
            out.writeDouble(spiel.getBewertung());
        } catch (IOException ex) {
            Logger.getLogger(SpielDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Spiel read(Spiel spiel) {
        try {
            // Liest alle Daten aus dem Inputstream ein
            spiel.setName(in.readUTF());
            spiel.setDurchgespielt(in.readBoolean());
            spiel.setStunden(in.readInt());
            spiel.setBewertung(in.readDouble());
        } catch (IOException ex) {
            Logger.getLogger(SpielDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Gibt das Spiel zurück
        return spiel;
    }

}
