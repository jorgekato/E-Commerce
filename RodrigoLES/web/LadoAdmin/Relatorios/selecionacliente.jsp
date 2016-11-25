<%-- 
    Document   : selecionacliente
    Created on : 19/06/2016, 15:20:09
    Author     : Henrique
--%>

<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="e_commer.dominio.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="e_commer.core.aplicacao.Resultado, e_commer.dominio.*, java.util.*"%>


<div class="content">
    <!--Exibe mensagem se cliente foi cadastrado    -->
    <%        //Resultado resultado = (Resultado) session.getAttribute("resultado");
        resultado = (Resultado) request.getAttribute("clientes");
    %>
    <h1>Consulta de Clientes</h1>

    <form action="${pageContext.request.contextPath}/SalvarCliente" method="post">
        <input type="hidden" name="graficoperfil" value="1" />
        <p> <label for="id">Id.:</label>
            <input type="text" id="id" name="txtId">
            <label for="nome">Nome.:</label> 
            <input type="text" id="nome" name="txtNome">
            <input type="submit" id="operacao" name="operacao" value="CONSULTAR"/></p>
    </form>
    <br/><br/><br/>
    <%
        if (resultado != null && resultado.getMsg() != null) {
            out.print(resultado.getMsg());
        }
    %>

    <div class="clearfix"> </div>
    <%
        if (resultado != null) {
            List<EntidadeDominio> entidades = resultado.getEntidades();
            StringBuilder sbRegistro = new StringBuilder();
            StringBuilder sbLink = new StringBuilder();

            if (entidades != null) {
    %>
    <form action="${pageContext.request.contextPath}/ServletGrafico4" method="POST">
        <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="100%" CELLPADDING="4" CELLSPACING="3">
            <tr>
                <th colspan="2"><strong>CLIENTES</strong></th>
            </tr>
            <tr>
                <td></td>
                <td>NOME</td>

            </tr>
            <%
                        for (int i = 0; i < entidades.size(); i++) {
                            Cliente c = (Cliente) entidades.get(i);
                            sbRegistro.setLength(0);
                            sbLink.setLength(0);

                            sbRegistro.append("<tr align='center'>");

                            //id
                            sbRegistro.append("<TD>");
                            sbRegistro.append("<input type=\"checkbox\" name=\"txtId\" value=\"");
                            sbRegistro.append(c.getId());
                            sbRegistro.append("\" />");
                            sbRegistro.append("</TD>");
                            //nome
                            sbRegistro.append("<TD>");
                            sbRegistro.append(c.getNome());
                            sbRegistro.append("</TD>");

                            sbRegistro.append("</TR>");

                            out.print(sbRegistro.toString());

                        }//for
                    }//if(entidade)
                

            %>          
        </table>
        <input type="submit" value="grafico4" name="operacao" />
    </form>
        <%
            }//if(resultado)
        %>
</div>
