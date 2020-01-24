/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class Dollar extends Currency implements Comparable<Dollar> {
    
    public static final double EURO_RATE = 0.8152;
    public static final double ARIARY_RATE = 3245.0;
    
    public Dollar() {
        super();
        setSeperator(',');
    }
    
    public Dollar(int dollar, int cent) {
        super(dollar, cent);
        setSeperator(',');
    }
    
    public int getDollar() {
        return getUnits();
    }
    
    public int getCent() {
        return getFractions();
    }
    
    @Override
    public String toString() {
        return super.toString() + " DOL";
    }
    
    private static Dollar exchange(Currency curry, int factor, double kurs) { //Tausche Geld in eine Währung um
        Dollar d = new Dollar(0, (int) ((curry.getUnits()*factor + curry.getFractions()) / kurs)); //Normalize() aus Currency wandelt den Fractions-betrag um
        return d;
    }
    
    public static Dollar exchange(Euro euro) { //Tausche Euro in Dollar
        return exchange(euro, 100, EURO_RATE); //Rufe exchange auf, mit geforderten Parametern zur Währung
    }
    
    public static Dollar exchange(Ariary ariary) { //Tausche Ariary in Dollar
        return exchange(ariary, 5, ARIARY_RATE); //Rufe exchange auf, mit geforderten Parametern zur Währung
    }

    @Override
    public int compareTo(Dollar d) { //Vergleiche Dollarbeträge
        int fracts = getUnits() * 100 + getFractions(); //Units in Fractions umwandeln
        int fracts2 = d.getUnits() * 100 + d.getFractions(); //Units in Fractions umwandeln
        if (fracts < fracts2) { //Fractions vergleichen
            return -1;
        } else if (fracts == fracts2) {
            return 0;
        } else {
            return 1;
        }
    }
}
