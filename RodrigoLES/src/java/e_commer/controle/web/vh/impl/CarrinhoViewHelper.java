/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.controle.web.vh.impl;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.CarrinhoCompra;
import e_commer.dominio.AbstractItem;
import e_commer.core.impl.controle.Fachada;
import e_commer.core.util.ConverteDate;
import e_commer.dominio.Artesanato;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.ItemArtesanato;
import e_commer.dominio.ItemProduto;
import e_commer.dominio.Produto;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Henrique
 */
public class CarrinhoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String operacao = request.getParameter("operacao");
        CarrinhoCompra carrinho = null;
        Resultado resultado = null;

        if (operacao.equals("ADICIONAR")) {

            if (request.getSession().getAttribute("carrinho") != null) {
                resultado = (Resultado) request.getSession().getAttribute("carrinho");
                for (EntidadeDominio e : resultado.getEntidades()) {
                    carrinho = (CarrinhoCompra) e;
                }
                //carrinho = resultado.getEntidades();
            } else {
                carrinho = new CarrinhoCompra();
            }

            String tipoClasse = request.getParameter("tipo");
            Fachada fachada = new Fachada();

            //Adiciona um item de artesanato ao carrinho
            if (tipoClasse.equals("ARTESANATO")) {
                String txtId = request.getParameter("txtId");
                int id = 0;

                if (txtId != null && !txtId.trim().equals("")) {
                    id = Integer.parseInt(txtId);
                }
                Artesanato artesanato = new Artesanato();
                artesanato.setId(id);
                resultado = fachada.consultar((EntidadeDominio) artesanato);

                ItemArtesanato item = new ItemArtesanato();

                for (EntidadeDominio e : resultado.getEntidades()) {
                    if (e.getId() == id) {
                        item.setArtesanato((Artesanato) e);
                    }
                }
                item.setQuantidade(Integer.parseInt(request.getParameter("qtde")));
                item.setTipo_item(tipoClasse);
                carrinho.setTotal(carrinho.getTotal() + (item.getArtesanato().getPrecoUnit()*item.getQuantidade()));
                carrinho.adiciona(item);

            } //Adiciona um item de produto ao carrinho
            else if (tipoClasse.equals("PRODUTO")) {
                String txtId = request.getParameter("txtId");
                int id = 0;

                if (txtId != null && !txtId.trim().equals("")) {
                    id = Integer.parseInt(txtId);
                }
                Produto produto = new Produto();
                produto.setId(id);
                resultado = fachada.consultar((EntidadeDominio) produto);

                ItemProduto item = new ItemProduto();

                for (EntidadeDominio e : resultado.getEntidades()) {
                    if (e.getId() == id) {
                        item.setProduto((Produto) e);
                    }
                }
                item.setQuantidade(Integer.parseInt(request.getParameter("qtde")));
                item.setTipo_item(tipoClasse);
                carrinho.setTotal(carrinho.getTotal() + (item.getProduto().getPrecoUnit()*item.getQuantidade()));
                carrinho.adiciona(item);
            }

        } else if (operacao.equals("EXCLUIRITEM")) {

            if (request.getSession().getAttribute("carrinho") != null) {
                resultado = (Resultado) request.getSession().getAttribute("carrinho");
                String txtId = request.getParameter("idItem");
                
                int id = 0;

                if (txtId != null && !txtId.trim().equals("")) {
                    id = Integer.parseInt(txtId);
                }
                for (EntidadeDominio e : resultado.getEntidades()) {
                    carrinho = (CarrinhoCompra) e;
                }

                List<AbstractItem> entidades = carrinho.getItens();

                if (entidades != null) {
                    
                    carrinho.remover(id);
                    if(entidades.size() == 0)
                        carrinho = null;
                }
                
            }
        } else if (operacao.equals("ATUALIZAR")) {

            if (request.getSession().getAttribute("carrinho") != null) {
                resultado = (Resultado) request.getSession().getAttribute("carrinho");
                String txtId = request.getParameter("idItem");
                double vlrUnit = Double.parseDouble(request.getParameter("vlrUnit"));
                String subtotal = request.getParameter("subtotal");
                int qtde = Integer.parseInt(request.getParameter("qtde"));
                int id = 0;

                if (txtId != null && !txtId.trim().equals("")) {
                    id = Integer.parseInt(txtId);
                }
                for (EntidadeDominio e : resultado.getEntidades()) {
                    carrinho = (CarrinhoCompra) e;
                }

                List<AbstractItem> entidades = carrinho.getItens();

                if (entidades != null) {

                    entidades.get(id).setQuantidade(Integer.parseInt(request.getParameter("qtde")));
                }
                carrinho.setTotal((vlrUnit * qtde));
            }
        }

        return carrinho;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher d = null;

        String operacao = request.getParameter("operacao");

        if (resultado.getMsg() == null && operacao.equals("ADICIONAR")) {

            request.getSession().setAttribute("carrinho", resultado);
            d = request.getRequestDispatcher("cart.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("EXCLUIRITEM")) {

            request.getSession().setAttribute("carrinho", resultado);
            d = request.getRequestDispatcher("cart.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("ATUALIZAR")) {

            request.getSession().setAttribute("carrinho", resultado);
            d = request.getRequestDispatcher("cart.jsp");
        }

        d.forward(request, response);
    }

}
