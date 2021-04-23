/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Nichl
 */
public class Spiel extends Object {

    private String name;
    private boolean durchgespielt;
    private int stunden;
    private double bewertung;

    public Spiel(String name, boolean durchgespielt, int stunden, double bewertung) {
        this.name = name;
        this.durchgespielt = durchgespielt;
        this.stunden = stunden;
        this.bewertung = bewertung;
    }

    public String getName() {
        return this.name;
    }
    
    public boolean isDurchgespielt() {
        return this.durchgespielt;
    }
    
    public int getStunden() {
        return this.stunden;
    }
    
    public double getBewertung() {
        return this.bewertung;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDurchgespielt(boolean durchgespielt) {
        this.durchgespielt = durchgespielt;
    }
    
    public void setStunden(int stunden) {
        this.stunden = stunden;
    }
    
    public void setBewertung(double bewertung) {
        this.bewertung = bewertung;
    }
    
    @Override
    public String toString() {
        return this.name;
    }

}
