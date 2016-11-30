<%-- 
    Document   : pesqcliente
    Created on : 07/04/2016, 18:14:05
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
    <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="100%" CELLPADDING="4" CELLSPACING="3">
        <tr>
            <th colspan="8"><strong>CLIENTES</strong></th>
        </tr>
        <tr>
            <th>ID</th>
            <th>NOME</th>
            <th>CPF</th>
            <th>SEXO</th>
            <th>SENHA</th>
            <th>DT CADASTRO</th>
            <th>SITUAÇÃO</th>
            <th>EMAIL</th>

        </tr>
        <%
            for (int i = 0; i < entidades.size(); i++) {
                Cliente c = (Cliente) entidades.get(i);
                sbRegistro.setLength(0);
                sbLink.setLength(0);

                sbRegistro.append("<tr align='center'>");

                sbLink.append("<a href=SalvarCliente?");
                sbLink.append("txtId=");
                sbLink.append(c.getId());
                sbLink.append("&");
                sbLink.append("operacao=");
                sbLink.append("VISUALIZAR");

                sbLink.append(">");
                //id
                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                sbRegistro.append(c.getId());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");
                //nome
                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                sbRegistro.append(c.getNome());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");
                //cpf
                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                sbRegistro.append(c.getCpf());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");
                //sexo
                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                sbRegistro.append(c.getSexo());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                //data de nascimento
                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                //String dtNasc = ConverteDate.converteDateString(c.getDtCadastro());
                sbRegistro.append(c.getLogin().getPassword());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                //data de nascimento
                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                String dtCad = ConverteDate.converteDateString(c.getDtCadastro());
                sbRegistro.append(dtCad);
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                //ativo
                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                if (c.getFlg_ativo()) {
                    sbRegistro.append("Ativo");
                } else {
                    sbRegistro.append("Inativo");
                }
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                sbRegistro.append(c.getEmail());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                sbRegistro.append("</TR>");

                out.print(sbRegistro.toString());

            }//for
        }//if(entidade)
        else {
        %>
        <h3>Não há produtos a serem exibidos.</h3>
        <%
                }
            }//if(resultado)

        %>

    </table>
</div>
