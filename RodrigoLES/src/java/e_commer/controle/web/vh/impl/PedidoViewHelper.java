package e_commer.controle.web.vh.impl;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.controle.Fachada;
import e_commer.core.util.ConverteDate;
import e_commer.dominio.AbstractItem;
import e_commer.dominio.CarrinhoCompra;
import e_commer.dominio.Cliente;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.ItemArtesanato;
import e_commer.dominio.ItemProduto;
import e_commer.dominio.Pedido;
import e_commer.dominio.Relatorio;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PedidoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String operacao = request.getParameter("operacao");
        CarrinhoCompra carrinho = null;
        Resultado resultado = null;
        Pedido pedido = null;

        if (operacao.equals("SALVAR")) {

            resultado = (Resultado) request.getSession().getAttribute("carrinho");
            for (EntidadeDominio e : resultado.getEntidades()) {
                carrinho = (CarrinhoCompra) e;
            }

            //Fachada fachada = new Fachada();
            pedido = new Pedido();
            List<AbstractItem> entidades = carrinho.getItens();

            for (int i = 0; i < entidades.size(); i++) {

                if (ItemArtesanato.class.getName().equals(entidades.get(i).getClass().getName())) {
                    //ItemArtesanato item = (ItemArtesanato) entidades.get(i);
                    pedido.adiciona(entidades.get(i));

                } else if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {
                    //ItemProduto item = (ItemProduto) entidades.get(i);
                    pedido.adiciona(entidades.get(i));
                }
            }

           // Resultado clientes = (Resultado) request.getSession().getAttribute("usuario");
            Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
            //Cliente cliente = null;

//            for (EntidadeDominio e : clientes.getEntidades()) {
//                cliente = (Cliente) e;
//            }

            pedido.setCliente(cliente);

            pedido.setPagamento("CARTAO");
            pedido.setServico("SEDEX");
            pedido.setStatus("APROVADO");
            pedido.setTotal(carrinho.getTotal());

        } else if (operacao.equals("ALTERAR1")) { //alterar
            String comentario = request.getParameter("txtComentario");
            String id = request.getParameter("txtId");
            String status = request.getParameter("txtStatus");

            pedido = new Pedido();

            if (id != null && !id.trim().equals("")) {
                pedido.setId(Integer.parseInt(id));
            }
            
            Cliente cli = new Cliente();            
            pedido.setCliente(cli);
            
            
//            Fachada fachada = new Fachada();
//            resultado = fachada.consultar((EntidadeDominio) pedido);
//
//            for (EntidadeDominio e : resultado.getEntidades()) {
//                if (e.getId() == pedido.getId()) {
//                    pedido = (Pedido) e;
//                }
//            }
            
            if (status != null && !status.trim().equals("")) {
                pedido.setStatus(status);
            }
            
            Relatorio rel = new Relatorio();
            rel.setComentario(comentario);
            rel.setStatus(status);
            pedido.addHistorico(rel);
            

        }else if (!operacao.equals("VISUALIZAR") && !operacao.equals("VISUALIZAR1")) {

            String id = request.getParameter("txtId");
            String cli_id = request.getParameter("txtCliId");
            String dtPedido = request.getParameter("txtDtPedido");
            String cliente = request.getParameter("txtCliente");
            String formPagamento = request.getParameter("txtPagamento");
            String numBoleto = request.getParameter("txtNumBoleto");
            String tipoServico = request.getParameter("txtServico");
            String total = request.getParameter("txttotal");
            String status = request.getParameter("txtStatus");
            String comentario = request.getParameter("txtComentario");

            Relatorio rel = new Relatorio();
            rel.setComentario(comentario);
            rel.setStatus(status);
            pedido = new Pedido();
            
            pedido.addHistorico(rel);
            
            if (id != null && !id.trim().equals("")) {
                pedido.setId(Integer.parseInt(id));
            }

            if (dtPedido != null && !dtPedido.trim().equals("")) {
                pedido.setDtCadastro(ConverteDate.converteStringDate(dtPedido));
            }

            Cliente cli = new Cliente();

            if (cli_id != null && !cli_id.trim().equals("")) {
                cli.setId(Integer.parseInt(cli_id));
            }
            
            if (cliente != null && !cliente.trim().equals("")){
                cli.setNome(cliente);
            }
            pedido.setCliente(cli);

            if (formPagamento != null && !formPagamento.trim().equals("")) {
                pedido.setPagamento(formPagamento);
            }

            if (numBoleto != null && !numBoleto.trim().equals("")) {
                pedido.setNumBoleto(numBoleto);
            }

            if (tipoServico != null && !tipoServico.trim().equals("")) {
                pedido.setServico(tipoServico);
            }

            if (total != null && !total.trim().equals("")) {
                pedido.setTotal(Integer.parseInt(total));
            }

            if (status != null && !status.trim().equals("")) {
                pedido.setStatus(status);
            }

        } else {

            String txtId = request.getParameter("txtId");
            String txtIdpro = request.getParameter("txtIdPro");
            int id = 0;
            pedido = new Pedido();
            Cliente cli = new Cliente();
            pedido.setCliente(cli);
            Fachada fachada = new Fachada();

            if (txtId != null && !txtId.trim().equals("")) {
                id = Integer.parseInt(txtId);
                pedido.setId(Integer.parseInt(txtId));
            }
            
            

            pedido.setId(id);

            resultado = fachada.consultar((EntidadeDominio) pedido);

            for (EntidadeDominio e : resultado.getEntidades()) {
                if (e.getId() == id) {
                    pedido = (Pedido) e;
                }
            }
        }

        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher d = null;

        String operacao = request.getParameter("operacao");

        if (resultado.getMsg() == null && operacao.equals("SALVAR")) {
            resultado.setMsg("Compra Finalizada com Sucesso!");
            request.getSession().setAttribute("carrinho", null);
            request.setAttribute("pedidos", resultado);
            d = request.getRequestDispatcher("cartmsg.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("ALTERAR")) {
            resultado.setMsg("Pedido alterado enviado para análise!");
            request.setAttribute("pedidos", resultado);
            d = request.getRequestDispatcher("cartmsg.jsp"); // mudar para outrar tela e msn geral

        } else if (resultado.getMsg() == null && operacao.equals("ALTERAR1")) {
            resultado.setMsg("Pedido alterado COM SUCESSO!");
            request.setAttribute("resultado", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pedidodetalhe.jsp"); // mudar para outrar tela e msn geral

        } else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR")) {
            request.setAttribute("pedidos", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pedidodetalhe.jsp");

        } else if (resultado.getMsg() == null && operacao.equals("VISUALIZAR1")) {
            //request.setAttribute("pedidos", resultado.getEntidades().get(0));
            request.setAttribute("pedidos", resultado);
            d = request.getRequestDispatcher("singlepedido.jsp");
        
        } else if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {
            resultado.setMsg("Artesanato excluido com sucesso!");
            request.setAttribute("pedidos", resultado);
            d = request.getRequestDispatcher("msggeral.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {

            request.setAttribute("pedidos", resultado);
            d = request.getRequestDispatcher("LadoAdmin/pesqpedido.jsp");
            
        } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR2")) {//produto para troca/devolução
            int idpro = Integer.parseInt(request.getParameter("txtIdPro"));
            Pedido pedido = (Pedido)resultado.getEntidades().get(0);
            
            for (int i = 0; i < pedido.getItens().size();) {
                if(ItemProduto.class.getName().equals(pedido.getItens().get(i).getClass().getName())){
                    ItemProduto itemp = (ItemProduto)pedido.getItens().get(i);
                    if(itemp.getProduto().getId() != idpro){
                        pedido.remove(itemp);
                    }
                    else
                        i++;
                }
                else{
                    ItemArtesanato itemp = (ItemArtesanato) pedido.getItens().get(i);
                    if(itemp.getArtesanato().getId() != idpro){
                        pedido.remove(itemp);
                    }else
                        i++;
                }
            }//for
            
            request.setAttribute("pedidos", pedido);
            d = request.getRequestDispatcher("pedidoTrocaDevolucao.jsp");
        
        } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {
            request.setAttribute("pedidos", resultado);
            d = request.getRequestDispatcher("meuspedidos.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("HISTORICO")) {
            request.setAttribute("hitoricoPedido", resultado);
            d = request.getRequestDispatcher("LadoAdmin/historicoPedido.jsp");
        
        }else{
            request.setAttribute("mensagem",resultado.getMsg());
            d = request.getRequestDispatcher("cartmsg.jsp");
        }

        d.forward(request, response);

    }

}
