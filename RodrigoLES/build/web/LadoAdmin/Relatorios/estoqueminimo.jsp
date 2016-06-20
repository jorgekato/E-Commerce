<%-- 
    Document   : estoqueminimo
    Created on : 21/05/2016, 12:16:05
    Author     : Henrique
--%>

<%@page import="e_commer.filtroAnalise.FiltroEstoqueMinimo"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.filtroAnalise.FiltroProdutoVendaPeriodo"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<div class="content">
    <div class="account-in">
        <div>
            <header><h1>Produtos com o estoque menor ou igual ao mínimo</h1></header></div><br><br><br>
                <%                    Resultado produtos = (Resultado) request.getAttribute("produtosmin");
                    if (produtos != null) {
                %>
        <TABLE BORDER="6"    WIDTH="90%"   CELLPADDING="4" CELLSPACING="3">
            <TR>
                <TH COLSPAN="7"><BR>
                    <H3>PRODUTOS</H3>
                </TH>
            </TR>

            <TR>
                <TH></TH>
                <TH>Produto:</TH>
                <TH>Quantidade:</TH>
                <TH>Estoque Mínimo</TH>
                <TH>Quantidade Venda Ult. 3 Meses</TH>
                <TH>Quantidade Pedido Mínimo</TH>
                <TH>Valor Pedido Mínimo</TH>
            </TR>


            <%
                List<EntidadeDominio> entidades = produtos.getEntidades();

                if (entidades != null) {
                    for (int i = 0; i < entidades.size(); i++) {

                        FiltroEstoqueMinimo f = (FiltroEstoqueMinimo) entidades.get(i);
            %>
            <tr align="center">
                <td><input type="hidden" name="idItem" value="<%= f.getId()%>"/></td>
                <td><a href="SalvarProduto?txtId=<%= f.getId()%>&operacao=VISUALIZAR"><%= f.getNome()%></a></td>
                <td><%= f.getQuantidade()%></td>
                <td><%= f.getEstoqueMin()%></td>
                <td><a href="ServletGrafico3?txtId=<%= f.getId()%>&operacao=grafico2&txtComparativo=!Comparar"><%= f.getQtdeVendida()%></a></td>
                <td><%= (f.getEstoqueMin() - f.getQuantidade()) %></td>
                <td>R$<%= (f.getEstoqueMin() - f.getQuantidade()) * f.getPrecoUnit()%></td>

            </tr>
            <%

                }
            %>
            <TR>
                <TD COLSPAN="6"></TD>
                <TD>Total: R$<%= ((FiltroEstoqueMinimo) entidades.get(0)).getTotal(entidades)  %></TD>
            </TR>
            <%
                }


            %>
        </TABLE>
        <%        } else {
        %>
        <h4 class="title">Não há produtos com estoque igual ou menor que o mínimo.</h4>
        

        <%
            }
        %>


    </div>	
</div>
<!---->