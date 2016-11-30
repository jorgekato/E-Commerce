<%-- 
    Document   : selecionaproduto
    Created on : 19/06/2016, 15:54:50
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

        <input type="hidden" name="graficoproduto" value="1" />
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
    <form action="${pageContext.request.contextPath}/ServletGrafico3" method="POST">
        <TABLE class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" BORDER="5"    WIDTH="100%"   CELLPADDING="4" CELLSPACING="3">
            <TR>
                <TH COLSPAN="2"><BR>
                    <H3>PRODUTOS</H3>
                </TH>
            </TR>

            <TR>
                <TH></TH>
                <TH>Produto:</TH>
            </TR>


            <%
                    for (int i = 0; i < entidades.size(); i++) {
                        Produto p = (Produto) entidades.get(i);
                        sbRegistro.setLength(0);
                        sbLink.setLength(0);

                        //	<a href="nome-do-lugar-a-ser-levado">descrição</a>
                        sbRegistro.append("<TR ALIGN='CENTER'>");

                        sbRegistro.append("<TD>");
                        sbRegistro.append("<input type=\"checkbox\" name=\"txtId\" value=\"");
                        sbRegistro.append(p.getId());
                        sbRegistro.append("\" />");
                        sbRegistro.append("</TD>");

                        sbRegistro.append("<TD>");
                        sbRegistro.append(sbLink.toString());
                        sbRegistro.append(p.getNome());
                        sbRegistro.append("</a>");
                        sbRegistro.append("</TD>");

                        sbRegistro.append("</TR>");

                        out.print(sbRegistro.toString());

                    }
                }


            %>
        </TABLE>
        <input type="submit" value="grafico2" name="operacao" /></p>
    </form>
    <%            }//if(resultado)
%>
</div>
<!---->
