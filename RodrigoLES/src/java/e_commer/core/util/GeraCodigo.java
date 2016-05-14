package e_commer.core.util;

import java.util.Random;

/**
 *
 * @author Jorge
 */
public class GeraCodigo {
    
    public static String geraCodigoAleatorio(){
        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ0123456789";

        Random random = new Random();

        String armazenaChaves = "";
        int index = -1;
        for (int i = 0; i < 15; i++) {
            index = random.nextInt(letras.length());
            armazenaChaves += letras.substring(index, index + 1);
        }
        return(armazenaChaves);
    }
}
