/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.impl.negocio;

import e_commer.core.IStrategy;
import e_commer.core.impl.dao.ProdutoDAO;
import e_commer.dominio.AbstractItem;
import e_commer.dominio.CarrinhoCompra;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.ItemArtesanato;
import e_commer.dominio.ItemProduto;
import e_commer.dominio.Pedido;
import e_commer.dominio.Produto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Henrique
 */
public class ValidadorQtdeEstoque implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if (entidade != null) {

            if (entidade.getClass().getName().equals(Pedido.class.getName())) {
                Pedido pedido = (Pedido) entidade;
                List<AbstractItem> entidades = pedido.getItens();

                for (int i = 0; i < entidades.size(); i++) {

                    if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {

                        ItemProduto itpPro = (ItemProduto) entidades.get(i);
                        ProdutoDAO proDAO = new ProdutoDAO();
                        Produto p = proDAO.consultar(itpPro.getProduto().getId());
                        if (p.getQuantidade() < itpPro.getQuantidade()) {
                            return "Desculpe, possuimos apenas " + p.getQuantidade() + " itens de " + p.getNome() + "!";
                        }
                    }
                }
            }else if (entidade.getClass().getName().equals(CarrinhoCompra.class.getName())) {
                CarrinhoCompra carrinho = (CarrinhoCompra) entidade;
                List<AbstractItem> entidades = carrinho.getItens();

                for (int i = 0; i < entidades.size(); i++) {

                    if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {

                        ItemProduto itpPro = (ItemProduto) entidades.get(i);
                        ProdutoDAO proDAO = new ProdutoDAO();
                        Produto p = proDAO.consultar(itpPro.getProduto().getId());
                        if (p.getQuantidade() < itpPro.getQuantidade()) {
                            return "Desculpe, possuimos apenas " + p.getQuantidade() + " itens de " + p.getNome() + "!";
                        }
                    }
                }
            }
            
        } else {
            return "Entidade: " + entidade.getClass().getCanonicalName() + " nula!";
        }

        return null;
    }

}
