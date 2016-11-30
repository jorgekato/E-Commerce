/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.controle.web.vh.impl;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.core.util.ConverteDate;
import e_commer.dominio.EntidadeDominio;
import e_commer.filtroAnalise.FiltroClienteVendaPeriodo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Henrique
 */
public class FiltroClienteVendaPeriodoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        FiltroClienteVendaPeriodo f = new FiltroClienteVendaPeriodo();
        String operacao = request.getParameter("operacao");

        if (!operacao.equals("grafico4")) {
            f.setDt_inicial(ConverteDate.converteStringDate(request.getParameter("txtDataInicial")));
            f.setDt_final(ConverteDate.converteStringDate(request.getParameter("txtDataFinal")));
        } else {
            f.setId(Integer.valueOf(request.getParameter("txtId")));
        }

        return f;

    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher d = null;
        String operacao = request.getParameter("operacao");
        if (!operacao.equals("grafico4")) {

            request.setAttribute("grafico", resultado);
            request.setAttribute("retorno", "1");
            d = request.getRequestDispatcher("LadoAdmin/Relatorios/clientecompraperiodo.jsp");
            d.forward(request, response);
        }else
        {
            if(resultado.getEntidades().size() == 0){
                request.setAttribute("grafico", null);
            }else{
                request.setAttribute("grafico", resultado);
            }
            request.setAttribute("retorno", "1");
            d = request.getRequestDispatcher("LadoAdmin/Relatorios/clienteperiodo.jsp");
            d.forward(request, response);
        }
    }

}
