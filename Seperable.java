/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author evgen
 */
public interface Seperable {
    int getUnits();
    int getFractions();
    void setSeperator(char seperator);
    void normalize();
}
