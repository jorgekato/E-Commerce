<%-- 
    Document   : pesqcategoria
    Created on : 20/03/2016, 16:55:13
    Author     : Henrique
--%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.dominio.Categorias"%>



<div class="content">
    <h1>Pesquisa Categoria</h1>
    <%
        resultado = (Resultado) request.getAttribute("resultado");
    %>

    <form action="${pageContext.request.contextPath}/SalvarCategoria" method="post">

        <label for="txtId">Id:</label> 
        <input type="text" id="txtId" name="txtId" /> 
        <label for="txtNome">DESCRIÇÃO:</label> 
        <input type="text" id="txtNome" name="txtNome" /> 
        <input type="submit" id="operacao" name="operacao" value="CONSULTAR" />
    </form>




    <%
        if (resultado != null && resultado.getMsg() != null) {
            out.print(resultado.getMsg());
        }

    %>
    <BR>

    <TABLE class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
        <TR>
            <TH COLSPAN="4"><BR>
                <H3>Categorias</H3>
            </TH>
        </TR>

        <TR>
            <TH>ID:</TH>
            <TH>Categoria:</TH>
            <TH>Descriçao:</TH>
            <TH>Status:</TH>
        </TR>


        <%       if (resultado != null) {
                List<EntidadeDominio> entidades = resultado.getEntidades();
                StringBuilder sbRegistro = new StringBuilder();
                StringBuilder sbLink = new StringBuilder();

                if (entidades != null) {
                    for (int i = 0; i < entidades.size(); i++) {
                        Categorias p = (Categorias) entidades.get(i);
                        sbRegistro.setLength(0);
                        sbLink.setLength(0);

                        //<a href="nome-do-lugar-a-ser-levado">descrição</a>
                        sbRegistro.append("<TR ALIGN='CENTER'>");

                        sbLink.append("<a href=SalvarCategoria?");
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
                        sbRegistro.append(p.getNomeCategoria());
                        sbRegistro.append("</a>");
                        sbRegistro.append("</TD>");

                        sbRegistro.append("<TD>");
                        sbRegistro.append(sbLink.toString());
                        sbRegistro.append(p.getDescricao());
                        sbRegistro.append("</a>");
                        sbRegistro.append("</TD>");
                        
                        sbRegistro.append("<TD>");
                        sbRegistro.append(sbLink.toString());
                        if(p.getFlg_ativo())
                            sbRegistro.append("Ativo");
                        else
                            sbRegistro.append("Inativo");
                        sbRegistro.append("</a>");
                        sbRegistro.append("</TD>");

                        sbRegistro.append("</TR>");

                        out.print(sbRegistro.toString());

                    }
                }

            }

        %>
    </TABLE>
</div>
<!---->