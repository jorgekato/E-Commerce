/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.teste;

/**
 *
 * @author Henrique
 */
public class TesteMod {
    
    public static void main(String[] args) {
        
        int contador = 0;
        
       for (int i = 0; i < 30; i++) {
           
           if((contador % 3) == 0)
               System.out.println(contador + " é Multiplo de 3");
           contador++;
       }
        
    }
    
}
