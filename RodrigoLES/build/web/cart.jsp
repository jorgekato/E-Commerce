<%-- 
    Document   : cart
    Created on : 06/04/2016, 11:50:01
    Author     : Henrique
--%>
<%@page import="e_commer.core.util.FormatDouble"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<div class="content">
    <div class="check-out">
        <H3>
            <%                if (carrinho != null && carrinho.getMsg() != null) {
                    out.print(carrinho.getMsg());
                }
            %>
        </h3>
        <br><br><br>

        <%      if (carrinho != null) {
        %>
        <form class="" action="SalvarCarrinho" method="post">
            <TABLE class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" BORDER="5"    WIDTH="100%"   CELLPADDING="4" CELLSPACING="3">
                <TR>
                    <TH COLSPAN="7"><BR>
                        <H3>Carrinho de compras</H3>
                    </TH>
                </TR>

                <TR>
                    <TH ALIGN='CENTER'></TH>
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
                    <td><input type="text" name="subtotal" value="<%= FormatDouble.formataDouble(item.getArtesanato().getPrecoUnit() * item.getQuantidade())%>"/></td>
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
                    <td><input type="text" name="subtotal" value="<%= FormatDouble.formataDouble(item.getProduto().getPrecoUnit() * item.getQuantidade())%>"/></td>
                    <td><input type="submit" name="operacao" value="ATUALIZAR"/></td>
                    <td><a href="SalvarCarrinho?idItem=<%= i%>&operacao=EXCLUIRITEM">Remover</a></td>
                </tr>
                <%
                                }//else
                            }//for i
                        }//if entidade != null
                    }//if carrinho != null
%>
                <TR>
                    <TD COLSPAN="5"></TD>
                    <TD>Total Parcial:<input type="text" name="total" value="<%= FormatDouble.formataDouble(carrinhos.getTotal())%>"/></TD>
                    <TD></TD>
                </TR>
                <%if (cliente != null) {%>
                <TR>

                    <Td COLSPAN="5"><input type="checkbox" name="ckbox" value="sim"/> Utilizar Vale Credito 
                        <input type="text" name="txtValeCredito" value="<%
                            if (carrinhos != null && carrinhos.getCredito() != null) {
                                out.print(carrinhos.getCredito().getCodigo());
                            }
                               %>"/>
                    </Td>
                    <%
                        if (carrinhos != null && carrinhos.getCredito() != null) {
                    %>
                    <TD>Desconto: <input type="text" name="txtDesconto" value="<%= FormatDouble.formataDouble(carrinhos.getCredito().getSaldo())%>"</TD>
                        <%} else {%>
                    <TD></TD>
                        <%}%>
                    <TD><input type="submit" name="operacao" value="VALIDAR" /></TD>
                </TR>
                <%
                    if (carrinhos != null && carrinhos.getCredito() != null) {
                %>
                <TR>
                    <TD COLSPAN="5"></TD>
                    <TD>Total:<input type="text" name="total" value="<%= FormatDouble.formataDouble(carrinhos.getTotal() - carrinhos.getCredito().getSaldo())%>"/></TD>
                    <TD></TD>
                </TR>
                <%}
                    }//if cliente != null%>
                <TR>
                    <TD COLSPAN="5"></TD>
                    <TD>
                        <a href="${pageContext.request.contextPath}/cartconfirmar.jsp">Comprar</a>



                    </TD>
                    <TD></TD>
                </TR>        
            </TABLE>
        </form>
        <br />
        <br/>
        <%
        } else {
        %>
        <h4 class="title">Carrinho vazio!</h4>
        <p class="cart-out">Voce nao possui itens no carrinho de compras.<br>Clique<a href="index.jsp"> aqui</a> para continuar comprando</p>
            <%}
            %>

    </div>


</div>

