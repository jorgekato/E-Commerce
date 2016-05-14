package e_commer.core.impl.negocio;

import e_commer.core.IStrategy;
import e_commer.dominio.EntidadeDominio;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Jorge
 */
public class ComplementarDtValidadeCredito implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if (entidade != null) {
            Calendar c = new GregorianCalendar();
            c.add(Calendar.DAY_OF_MONTH, 60);//adiciona 60 dias a partir da data de hoje

            entidade.setDtValidade(c.getTime());
        } else {
            return "Entidade: " + entidade.getClass().getCanonicalName() + " nula!";
        }

        return null;
    }

}
