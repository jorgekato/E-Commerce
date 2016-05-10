<%-- 
    Document   : cart
    Created on : 06/04/2016, 11:50:01
    Author     : Henrique
--%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<div class="content">
    <div class="check-out">


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
                    }
                    if (carrinhos != null) {
                        List<AbstractItem> entidades = carrinhos.getItens();
                        if (entidades != null) {
                            for (int i = 0; i < entidades.size(); i++) {
                               
                                if (ItemArtesanato.class.getName().equals(entidades.get(i).getClass().getName())) {
                                    ItemArtesanato item = (ItemArtesanato) entidades.get(i);
                %>

                <tr align="center">
                    <td><a href="SalvarArtesanato?txtId=<%= item.getArtesanato().getId()%>&operacao=VISUALIZAR1"><%= item.getArtesanato().getId()%></a></td>
                    <td><%= item.getArtesanato().getNome()%><input type="hidden" name="idItem" value="<%= i%>"/></td>
                    <td><input type="number" name="qtde" min="1" max="<%%>" value="<%= item.getQuantidade()%>"/></td>
                    <td><input type="text" name="vlrUnit" value="<%= item.getArtesanato().getPrecoUnit()%>"/></td>
                    <td><input type="text" name="subtotal" value="<%= item.getArtesanato().getPrecoUnit() * item.getQuantidade()%>"/></td>
                    <td><input type="submit" name="operacao" value="ATUALIZAR"/></td>
                    <td><input type="submit" name="operacao" value="EXCLUIRITEM"/></td>

                </tr>
                <%
                } else if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {
                    ItemProduto item = new ItemProduto();
                    item = (ItemProduto) entidades.get(i);
                %>
                <tr align="center">
                    <td><a href="SalvarArtesanato?txtId=<%= item.getProduto().getId()%>&operacao=VISUALIZAR1"><%= item.getProduto().getId()%></a></td>
                    <td><%= item.getProduto().getNome()%><input type="hidden" name="idItem" value="<%= i%>"/></td>
                    <td><input type="number" name="qtde" min="1" max="<%%>" value="<%= item.getQuantidade()%>"/></td>
                    <td><input type="text" name="vlrUnit" value="<%= item.getProduto().getPrecoUnit()%>"/></td>
                    <td><input type="text" name="subtotal" value="<%= item.getProduto().getPrecoUnit() * item.getQuantidade()%>"/></td>
                    <td><input type="submit" name="operacao" value="ATUALIZAR"/></td>
                    <td><input type="submit" name="operacao" value="EXCLUIRITEM"/></td>
                </tr>
                <%
                            }//else
                        }//for i
                    }//if
                %>
                <TR>
                    <TD COLSPAN="5"></TD>
                    <TD>Total: <%= carrinhos.getTotal()%></TD>
                </TR>
                <TR>
                    <TD COLSPAN="5"></TD>
                    <TD>
                        <a href="${pageContext.request.contextPath}/cartconfirmar.jsp">Comprar</a>
                        <!--
                        <form action="${pageContext.request.contextPath}/SalvarCarrinho" method="POST">
                            <input type="submit" name="operacao" value="COMPRAR" />
                        </form>
                        -->
                    </TD>
                </TR>
                <%
                    }
                %>
            </TABLE>
        </form>
        <br />
        <br/>
        <!--
        <div class="check-out">
            <form method="post" action="#">
                <table cellspacing="0" class="shop_table cart">
                    <thead>
                        <tr>
                            <th class="product-remove">&nbsp;</th>
                            <th class="product-thumbnail">&nbsp;</th>
                            <th class="product-name">Product</th>
                            <th class="product-price">Price</th>
                            <th class="product-quantity">Quantity</th>
                            <th class="product-subtotal">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="cart_item">
                            <td class="product-remove">
                                <a title="Remove this item" class="remove" href="#">×</a> 
                            </td>

                            <td class="product-thumbnail">
                                <a href="single-product.html"><img width="145" height="145" alt="poster_1_up" class="shop_thumbnail" src="img/product-thumb-2.jpg"></a>
                            </td>

                            <td class="product-name">
                                <a href="single-product.html">Ship Your Idea</a> 
                            </td>

                            <td class="product-price">
                                <span class="amount">£15.00</span> 
                            </td>

                            <td class="product-quantity">
                                <div class="quantity buttons_added">
                                    <input type="button" class="minus" value="-">
                                    <input type="number" size="4" class="input-text qty text" title="Qty" value="1" min="0" step="1">
                                    <input type="button" class="plus" value="+">
                                </div>
                            </td>

                            <td class="product-subtotal">
                                <span class="amount">£15.00</span> 
                            </td>
                        </tr>
                        <tr>
                            <td class="actions" colspan="6">
                                <div class="coupon">
                                    <label for="coupon_code">Coupon:</label>
                                    <input type="text" placeholder="Coupon code" value="" id="coupon_code" class="input-text" name="coupon_code">
                                    <input type="submit" value="Apply Coupon" name="apply_coupon" class="button">
                                </div>
                                <input type="submit" value="Update Cart" name="update_cart" class="button">
                                <input type="submit" value="Checkout" name="proceed" class="checkout-button button alt wc-forward">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>-->
        <h4 class="title">Shopping cart is empty</h4>
        <p class="cart-out">You have no items in your shopping cart.<br>Click<a href="index.jsp"> here</a> to continue shopping</p>


    </div>


</div>
