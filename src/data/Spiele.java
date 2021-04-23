package data;

import java.util.ArrayList;

/**
 * Verwaltet alle Spiele.
 *
 * @author Nichlas
 * @author Steeve
 * @author Jan-Tilo
 */
public class Spiele {

    // Liste aller Spiele
    private ArrayList<Spiel> spiele = new ArrayList<>();

    // Fügt ein Spiel hinzu
    public void add(Spiel spiel) {
        spiele.add(spiel);
    }

    // Holt sich ein bestimmtes Spiel durch den gegebenen Index
    public Spiel get(int index) {
        return spiele.get(index);
    }

    // Gibt die Liste aller Spiele zurück
    public ArrayList<Spiel> getAll() {
        return this.spiele;
    }

}
