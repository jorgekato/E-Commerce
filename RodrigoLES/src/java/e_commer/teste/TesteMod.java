/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.teste;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class TesteMod {
    
    public static void main(String[] args) {
        
//        int contador = 0;
//        
//       for (int i = 0; i < 30; i++) {
//           
//           if((contador % 3) == 0)
//               System.out.println(contador + " é Multiplo de 3");
//           contador++;
//       }
        
       List<Object> valores = new ArrayList<Object>();
        for (int i = 0; i < 10; i++) {
            valores.add(i);
        }
//        for(Object valor : valores){
//            System.out.println(valor);
//        }
        
        int last = valores.size();
        System.out.println(last);
        
        DecimalFormat formato = new DecimalFormat("#.##");
        double valor = 10.2222;
        double total = Double.parseDouble(formato.format(valor).replace(",", "."));
        System.out.println(total);
    }
    
}
