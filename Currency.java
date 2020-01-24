/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class Currency implements Seperable {

    private int units = 0;
    private int fractions = 0;
    private char seperator = ',';
    
    public Currency() {
        units = 1;
    }
    
    public Currency(int units, int fractions) { //Ohne Seperator-parameter, d.h. "," (Standard)
        this.units = units;
        this.fractions = fractions;
        normalize(); //Stelle überschüssige fractions auf unit um
    }
    
    public Currency(int units, int fractions, char seperator) { //Mit Seperator-parameter
        this.units = units;
        this.fractions = fractions;
        this.seperator = seperator;
        normalize();
    }
    
    public static void main(String[] args) {
        Dollar dollar = new Dollar(42, 111); //Dollarobjekt mit 43,11 Dollar
        Ariary ariary = new Ariary(3450, 6); //Ariaryobjekt mit 3451,1 Ariary
        Euro euro = new Euro(42, 111); //Euroobjekt mit 43,11 Euro
        System.out.println(dollar.toString()); //Ausgabe von toString der Objekte
        System.out.println(ariary.toString());
        System.out.println(euro.toString());
        Dollar ariaryToDollar = Dollar.exchange(ariary); //Dollarobjekt nach Wertberechnung vom Ariaryobjekt
        Dollar euroToDollar = Dollar.exchange(euro); //Dollarobjekt nach Werteberechnung vom Euroobjekt
        System.out.println(ariaryToDollar.toString()); //Ausgabe der neuen Dollarwerte der Objekte
        System.out.println(euroToDollar.toString());
    }
    
    @Override
    public int getUnits() {
        return units;
    }

    @Override
    public int getFractions() {
        return fractions;
    }
    
    protected void setValues(int units, int fractions) {
        this.units = units;
        this.fractions = fractions;
    }

    @Override
    public void setSeperator(char seperator) {
        this.seperator = seperator;
    }

    @Override
    public void normalize() { //Centbeträge über 100 werden in Euro umgewandelt
        int addition = fractions / 100;
        fractions -= addition * 100;
        units += addition;
        
        while(fractions < 0) {
            units--;
            fractions += 100;
        }
    }
    
    @Override
    public String toString() {
        String fractionsAngabe = String.valueOf(fractions); //Hilfsvariable, falls der Centbetrag unter 10 fällt
        if (fractions < 10) {
            fractionsAngabe = "0" + fractionsAngabe;
        }
        return (units + "" + (char) seperator + fractionsAngabe); //Rückgabe des Textes in geforderter Form
    }
}
