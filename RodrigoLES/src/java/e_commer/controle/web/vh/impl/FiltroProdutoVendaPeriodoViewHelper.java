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
import e_commer.filtroAnalise.FiltroProdutoVendaPeriodo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Henrique
 */
public class FiltroProdutoVendaPeriodoViewHelper implements IViewHelper{
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        
        FiltroProdutoVendaPeriodo f = new FiltroProdutoVendaPeriodo();
        
        f.setDt_inicial(ConverteDate.converteStringDate(request.getParameter("txtDataInicial")));
        f.setDt_final(ConverteDate.converteStringDate(request.getParameter("txtDataFinal")));
        
        return f;
        
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        RequestDispatcher d = null;
        request.setAttribute("grafico", resultado);
        //d = request.getRequestDispatcher("LadoAdmin/graficobarra.jsp");
        d = request.getRequestDispatcher("LadoAdmin/Relatorios/produtosmaisvendidos.jsp");
        d.forward(request, response);
    }
    
}
