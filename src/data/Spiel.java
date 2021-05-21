package data;

/**
 * Verwaltet das Datenobjekt: Spiel
 *
 * @author Nichlas
 * @author Steeve
 * @author Jan-Tilo
 */
public class Spiel {

    // Datenfelder
    private String name;
    private boolean durchgespielt;
    private int stunden;
    private double bewertung;

    // Konstruktor mit den gegebenen Datenfeldern
    public Spiel(String name, boolean durchgespielt, int stunden, double bewertung) {
        this.name = name;
        this.durchgespielt = durchgespielt;
        this.stunden = stunden;
        this.bewertung = bewertung;
    }

    // Leerer Konstruktor
    public Spiel() {
    }

    // Gibt den Namen zurück
    public String getName() {
        return this.name;
    }

    // Gibt wieder ob das Spiel durchgespielt worden ist oder nicht
    public boolean isDurchgespielt() {
        return this.durchgespielt;
    }

    // Gibt die Stunden zurück
    public int getStunden() {
        return this.stunden;
    }

    // Gibt die Bewertung zurück
    public double getBewertung() {
        return this.bewertung;
    }

    // Setzt den Namen
    public void setName(String name) {
        this.name = name;
    }

    // Setzt, ob das Spiel durchgespielt worden ist oder nicht
    public void setDurchgespielt(boolean durchgespielt) {
        this.durchgespielt = durchgespielt;
    }

    // Setzt die Stunden
    public void setStunden(int stunden) {
        this.stunden = stunden;
    }

    // Setzt die Bewertung
    public void setBewertung(double bewertung) {
        this.bewertung = bewertung;
    }

    // Überschreiben der von Object-ausgehenden toString methode
    // Wird für die Liste benötigt, sodass diese den Namen anzeigen kann
    @Override
    public String toString() {
        return this.name;
    }

}
