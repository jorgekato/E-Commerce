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
import e_commer.filtroAnalise.FiltroProdutoQtdePeriodo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Henrique
 */
public class FiltroProdutoQtdePeriodoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        FiltroProdutoQtdePeriodo f = new FiltroProdutoQtdePeriodo();

        String[] ids = request.getParameterValues("txtId");
        
        
        
        if (ids.length == 1) {

            f.setId(Integer.valueOf(ids[0]));
        } else {
            f.setId(Integer.valueOf(ids[0]));
            FiltroProdutoQtdePeriodo f2 = new FiltroProdutoQtdePeriodo();
            f2.setId(Integer.valueOf(ids[1]));
            f.setFlgComparar(true);
            f.setfPQP(f2);
        }

        return f;

    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher d = null;

        if (resultado.getEntidades().size() == 0) {
            request.setAttribute("grafico", null);
        } else {
            request.setAttribute("grafico", resultado);
        }

        //d = request.getRequestDispatcher("LadoAdmin/graficobarra.jsp");
        d = request.getRequestDispatcher("LadoAdmin/Relatorios/produtoperiodo.jsp");
        d.forward(request, response);
    }

}
