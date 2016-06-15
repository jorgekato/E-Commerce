<%-- 
    Document   : cart
    Created on : 06/04/2016, 11:50:01
    Author     : Henrique
--%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<div class="content">
    <div class="check-out">
        <H3>
            <%                
                if (resultado != null && resultado.getMsg() != null) {
                    out.print(resultado.getMsg());
                }
            %>
        </h3>
        <br><br><br>

        <%      if (carrinho != null) {
        %>
        <form action="SalvarCarrinho" method="post">
            <TABLE BORDER="5"    WIDTH="100%"   CELLPADDING="4" CELLSPACING="3">
                <TR>
                    <TH COLSPAN="6"><BR>
                        <H3>Carrinho de compras</H3>
                    </TH>
                </TR>

                <TR>
                    <TH ALIGN='CENTER'><input type="checkbox"/></TH>
                    <TH>Nome</TH>
                    <TH>Quantidade</TH>                
                    <TH>Valor Unit</TH>
                    <TH>Sub Total</TH>
                    <TH>UpDate:</TH>
                    <TH>Excluir todos:</TH>
                </TR>
                <%
                    for (EntidadeDominio e : carrinho.getEntidades()) {
                        carrinhos = (CarrinhoCompra) e;
                    }

                    if (carrinhos != null) {
                        List<AbstractItem> entidades = carrinhos.getItens();
                        if (entidades != null) {
                            for (int i = 0; i < entidades.size(); i++) {

                                if (ItemArtesanato.class.getName().equals(entidades.get(i).getClass().getName())) {
                                    ItemArtesanato item = (ItemArtesanato) entidades.get(i);
                %>

                <tr align="center">
                    <td><input type="hidden" name="idItem" value="<%= i%>"/></td>
                    <td><a href="SalvarArtesanato?txtId=<%= item.getArtesanato().getId()%>&operacao=VISUALIZAR1"><%= item.getArtesanato().getNome()%></a></td>
                    <td><input type="number" name="qtde" min="1" max="<%= 10%>" value="<%= item.getQuantidade()%>"/></td>
                    <td><input type="text" name="vlrUnit" value="<%= item.getArtesanato().getPrecoUnit()%>"/></td>
                    <td><input type="text" name="subtotal" value="<%= item.getArtesanato().getPrecoUnit() * item.getQuantidade()%>"/></td>
                    <td><input type="submit" name="operacao" value="ATUALIZAR"/></td>
                    <td><a href="SalvarCarrinho?idItem=<%= i%>&operacao=EXCLUIRITEM">Remover</a></td>
                </tr>
                <%
                } else if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {
                    ItemProduto item = new ItemProduto();
                    item = (ItemProduto) entidades.get(i);
                %>
                <tr align="center">
                    <td><input type="hidden" name="idItem" value="<%= i%>"/></td>
                    <td><a href="SalvarProduto?txtId=<%= item.getProduto().getId()%>&operacao=VISUALIZAR1"><%= item.getProduto().getNome()%></a></td>
                    <td><input type="number" name="qtde" min="1" max="<%= item.getProduto().getQtdeMaxVenda()%>" value="<%= item.getQuantidade()%>"/></td>
                    <td><input type="text" name="vlrUnit" value="<%= item.getProduto().getPrecoUnit()%>"/></td>
                    <td><input type="text" name="subtotal" value="<%= item.getProduto().getPrecoUnit() * item.getQuantidade()%>"/></td>
                    <td><input type="submit" name="operacao" value="ATUALIZAR"/></td>
                    <td><a href="SalvarCarrinho?idItem=<%= i%>&operacao=EXCLUIRITEM">Remover</a></td>
                </tr>
                <%
                                }//else
                            }//for i
                        }//if
                    }
                %>
                <TR>
                    <TD COLSPAN="5"></TD>
                    <TD>Total: <%= carrinhos.getTotal()%></TD>
                </TR>
                <TR>
                    <TH COLSPAN="5"><input type="checkbox" name=""/> Utilizar Vale Credito</TH>
                    <TD>
                        <a href="${pageContext.request.contextPath}/cartconfirmar.jsp">Comprar</a>
                        <!--
                        <form action="${pageContext.request.contextPath}/SalvarCarrinho" method="POST">
                            <input type="submit" name="operacao" value="COMPRAR" />
                        </form>
                        -->
                    </TD>
                </TR>               
            </TABLE>
        </form>
        <br />
        <br/>
        <%
        } else {
        %>
        <h4 class="title">Shopping cart is empty</h4>
        <p class="cart-out">You have no items in your shopping cart.<br>Click<a href="index.jsp"> here</a> to continue shopping</p>
            <%}
            %>

    </div>


</div>
