<%-- 
    Document   : singlepedido
    Created on : 10/04/2016, 19:07:06
    Author     : Henrique
--%>

<%@page import="e_commer.core.util.FormatDouble"%>
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
                    <li class="sort">
                        <a href="SalvarEndereco?txtId=<%= cliente.getId()%>&operacao=VISUALIZAR3">Meus Dados</a></li>
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
                <ul class="place">
                    <li class="sort">
                        <a href="SalvarCredito?txtCliId=<%= cliente.getId()%>&operacao=CONSULTAR1">Meus Vale-Creditos</a></li>
                    <li class="by"></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>  
            <div class="tab5">
            </div>
            <div class="tab6">

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
            <h3>Meu Pedido</h3>
            <%
                //Resultado resultado = (Resultado) session.getAttribute("resultado");
                Resultado pedido = (Resultado) request.getAttribute("pedidos");

                if (pedido != null && pedido.getMsg() != null) {
                    out.print(pedido.getMsg());
                }

                if (pedido != null) {
                    List<EntidadeDominio> entidades = pedido.getEntidades();
                    if (entidades != null) {
                        Pedido p = null;
                        //esta pegando 01 pedido e preciso que ande mais de uma vez no itens do pedido
                        for (int i = 0; i < entidades.size(); i++) {
                            p = (Pedido) entidades.get(i);
            %>
            <div class="clearfix"> </div>
            <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="1000%" CELLPADDING="4" CELLSPACING="3">
                <tr>
                    <th colspan="6"><strong>Pedido</strong></th>
                </tr>
                <tr>
                    <th>Nº Pedido</th>
                    <th>Data</th>
                    <th>Cliente</th>
                    <th>Email</th>
                    <th>Status</th>
                </tr>
                <tr>
                    <td><%= p.getId()%></td>
                    <td><%= ConverteDate.converteDateString(p.getDtCadastro())%></td>
                    <td><%= p.getCliente().getNome()%></td>
                    <td><%= p.getCliente().getEmail()%></td>
                    <td><%= p.getStatus()%></td>
                </tr>
            </table>
            <h3>Produtos</h3>
            <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="1000%" CELLPADDING="4" CELLSPACING="3">
                <tr>
                    <th>Cod Produto</th>
                    <th>Produto</th>
                    <th>Quantidade</th>
                    <th>Valor Unit</th>
                    <th>Sub Total</th>
                    <th>Troca/Devolução</th>
                </tr>
                <%
                    for (int j = 0; j < p.getItens().size(); j++) {

                        if (ItemArtesanato.class.getName().equals(p.getItens().get(j).getClass().getName())) {
                            ItemArtesanato item = (ItemArtesanato) p.getItens().get(j);
                %>
                <tr>
                    <td><%= item.getArtesanato().getId()%></td>
                    <td><%= item.getArtesanato().getNome()%></td>
                    <td><%= item.getQuantidade()%></td>
                    <td><%= item.getValorUnit()%></td>
                    <td><%= item.getQuantidade() * item.getValorUnit()%></td>
                    <td><a href="SalvarPedidos?operacao=CONSULTAR2&txtId=<%= p.getId()%>&txtIdPro=<%= item.getArtesanato().getId()%>">Troca/Devolução</a></td>
                </tr>
                <%
                } else if (ItemProduto.class.getName().equals(p.getItens().get(j).getClass().getName())) {
                    ItemProduto item = new ItemProduto();
                    item = (ItemProduto) p.getItens().get(j);
                %>
                <tr>
                    <td><%= item.getProduto().getId()%></td>
                    <td><%= item.getProduto().getNome()%></td>
                    <td><%= item.getQuantidade()%></td>
                    <td><%= FormatDouble.formataDouble(item.getValorUnit())%></td>
                    <td><%= FormatDouble.formataDouble(item.getQuantidade() * item.getValorUnit())%></td>
                    <td><a href="SalvarPedidos?operacao=CONSULTAR2&txtId=<%= p.getId()%>&txtIdPro=<%= item.getProduto().getId()%>">Troca/Devolução</a></td>
                </tr>  
                <%
                            }//else
                        }//for j
                    }//for i
                %>        
            </table>
            <br/>
            <%//se o status do pedido nao for CONCLUIDO, habilitar o botao de cancelar pedido
                if (!p.getStatus().equals("CONCLUIDO")) {
            %>
            <a href="SalvarPedidos?txtId=<%= pedido.getEntidades().get(0).getId()%>&txtStatus=CANCELAR&operacao=ALTERAR">
                
                <%
                            }
                        }//if(entidade)
                    }//if(resultado)
                %>
        </div>

    </div>


    <div class="clearfix"> </div>
</div>
