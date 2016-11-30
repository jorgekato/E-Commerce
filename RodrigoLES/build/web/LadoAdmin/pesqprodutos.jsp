<%-- 
    Document   : pesqprodutos
    Created on : 21/03/2016, 00:26:13
    Author     : Henrique
--%>

<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.dominio.Produto"%>



<div class="content">
    <h1>Pesquisa Produto</h1>
    <%        resultado = (Resultado) request.getAttribute("resultado");
    %>

    <form action="${pageContext.request.contextPath}/SalvarProduto" method="post">

        <label for="txtId">Id:</label> 
        <input type="text" id="txtId" name="txtId" /> 
        <label for="nome">DESCRIÇÃO:</label> 
        <input type="text" id="nome" name="nome" /> 
        <input type="submit" id="operacao" name="operacao" value="CONSULTAR" />
    </form>




    <%
        if (resultado != null && resultado.getMsg() != null) {
            out.print(resultado.getMsg());
        }

    %>
    <BR>

    <%        if (resultado != null) {
            List<EntidadeDominio> entidades = resultado.getEntidades();
            StringBuilder sbRegistro = new StringBuilder();
            StringBuilder sbLink = new StringBuilder();

            if (entidades != null) {
    %>
    <TABLE class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" BORDER="5"    WIDTH="100%"   CELLPADDING="4" CELLSPACING="3">
        <TR>
            <TH COLSPAN="6"><BR>
                <H3>PRODUTOS</H3>
            </TH>
        </TR>

        <TR>
            <TH>ID:</TH>
            <TH>Produto:</TH>
            <TH>Qtde Estoque</TH>
            <TH>Valor Unit</TH>
            <TH>Categoria</TH>
            <TH>Status:</TH>
        </TR>


        <%
            for (int i = 0; i < entidades.size(); i++) {
                Produto p = (Produto) entidades.get(i);
                sbRegistro.setLength(0);
                sbLink.setLength(0);

                //	<a href="nome-do-lugar-a-ser-levado">descrição</a>
                sbRegistro.append("<TR ALIGN='CENTER'>");

                sbLink.append("<a href=SalvarProduto?");
                sbLink.append("txtId=");
                sbLink.append(p.getId());
                sbLink.append("&");
                sbLink.append("operacao=");
                sbLink.append("VISUALIZAR");

                sbLink.append(">");

                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                sbRegistro.append(p.getId());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                sbRegistro.append(p.getNome());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                sbRegistro.append(p.getQuantidade());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                sbRegistro.append(p.getPrecoUnit());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                sbRegistro.append(p.getCategoria().getNomeCategoria());
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                sbRegistro.append("<TD>");
                sbRegistro.append(sbLink.toString());
                if (p.getFlg_ativo()) {
                    sbRegistro.append("Ativo");
                } else {
                    sbRegistro.append("Inativo");
                }
                sbRegistro.append("</a>");
                sbRegistro.append("</TD>");

                sbRegistro.append("</TR>");

                out.print(sbRegistro.toString());

            }
        } else {
        %>
        <h3>Não há produtos a serem exibidos.</h3>
        <%
                }
            }

        %>
    </TABLE>
</div>
<!---->
