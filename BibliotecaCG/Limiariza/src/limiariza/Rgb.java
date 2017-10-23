/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package limiariza;

/**
 *
 * @author renato
 */
public class Rgb {

    double[] r = new double[256];
    double[] g = new double[256];
    double[] b = new double[256];

    public Rgb() {
    }

    @Override
    public String toString() {
        return "Rgb{" + "r=" + r + ", g=" + g + ", b=" + b + '}';
    }
    
}
