<%-- 
    Document   : estoquegeral
    Created on : 30/10/2016, 17:15:37
    Author     : Henrique Padovani
--%>

<%@page import="e_commer.core.util.FormatDouble"%>
<%@page import="e_commer.filtroAnalise.FiltroEstoqueGeral"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.filtroAnalise.FiltroProdutoVendaPeriodo"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<div class="content">
    <div class="account-in">
        <div>
            <header><h1>Estoque Geral</h1></header></div><br><br><br>
                <%                    Resultado produtos = (Resultado) request.getAttribute("estoqueGeral");
                    if (produtos.getEntidades().size() > 0) {

                %>
        <TABLE class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" BORDER="6"    WIDTH="90%"   CELLPADDING="4" CELLSPACING="3">
            <TR>
                <TH COLSPAN="8"><BR>
                    <H3>PRODUTOS</H3>
                </TH>
            </TR>

            <TR>
                <TH>ID</TH>
                <TH>Produto:</TH>                
                <TH>Marca:</TH> 
                <TH>Descri��o:</TH>
                <TH>Categoria:</TH>
                <TH>Quantidade:</TH>
                <TH>Estoque M�nimo:</TH>
                <TH>Pre�o Unit.:</TH>
            </TR>


            <%                List<EntidadeDominio> entidades = produtos.getEntidades();

                if (entidades != null) {
                    for (int i = 0; i < entidades.size(); i++) {

                        FiltroEstoqueGeral f = (FiltroEstoqueGeral) entidades.get(i);
            %>
            <tr align="center">
                <td><%= f.getId()%></td>
                <td><a href="SalvarProduto?txtId=<%= f.getId()%>&operacao=VISUALIZAR"><%= f.getNome()%></a></td>
                <td><%= f.getMarca()%></a></td>
                <td><%= f.getDescricao()%></a></td>
                <td><%= f.getNomeCategoria()%></a></td>
                <td><%= f.getQuantidade()%></td>
                <td><%= f.getEstoqueMin()%></td>
                <td>R$<%= FormatDouble.formataDouble(f.getPrecoUnit()) %></td>
            </tr>
            <%
                    }
                }
            %>
        </TABLE>
        <%        } else {
        %>
        <h4 class="title">N�o h� produtos com estoque igual ou menor que o m�nimo.</h4>


        <%
            }
        %>        
    </div>	
</div>
<!---->