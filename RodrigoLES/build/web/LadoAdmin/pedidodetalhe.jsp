
<%@page import="e_commer.core.util.FormatDouble"%>
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

            <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="100%" CELLPADDING="4" CELLSPACING="3">

                <tr>
                    <th colspan="7"><strong>Pedidos</strong></th>
                </tr>
                <tr>
                    <th>Nº Pedido</th>
                    <th>Data da Compra</th>
                    <th>Cliente</th>
                    <th>Email</th>
                    <th>Status</th>
                </tr>
                <tr>
                    <td><%= p.getId()%></td>
                    <td><%= ConverteDate.converteDateString(p.getDtCadastro())%></td>
                    <td><%= p.getCliente().getNome()%></td>
                    <td><%= p.getCliente().getEmail()%></td>
                    <td><%= p.getStatus()%></td>
                </tr>
            </table>
                <p><label for="novoStatus">Novo Status</label>
            <select name="txtStatus" >
                <option value="" disabled="" selected></option>
                <option value="APROVADO">APROVADO</option>
                <option value="ENVIADO">ENVIADO</option>
                <option value="CANCELADO">CANCELADO</option>
                <option value="REENVIADO">REENVIADO</option>
                <option value="AGUARDANDO PAGAMENTO">AGUARDANDO PAGAMENTO</option>
                <option value="CONCLUIDO">CONCLUIDO</option>
            </select></p>
            <p><label for="comentario">Comentario</label>
                <textarea name="txtComentario" cols="40"></textarea></p>
            
            <input type="hidden" name="txtId" value="<%= p.getId()%>"/>
            <input type="submit" name="operacao" value="ALTERAR1"/>
            <input type="submit" name="operacao" value="HISTORICO"/>
            <h3>Detalhes do Produto</h3>
            <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="100%" CELLPADDING="4" CELLSPACING="3">
                <tr>
                    <th>Cod. Produto</th>
                    <th>Descrição</th>
                    <th>Quantidade</th>
                    <th>Valor Unit.</th>
                    <th>Sub Total</th>
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
                    <td>R$ <%= FormatDouble.formataDouble(item.getValorUnit())%></td>
                    <td>R$ <%= FormatDouble.formataDouble(item.getQuantidade() * item.getValorUnit())%></td>
                </tr>

                <%
                } else {
                    ItemArtesanato itemA = (ItemArtesanato) p.getItens().get(i);
                %>
                <tr>
                    <td><%= itemA.getArtesanato().getId()%></td>
                    <td><%= itemA.getArtesanato().getNome()%></td>
                    <td><%= itemA.getQuantidade()%></td>
                    <td>R$ <%= FormatDouble.formataDouble(itemA.getValorUnit())%></td>
                    <td>R$ <%= FormatDouble.formataDouble(itemA.getQuantidade() * itemA.getValorUnit())%></td>
                </tr>


                <%
                                }
                            }
                        }
                    }
                %>
            </table>
        </form>
    </div>

</div>



