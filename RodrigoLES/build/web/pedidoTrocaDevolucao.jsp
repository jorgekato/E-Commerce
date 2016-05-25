<%-- 
    Document   : pedidoTrocaDevolucao
    Created on : 06/05/2016, 16:33:50
    Author     : Jorge
--%>

<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="e_commer.dominio.Cliente"%>
<%@page import="e_commer.dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="e_commer.core.aplicacao.Resultado, e_commer.dominio.*, java.util.*"%>



<div class="content">
    <div class="col-md-3 col-md">
        <div class=" possible-about">
            <h4>Sort Products</h4>
            <div class="tab1">
                <ul class="place">
                    <li class="sort">
                        <a href="SalvarPedidos?txtCliId=<%= cliente.getId()%>&operacao=CONSULTAR1">Meus Pedidos</a></li>
                    <li class="by"></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>
            <div class="tab2">
                <ul class="place">
                    <li class="sort">Meus Dados</li>
                    <li class="by"></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>
            <div class="tab3">
                <ul class="place">
                    <li class="sort">                     
                        <a href="SalvarTrocaDevolucao?txtCliId=<%= cliente.getId()%>&operacao=CONSULTAR1">Minhas Trocas e Cancelamentos</a></li>
                    <li class="by"></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>
            <div class="tab4">
            </div>
            <div class="tab5">

            </div>

            <!--script-->
            <script>
                $(document).ready(function () {
                    $(".tab1 .single-bottom").hide();
                    $(".tab2 .single-bottom").hide();
                    $(".tab3 .w_nav2").hide();
                    $(".tab4 .single-bottom").hide();
                    $(".tab5 .star-at").hide();
                    $(".tab1 ul").click(function () {
                        $(".tab1 .single-bottom").slideToggle(300);
                        $(".tab2 .single-bottom").hide();
                        $(".tab3 .w_nav2").hide();
                        $(".tab4 .single-bottom").hide();
                        $(".tab5 .star-at").hide();
                    })
                    $(".tab2 ul").click(function () {
                        $(".tab2 .single-bottom").slideToggle(300);
                        $(".tab1 .single-bottom").hide();
                        $(".tab3 .w_nav2").hide();
                        $(".tab4 .single-bottom").hide();
                        $(".tab5 .star-at").hide();
                    })
                    $(".tab3 ul").click(function () {
                        $(".tab3 .w_nav2").slideToggle(300);
                        $(".tab4 .single-bottom").hide();
                        $(".tab5 .star-at").hide();
                        $(".tab2 .single-bottom").hide();
                        $(".tab1 .single-bottom").hide();
                    })
                    $(".tab4 ul").click(function () {
                        $(".tab4 .single-bottom").slideToggle(300);
                        $(".tab5 .star-at").hide();
                        $(".tab3 .w_nav2").hide();
                        $(".tab2 .single-bottom").hide();
                        $(".tab1 .single-bottom").hide();
                    })
                    $(".tab5 ul").click(function () {
                        $(".tab5 .star-at").slideToggle(300);
                        $(".tab4 .single-bottom").hide();
                        $(".tab3 .w_nav2").hide();
                        $(".tab2 .single-bottom").hide();
                        $(".tab1 .single-bottom").hide();
                    })
                });
            </script>
            <!-- script -->
        </div>
        <div class="content-bottom-grid">

        </div>
        <!---->
        <div class="money">

        </div>
    </div>
    <div class="col-md-9">
        <div class="shoe">


        </div>
        <div class="content-bottom">
            <h2>Formulário de Troca ou Cancelamento</h2>
            <h3>Meu Pedido</h3>
            <%
                //Resultado resultado = (Resultado) session.getAttribute("resultado");
                Pedido p = (Pedido) request.getAttribute("pedidos");

                if (p != null) {

            %>
            <div class="clearfix"> </div>
            <form action="SalvarTrocaDevolucao" method="post">
                <input type="hidden" name="txtIdPed" value="<%= p.getId()%>"/>
                
                <table border="3" width="1000%" CELLPADDING="4" CELLSPACING="3">
                    <tr>
                        <th colspan="6"><strong>Pedido</strong></th>
                    </tr>
                    <tr>
                        <td>Nº Pedido</td>
                        <td>Data</td>
                        <td>Cliente</td>
                        <td>Email</td>
                        <td>Status</td>
                    </tr>
                    <tr>
                        <td><%= p.getId()%></td>
                        <td><%= ConverteDate.converteDateString(p.getDtCadastro())%></td>
                        <td><%= p.getCliente().getNome()%></td>
                        <td><%= p.getCliente().getEmail()%></td>
                        <td><%= p.getStatus()%></td>
                    </tr>
                </table>
                <h3>Produto e Motivo</h3>
                <table>
                    <%
                        for (int i = 0; i < p.getItens().size(); i++) {//qtde de itens
                            if (ItemArtesanato.class.getName().equals(p.getItens().get(i).getClass().getName())) {
                                ItemArtesanato item = (ItemArtesanato) p.getItens().get(i);
                    %>
                    
                    <tr>
                        <th>Cod Produto</th>
                        <td><input type="text" name="txtIdpro" value="<%= item.getArtesanato().getId()%>" readonly/></td>
                    </tr>
                    <tr>
                        <th>Produto</th>
                        <td><input type="text" name="txtNome" value="<%= item.getArtesanato().getNome()%>" readonly/></td>
                    </tr>
                    <tr>
                        <th>Quantidade</th>
                        <td><input type="text" name="txtQtde" value="<%= item.getQuantidade()%>" readonly/></td>
                    </tr>
                    <tr>
                        <th>Valor Unit</th>
                        <td><input type="text" name="txtVlrUnit" value="<%= item.getArtesanato().getPrecoUnit()%>" readonly/></td>
                    </tr>
                    <tr>
                        <th>Sub Total</th>
                        <td><input type="text" name="txtSubTotal" value="<%= item.getQuantidade() * item.getArtesanato().getPrecoUnit()%>" readonly/></td>
                    </tr>

                    <%
                    } else if (ItemProduto.class.getName().equals(p.getItens().get(i).getClass().getName())) {
                        ItemProduto item = new ItemProduto();
                        item = (ItemProduto) p.getItens().get(i);
                    %>
                    <tr>
                        <th>Cod Produto</th>
                        <td><input type="text" name="txtIdpro" value="<%= item.getProduto().getId()%>" readonly/></td>
                    </tr>
                    <tr>
                        <th>Produto</th>
                        <td><input type="text" name="txtNome" value="<%= item.getProduto().getNome()%>" readonly/></td>
                    </tr>
                    <tr>
                        <th>Quantidade</th>
                        <td><input type="text" name="txtQtde" value="<%= item.getQuantidade()%>" readonly/>${qtde}</td>
                    </tr>
                    <tr>
                        <th>Valor Unit</th>
                        <td><input type="text" name="txtVlrUnit" value="<%= item.getProduto().getPrecoUnit()%>" readonly/></td>
                    </tr>
                    <tr>
                        <th>Sub Total</th>
                        <td><input type="text" name="txtSubTotal" value="<%= item.getQuantidade() * item.getProduto().getPrecoUnit()%>" readonly/></td>
                    </tr>
                    <%
                                }//else
                            }//for i
                        }//if(entidade)

                    %> 
                    <tr>
                        <th>Qtde para Troca/Devolução</th>
                        <td><input type="text" name="txtQtdeDev" value=""/></td>
                    </tr>
                    <tr>
                        <th>Motivo</th>
                        <td><input type="radio" name="txtMotivo" value="Defeito"/>Defeito
                            <input type="radio" name="txtMotivo" value="Cancelamento"/>Cancelamento
                            <input type="radio" name="txtMotivo" value="Outro"/>Outro
                        </td>
                    </tr>
                    <tr>
                        <th>Anotação</th>
                    </tr>
                    <tr>
                        <td colspan="2"><textarea name="txtAnotacao" cols="80"></textarea></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="operacao" value="ENVIAR"/></td>
                    </tr>
                </table>
                    
            </form>
        </div>

    </div>


    <div class="clearfix"> </div>
</div>
