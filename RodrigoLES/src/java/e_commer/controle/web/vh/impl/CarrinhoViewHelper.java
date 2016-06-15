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
            } else {
                carrinho = new CarrinhoCompra();
            }

            String tipoClasse = request.getParameter("tipo");
            String txtId = request.getParameter("txtId");
            List<AbstractItem> itens = carrinho.getItens();
            for (int i = 0; i < itens.size(); i++) {
                if (ItemArtesanato.class.getName().equals(itens.get(i).getClass().getName())) {
                    ItemArtesanato item = new ItemArtesanato();
                    item = (ItemArtesanato) itens.get(i);
                    if(item.getArtesanato().getId() == Integer.parseInt(txtId)){//ja existe um item igual?
//                        int qtde = item.getQuantidade() + 1;
//                        item.setQuantidade(qtde);
                        return carrinho;
                    }
                }else if (ItemProduto.class.getName().equals(itens.get(i).getClass().getName())) {
                    ItemProduto item = new ItemProduto();
                    item = (ItemProduto) itens.get(i);
                    if(item.getProduto().getId() == Integer.parseInt(txtId)){//ja existe um item igual?
//                        int qtde = item.getQuantidade() + 1;
//                        item.setQuantidade(qtde);
                        return carrinho;
                    }
                }
            }
             Fachada fachada = new Fachada();

            //Adiciona um item de artesanato ao carrinho
            if (tipoClasse.equals("ARTESANATO")) {
                //String txtId = request.getParameter("txtId");
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
                item.setValorUnit(item.getArtesanato().getPrecoUnit());//seta o valor unitario
                //carrinho.setTotal(carrinho.getTotal() + (item.getQuantidade() * item.getArtesanato().getPrecoUnit()));
                carrinho.adiciona(item);

            } //Adiciona um item de produto ao carrinho
            else if (tipoClasse.equals("PRODUTO")) {
               // String txtId = request.getParameter("txtId");
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
                item.setValorUnit(item.getProduto().getPrecoUnit());//seta o valor unitario
                //carrinho.setTotal(carrinho.getTotal() + (item.getProduto().getPrecoUnit() * item.getQuantidade()));
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
                    if (entidades.size() == 0) {
                        carrinho = null;
                    }
                }

            }
        } else if (operacao.equals("ATUALIZAR")) {

            if (request.getSession().getAttribute("carrinho") != null) {
                resultado = (Resultado) request.getSession().getAttribute("carrinho");

                String[] qtde = request.getParameterValues("qtde");
                String[] txtId = request.getParameterValues("idItem");

                for (EntidadeDominio e : resultado.getEntidades()) {
                    carrinho = (CarrinhoCompra) e;
                }

                List<AbstractItem> entidades = carrinho.getItens();

                if (entidades != null) {

                    for (int i = 0; i < qtde.length; i++) {
                        int id = 0;

                        if (txtId[i] != null && !txtId[i].trim().equals("")) {
                            id = Integer.parseInt(txtId[i]);
                        }
                        entidades.get(id).setQuantidade(Integer.valueOf(qtde[i]));
                    }
                }

//                double vlrUnit = Double.parseDouble(request.getParameter("vlrUnit"));
//
//                
//                
//                carrinho.setTotal((vlrUnit * qtde));
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
            if (resultado.getEntidades() != null) {
                request.getSession().setAttribute("carrinho", resultado);
                d = request.getRequestDispatcher("cart.jsp");
            }else{
                request.getSession().setAttribute("carrinho", null);
                d = request.getRequestDispatcher("cart.jsp");
            }
        } else if (resultado.getMsg() == null && operacao.equals("ATUALIZAR")) {

            request.getSession().setAttribute("carrinho", resultado);
            d = request.getRequestDispatcher("cart.jsp");
        }else{
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("cart.jsp");
        }

        d.forward(request, response);
    }

}
