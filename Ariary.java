/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class Ariary extends Currency {
    
    public Ariary() {
        super();
    }
    
    public Ariary(int ariary, int iraimbilanja) {
        super(ariary, iraimbilanja);
    }
    
    @Override
    public void normalize() {
        while(getFractions() > 4) { //Solange cent über 99
            setValues(getUnits() + 1, getFractions() - 5);
        }
        while(getFractions() < 0) {
            setValues(getUnits() - 1, getFractions() + 5);
        }
    }
    
    public int getAriary() {
        return getUnits();
    }
    
    public int getIraimbilanja() {
        return getFractions();
    }
    
    @Override
    public String toString() {
        return super.getUnits() + " MAG " + super.getFractions() + " Iraimbilanja";
    }
}
