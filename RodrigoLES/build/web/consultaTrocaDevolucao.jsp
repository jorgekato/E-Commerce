<%-- 
    Document   : consultaTrocaDevolucao
    Created on : 08/05/2016, 13:25:39
    Author     : Jorge
--%>

<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="e_commer.dominio.Cliente"%>
<%@page import="e_commer.dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
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

        <div class="content">
            <h3>Detalhes Troca e Devolução</h3>
            <%        //Resultado resultado = (Resultado) session.getAttribute("resultado");
                resultado = (Resultado) request.getAttribute("trocadevolucao");

                if (resultado != null) {

            %>
            <div class="clearfix"> </div>
            <form action="SalvarTrocaDevolucao" method="post">


                <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="100%" CELLPADDING="4" CELLSPACING="3">
                    <tr>
                        <th colspan="6"><strong>Pedido</strong></th>
                    </tr>
                    <tr>
                        <td>Nº Pedido</td>
                        <td>Data Pedido</td>
                        <td>Cliente</td>
                        <td>Produto</td>
                        <td>Quantidade</td>
                        <td>Status do Pedido</td>
                    </tr>
                    <%                        for (int i = 0; i < resultado.getEntidades().size(); i++) {
                            TrocaDevolucao td = (TrocaDevolucao) resultado.getEntidades().get(i);
                            for (int j = 0; j < td.getPedido().getItens().size(); j++) {
                                if (ItemArtesanato.class.getName().equals(td.getPedido().getItens().get(0).getClass().getName())) {
                                    ItemArtesanato item = (ItemArtesanato) td.getPedido().getItens().get(j);
                    %>
                    <tr>
                        <td><%= td.getPedido().getId()%></td>
                        <td><%= ConverteDate.converteDateString(td.getDtCadastro())%></td>
                        <td><%= td.getPedido().getCliente().getNome()%></td>
                        <td><%= item.getArtesanato().getNome()%></td>
                        <td><%= td.getQuantidade()%></td>
                        <td><%= td.getStatus()%></td>
                    </tr>
                    <%
                    } else {
                        ItemProduto item = (ItemProduto) td.getPedido().getItens().get(j);
                    %>
                    <tr>
                        <td><%= td.getPedido().getId()%></td>
                        <td><%= ConverteDate.converteDateString(td.getDtCadastro())%></td>
                        <td><%= td.getPedido().getCliente().getNome()%></td>
                        <td><%= item.getProduto().getNome()%></td>
                        <td><%= td.getQuantidade()%></td>
                        <td><%= td.getStatus()%></td>
                    </tr>
                    <%
                                    }//else
                                }//for j
                            }//for i
                        }//if(entidade)
                    %> 
                </table>
            </form>
        </div>
    </div>


    <div class="clearfix"> </div>
</div>
