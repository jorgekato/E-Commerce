
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.ItemProduto"%>
<%@page import="e_commer.dominio.ItemArtesanato"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="e_commer.dominio.Cliente"%>
<%@page import="e_commer.dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="e_commer.core.aplicacao.Resultado, e_commer.dominio.*, java.util.*"%>



<div class="content">
    
        <div class="content-bottom">
            <h3>Detalhe do Pedido</h3>
            <%                //Resultado resultado = (Resultado) session.getAttribute("resultado");
                Resultado pedido = (Resultado) request.getAttribute("pedidos");

                if (pedido != null && pedido.getMsg() != null) {
                    out.print(pedido.getMsg());
                }

                if (pedido != null) {
                    List<EntidadeDominio> entidades = pedido.getEntidades();
                    if (entidades != null) {
                        Pedido p = (Pedido) entidades.get(0);
                        //esta pegando 01 pedido e preciso que ande mais de uma vez no itens do pedido

            %>
            <div class="clearfix"> </div>
            <form action="${pageContext.request.contextPath}/SalvarPedidos" method="post">

                <table border="3" width="100%" CELLPADDING="4" CELLSPACING="3">

                    <tr>
                        <th colspan="7"><strong>Pedidos</strong></th>
                    </tr>
                    <tr>
                        <td>Nº Pedido</td>
                        <td>Data da Compra</td>
                        <td>Cliente</td>
                        <td>Email</td>
                        <td>Status</td>
                    </tr>
                    <tr>
                        <td><%= p.getId()%></td>
                        <td><%= ConverteDate.converteDateString(p.getDtCadastro())%></td>
                        <td><%= p.getCliente().getNome()%></td>
                        <td><%= p.getCliente().getEmail()%></td>
                        <td><%= p.getStatus()%></td>
                    </tr>
                </table>
                    <label for="novoStatus">Novo Status</label>
                        <select name="txtStatus" >
                        <option value="" disabled="" selected></option>
                        <option value="APROVADO">APROVADO</option>
                        <option value="ENVIADO">ENVIADO</option>
                        <option value="AGUARDANDO PAGAMENTO">AGUARDANDO PAGAMENTO</option>
                        <option value="CONCLUIDO">CONCLUIDO</option>
                    </select>
                    <input type="hidden" name="txtId" value="<%= p.getId()%>"/>
                    <input type="submit" name="operacao" value="ALTERAR1"/>
                <h3>Detalhes do Produto</h3>
                <table border="3" width="100%" CELLPADDING="4" CELLSPACING="3">
                    <tr>
                        <td>Cod. Produto</td>
                        <td>Descrição</td>
                        <td>Quantidade</td>
                        <td>Valor Unit.</td>
                        <td>Sub Total</td>
                    </tr>
                    <%
                        for (int i = 0; i < p.getItens().size(); i++) {
                            if (ItemProduto.class.getName().equals(p.getItens().get(i).getClass().getName())) {
                                ItemProduto item = (ItemProduto) p.getItens().get(i);

                    %> 
                    <tr>
                        <td><%= item.getProduto().getId()%></td>
                        <td><%= item.getProduto().getNome()%></td>
                        <td><%= item.getQuantidade()%></td>
                        <td><%= item.getProduto().getPrecoUnit()%></td>
                        <td><%= item.getQuantidade() * item.getProduto().getPrecoUnit()%></td>
                    </tr>

                    <%
                    } else {
                        ItemArtesanato itemA = (ItemArtesanato) p.getItens().get(i);

                    %>
                    <tr>
                        <td><%= itemA.getArtesanato().getId()%></td>
                        <td><%= itemA.getArtesanato().getNome()%></td>
                        <td><%= itemA.getQuantidade()%></td>
                        <td><%= itemA.getArtesanato().getPrecoUnit()%></td>
                        <td><%= itemA.getQuantidade() * itemA.getArtesanato().getPrecoUnit()%></td>
                    </tr>
                </table>
                    
            </form>

            <%
                            }
                        }
                    }
                }
            %>
        </div>

    </div>


    <div class="clearfix"> </div>
</div>
