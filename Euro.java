/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public class Euro extends Currency {
    
    public Euro() {
        super();
        setSeperator(',');
    }
    
    public Euro(int euro, int cent) {
        super(euro, cent);
        setSeperator(',');
    }
    
    public int getEuro() {
        return getUnits();
    }
    
    public int getCent() {
        return getFractions();
    }
    
    @Override
    public String toString() {
        return super.toString() + " EUR";
    }
}
