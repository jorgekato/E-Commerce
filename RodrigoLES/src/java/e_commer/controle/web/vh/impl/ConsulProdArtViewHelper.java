/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.controle.web.vh.impl;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.controle.Fachada;
import e_commer.core.util.ConverteDate;
import e_commer.dominio.Artesanato;
import e_commer.dominio.Categorias;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Produto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Henrique
 */
public class ConsulProdArtViewHelper implements IViewHelper {

    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String tipo = request.getParameter("tipo");
        Produto produto = null;
        Artesanato artesanato = null;

        if (tipo.equals("Produto")) {

            String nome = request.getParameter("nome");  //ok

            produto = new Produto();

            if (nome != null && !nome.trim().equals("")) {
                produto.setNome(nome);
            }
            return produto;
        } else if (tipo.equals("Artesanato")) {

            String nome = request.getParameter("nome");  //ok

            artesanato = new Artesanato();

            if (nome != null && !nome.trim().equals("")) {
                artesanato.setNome(nome);
            }
            return artesanato;
        }

        return null;
    }

    public void setView(Resultado resultado, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher d = null;

        String tipo = request.getParameter("tipo");
        String operacao = request.getParameter("operacao");

        if (tipo.equals("Produto")) {
            if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {

                request.setAttribute("resultado", resultado);
                d = request.getRequestDispatcher("LadoAdmin/pesqprodutos.jsp");
            } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {

                request.setAttribute("resultado", resultado);
                //d = request.getRequestDispatcher("pesqartesanato.jsp");
                d = request.getRequestDispatcher("produtostore.jsp");
            }
        } else if (tipo.equals("Artesanato")) {
            if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {

                request.setAttribute("resultado", resultado);
                d = request.getRequestDispatcher("LadoAdmin/pesqartesanato.jsp");
            } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {

                request.setAttribute("resultado", resultado);
                d = request.getRequestDispatcher("artesanatostore.jsp");
            }
        }
        d.forward(request, response);

    }

}
