/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.controle.web.vh.impl;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.EntidadeDominio;
import e_commer.filtroAnalise.FiltroEstoqueGeral;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Henrique Padovani
 */
public class FiltroEstoqueGeralViewHelper implements IViewHelper{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
         FiltroEstoqueGeral estGeral = new FiltroEstoqueGeral();

        return estGeral;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher d = null;
        request.setAttribute("estoqueGeral", resultado);
        request.setAttribute("retorno","1");
        d = request.getRequestDispatcher("LadoAdmin/Relatorios/estoquegeral.jsp");
        d.forward(request, response);
    }
    
}
