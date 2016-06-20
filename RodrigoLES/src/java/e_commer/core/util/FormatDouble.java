/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.util;

import java.text.DecimalFormat;

/**
 *
 * @author Jorge
 */
public class FormatDouble {
    
    public static double formataDouble(double valor){
        DecimalFormat formato = new DecimalFormat("#.##");
        
        return Double.parseDouble(formato.format(valor).replace(",", "."));
    }
}
