/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author Nichl
 */
public class Spiele {
    
    private ArrayList<Spiel> spiele = new ArrayList<>();
    
    public void add(Spiel spiel) {
        spiele.add(spiel);
    }
    
    public Spiel get(int index) {
        return spiele.get(index);
    }
    
    public ArrayList<Spiel> getAll() {
        return this.spiele;
    }
    
}
