<%-- 
    Document   : historicoPedido
    Created on : 15/05/2016, 18:09:13
    Author     : Jorge
--%>

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
            Resultado pedido = (Resultado) request.getAttribute("hitoricoPedido");

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
            <h3>Relatório</h3>
            <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="1000%" CELLPADDING="4" CELLSPACING="3">
                <tr>
                    <th>Data do Registro</th>
                    <th>Comentário</th>
                    <th>Status</th>
                </tr>
                <%
                    for (int i = 0; i < p.getHistorico().size(); i++) {
                        Relatorio rel = (Relatorio) p.getHistorico().get(i);

                %>
                <tr>
                    <td><%= ConverteDate.converteDateString(rel.getDtCadastro())%></td>
                    <td><%= rel.getComentario()%> </td>
                    <td><%= rel.getStatus()%></td>
                </tr>

                <%
                        }
                    }
            }
                %>
            </table>

        </form>
    </div>