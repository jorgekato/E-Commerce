package e_commer.controle.web.vh.impl;

import e_commer.controle.web.command.ICommand;
import e_commer.controle.web.command.impl.ConsultarCommand;
import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.CarrinhoCompra;
import e_commer.dominio.AbstractItem;
import e_commer.dominio.Artesanato;
import e_commer.dominio.Cliente;
import e_commer.dominio.Credito;
import e_commer.dominio.Endereco;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.ItemArtesanato;
import e_commer.dominio.ItemProduto;
import e_commer.dominio.Produto;
import java.io.IOException;
import java.util.List;
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

        if (operacao.equals("ADICIONAR") || operacao.equals("CONTINUAR")) {

            if (request.getSession().getAttribute("carrinho") != null) {
                resultado = (Resultado) request.getSession().getAttribute("carrinho");
                for (EntidadeDominio e : resultado.getEntidades()) {
                    carrinho = (CarrinhoCompra) e;
                }
            } else {
                carrinho = new CarrinhoCompra();
            }

            if (operacao.equals("CONTINUAR")) {
                String endEntrega = request.getParameter("endEntrega");
                Endereco end = new Endereco();
                if (endEntrega != null && !endEntrega.trim().equals("")) {
                    end.setId(Integer.parseInt(endEntrega));
                }
                carrinho.setEndereco(end);
            } else {
                String tipoClasse = request.getParameter("tipo");
                String txtId = request.getParameter("txtId");
                List<AbstractItem> itens = carrinho.getItens();
                for (int i = 0; i < itens.size(); i++) {
                    if (itens.get(i).getTipo_item().equals(tipoClasse)) {
                        if (ItemArtesanato.class.getName().equals(itens.get(i).getClass().getName())) {
                            ItemArtesanato item = new ItemArtesanato();
                            item = (ItemArtesanato) itens.get(i);
                            if (item.getArtesanato().getId() == Integer.parseInt(txtId)) {//ja existe um item igual?
                                return carrinho;
                            }
                        } else if (ItemProduto.class.getName().equals(itens.get(i).getClass().getName())) {
                            ItemProduto item = new ItemProduto();
                            item = (ItemProduto) itens.get(i);
                            if (item.getProduto().getId() == Integer.parseInt(txtId)) {//ja existe um item igual?

                                return carrinho;
                            }
                        }
                    }
                }

                //Adiciona um item de artesanato ao carrinho
                if (tipoClasse.equals("ARTESANATO")) {
                    //String txtId = request.getParameter("txtId");
                    int id = 0;

                    if (txtId != null && !txtId.trim().equals("")) {
                        id = Integer.parseInt(txtId);
                    }
                    Artesanato artesanato = new Artesanato();
                    artesanato.setId(id);
                    ICommand command = new ConsultarCommand();
                    resultado = command.execute((EntidadeDominio) artesanato);

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
                    ICommand command = new ConsultarCommand();
                    resultado = command.execute((EntidadeDominio) produto);

                    ItemProduto item = new ItemProduto();

                    for (EntidadeDominio e : resultado.getEntidades()) {
                        if (e.getId() == id) {
                            item.setProduto((Produto) e);
                        }
                    }
                    item.setQuantidade(Integer.parseInt(request.getParameter("qtde")));
                    item.setTipo_item(tipoClasse);
                    item.setValorUnit(item.getProduto().getPrecoUnit());//seta o valor unitario

                    carrinho.adiciona(item);
                }
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
            }
        } else if (operacao.equals("VALIDAR")) {
            if (request.getParameter("txtValeCredito") != null) {
                if (request.getSession().getAttribute("carrinho") != null) {
                    //pega o carrinho da sessão
                    resultado = (Resultado) request.getSession().getAttribute("carrinho");
                    for (EntidadeDominio e : resultado.getEntidades()) {
                        carrinho = (CarrinhoCompra) e;
                    }
                    
                    Credito credito = new Credito();
                    Cliente cli = (Cliente) request.getSession().getAttribute("usuario");

                    String vale = request.getParameter("txtValeCredito");
                    if (vale != null && !vale.trim().equals("")) {
                        credito.setCodigo(vale);
                    }
                    credito.setCliente(cli);

                    ICommand command = new ConsultarCommand();

                    resultado = command.execute(credito);

                    if (resultado.getEntidades().size() != 0) {
                        for (EntidadeDominio e : resultado.getEntidades()) {
                            Credito c = (Credito) e;
                            if (c.getFlgAtivo()) {
                                carrinho.setCredito(c);
                            }
                        }
                    }
                }
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
            } else {
                request.getSession().setAttribute("carrinho", null);
                d = request.getRequestDispatcher("cart.jsp");
            }
        } else if (resultado.getMsg() == null && operacao.equals("CONTINUAR")) {

            request.getSession().setAttribute("carrinho", resultado);
            d = request.getRequestDispatcher("cartpayment.jsp");

        } else if (resultado.getMsg() == null && operacao.equals("ATUALIZAR")) {

            request.getSession().setAttribute("carrinho", resultado);
            d = request.getRequestDispatcher("cart.jsp");
        }else if (resultado.getMsg() == null && operacao.equals("VALIDAR")) {
            CarrinhoCompra c = (CarrinhoCompra)resultado.getEntidades().get(0);
            if(c.getCredito()==null)
                resultado.setMsg("Vale Crédito Inválido");
            request.getSession().setAttribute("carrinho", resultado);
            d = request.getRequestDispatcher("cart.jsp");
        } else {
            request.setAttribute("carrinho", resultado);
            d = request.getRequestDispatcher("cart.jsp");
        }

        d.forward(request, response);
    }

}
