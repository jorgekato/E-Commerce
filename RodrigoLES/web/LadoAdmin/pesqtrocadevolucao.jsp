<%-- 
    Document   : pesqtrocadevolucao
    Created on : 06/05/2016, 14:23:01
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
    <!--Exibe mensagem se cliente foi cadastrado    -->
    <%
        //Resultado resultado = (Resultado) session.getAttribute("resultado");
        Resultado devolucao= (Resultado) request.getAttribute("trocadevolucao");
    %>
    <h1>Consulta de Trocas e Devolu��es</h1>

    <form action="${pageContext.request.contextPath}/SalvarTrocaDevolucao" method="post">
        <p> <label for="id">N� Pedido:</label>
            <input type="text" id="id" name="txtId">
            <label for="nome">Nome Cliente:</label> 
            <input type="text" id="nome" name="txtNome">
            <input type="submit" id="operacao" name="operacao" value="CONSULTAR"/></p>
    </form>
    <%
        if (devolucao != null && devolucao.getMsg() != null) {
            out.print(devolucao.getMsg());
        }
    %>
    <br /><br />
        
    <div class="clearfix"> </div>
    <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="100%" CELLPADDING="4" CELLSPACING="3">
        <tr>
            <th colspan="5" align="center"><strong>Devolu��es</strong></th>
        </tr>
        <tr>
            <th>ID</th>
            <th>N� Pedido</th>
            <th>Cliente</th>
            <th>Data Solicita��o</th>
            <th>Situa��o</th>

        </tr>
        <%
            if (devolucao != null) {
                List<EntidadeDominio> entidades = devolucao.getEntidades();
                if (entidades != null) {
                    for (int i = 0; i < entidades.size(); i++) {
                        TrocaDevolucao td = (TrocaDevolucao) entidades.get(i);
        %>
        <tr>
            <td><%= td.getId()%></td>
            <td><%= td.getPedido().getId()%></td>
            <td><a href="SalvarTrocaDevolucao?operacao=VISUALIZAR&txtIdtd=<%= td.getId()%>"><%= td.getPedido().getCliente().getNome()%></a></td>
            <td><%= ConverteDate.converteDateString(td.getDtCadastro())%></td>
            <td><%= td.getStatus()%></td>
        </tr>
        
        <%
                    }//for
                }//if(entidade)
            }//if(resultado)

        %>        
    </table>
</div>

