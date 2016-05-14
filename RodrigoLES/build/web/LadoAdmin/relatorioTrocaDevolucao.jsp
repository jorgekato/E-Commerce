<%-- 
    Document   : relatorioTrocaDevolucao
    Created on : 11/05/2016, 14:37:33
    Author     : Jorge
--%>

<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="e_commer.dominio.Cliente"%>
<%@page import="e_commer.dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="e_commer.core.aplicacao.Resultado, e_commer.dominio.*, java.util.*"%>


<div class="content">
    <h3>Detalhes Troca e Devolução</h3>
    <%        //Resultado resultado = (Resultado) session.getAttribute("resultado");
        resultado = (Resultado) request.getAttribute("trocadevolucao");

        if (resultado != null) {
            TrocaDevolucao td = (TrocaDevolucao) resultado.getEntidades().get(0);
    %>
    <div class="clearfix"> </div>
    <form action="SalvarTrocaDevolucao" method="post">
        <input type="hidden" name="txtIdPed" value="<%= td.getPedido().getId()%>"/>
        <input type="hidden" name="txtIdtd" value="<%= td.getId()%>"/>

        <table border="3" width="1000%" CELLPADDING="4" CELLSPACING="3">
            <tr>
                <th colspan="6"><strong>Pedido</strong></th>
            </tr>
            <tr>
                <td>Nº Pedido</td>
                <td>Data</td>
                <td>Cliente</td>
                <td>Email</td>
                <td>Status</td>
            </tr>
            <tr>
                <td><%= td.getPedido().getId()%></td>
                <td><%= ConverteDate.converteDateString(td.getDtCadastro())%></td>
                <td><%= td.getPedido().getCliente().getNome()%></td>
                <td><%= td.getPedido().getCliente().getEmail()%></td>
                <td><%= td.getStatus()%></td>
            </tr>
        </table>
        <h3>Relatório</h3>
        <table>
            <tr>
                <th>Data do Registro</th>
                <th>Comentário</th>
                <th>Status</th>
            </tr>
            <%
                for (int i = 0; i < td.getRelatorio().size(); i++) {
                Relatorio rel = (Relatorio) td.getRelatorio().get(i);
                
            %>
            <tr>
                <td><%= ConverteDate.converteDateString(rel.getDtCadastro())%></td>
                <td><%= rel.getComentario()%> </td>
                <td><%= td.getStatus()%></td>
            </tr>

            <%
                }
        }
            %>
        </table>
        
    </form>
</div>





