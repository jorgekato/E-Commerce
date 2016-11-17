/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.util;

import e_commer.dominio.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 * @author Henrique
 */
public class Teste {

    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//      Date dataBase = null;
//      Date vencimento = null;
//      try {
//         dataBase = sdf.parse("07/10/2000");
//         vencimento = sdf.parse("03/11/2000");
//      } catch (java.text.ParseException e) { return; }
//      long diferencaMS = vencimento.getTime() - dataBase.getTime();
//      long diferencaSegundos = diferencaMS / 1000;
//      long diferencaMinutos = diferencaSegundos / 60;
//      long diferencaHoras = diferencaMinutos / 60;
//      long diferencaDias = diferencaHoras / 24;
//      System.out.println(diferencaMS);
//      System.out.println(diferencaSegundos);
//      System.out.println(diferencaMinutos);
//      System.out.println(diferencaHoras);
//      System.out.println(diferencaDias);
//    }

//        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ0123456789";
//
//        Random random = new Random();
//
//        String armazenaChaves = "";
//        int index = -1;
//        for (int i = 0; i < 15; i++) {
//            index = random.nextInt(letras.length());
//            armazenaChaves += letras.substring(index, index + 1);
//        }
//        System.out.println(armazenaChaves);
        
        
//        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

//        Calendar c = new GregorianCalendar(2004, 9, 2);
        Calendar c = new GregorianCalendar();
//        System.out.println(c.getTime());
//        System.out.println("Data: " + sd.format(c.getTime()));

/*        c.add(Calendar.DAY_OF_MONTH, 60);
        System.out.println("Sessenta dias depois: " + sd.format(c.getTime()));

        c.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println("Um dia antes: " + sd.format(c.getTime()));

        c.add(Calendar.MONTH, -1);
        System.out.println("Um mês antes: " + sd.format(c.getTime()));

        c = new GregorianCalendar(2003, Calendar.MARCH, 3);
        System.out.println("Data: " + sd.format(c.getTime()));

        c.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println("Um dia antes: " + sd.format(c.getTime()));

        c.add(Calendar.MONTH, -1);
        System.out.println("Um mês antes: " + sd.format(c.getTime()));
        
        c.add(Calendar.YEAR, 1);
        System.out.println("Um ano depois: " + sd.format(c.getTime()));
*/
    }
}
