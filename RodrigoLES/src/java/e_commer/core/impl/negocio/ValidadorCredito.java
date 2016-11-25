/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.impl.negocio;

import e_commer.core.IStrategy;
import e_commer.core.impl.dao.CreditoDAO;
import e_commer.dominio.AbstractItem;
import e_commer.dominio.Credito;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Pedido;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henrique
 */
public class ValidadorCredito implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if (entidade != null) {

            if (entidade.getClass().getName().equals(Pedido.class.getName())) {

                Pedido pedido = (Pedido) entidade;

                if (pedido.getCredito() != null) {
                    CreditoDAO creDAO = new CreditoDAO();
                    try {
                        List<EntidadeDominio> entidades = creDAO.consultar(pedido.getCredito());
                        for (EntidadeDominio c : entidades) {
                            if (!((Credito)c).getFlgAtivo()) {
                                return "Desculpe, Crédito " + ((Credito)c).getCodigo() + " inválido!";
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ValidadorCredito.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            return "Entidade: " + entidade.getClass().getCanonicalName() + " nula!";
        }

        return null;
    }

}
