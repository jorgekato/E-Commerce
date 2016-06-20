<%-- 
    Document   : meuspedidos
    Created on : 09/04/2016, 01:15:31
    Author     : Henrique
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
        <div class="content-bottom">
            <h3>Meus Pedidos</h3>
            <%
                //Resultado resultado = (Resultado) session.getAttribute("resultado");
                Resultado pedido = (Resultado) request.getAttribute("pedidos");

                if (pedido != null && pedido.getMsg() != null) {
                    out.print(pedido.getMsg());
                }
            %>
            <div class="clearfix"> </div>
            <table border="3" width="50%" CELLPADDING="4" CELLSPACING="3">
                <tr>
                    <th colspan="3"><strong>Pedidos</strong></th>
                </tr>
                <tr>
                    <td>Nº Pedido</td>
                    <td>DATA</td>
                    <td>SITUAÇÃO</td>

                </tr>
                <%
                    if (pedido != null) {
                        List<EntidadeDominio> entidades = pedido.getEntidades();
                        StringBuilder sbRegistro = new StringBuilder();
                        StringBuilder sbLink = new StringBuilder();

                        if (entidades != null) {
                            for (int i = 0; i < entidades.size(); i++) {
                                Pedido p = (Pedido) entidades.get(i);
                                sbRegistro.setLength(0);
                                sbLink.setLength(0);

                                sbRegistro.append("<tr align='center'>");

                                sbLink.append("<a href=SalvarPedidos?");
                                sbLink.append("txtId=");
                                sbLink.append(p.getId());
                                sbLink.append("&");
                                sbLink.append("operacao=");
                                sbLink.append("VISUALIZAR1");

                                sbLink.append(">");

                                //nome
                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                sbRegistro.append(p.getId());
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                //data de nascimento
                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                String dtCad = ConverteDate.converteDateString(p.getDtCadastro());
                                sbRegistro.append(dtCad);
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                //ativo
                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                sbRegistro.append(p.getStatus());
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("</TR>");

                                out.print(sbRegistro.toString());

                            }//for
                        }//if(entidade)
                    }//if(resultado)

                %>        
            </table>

        </div>

    </div>


    <div class="clearfix"> </div>
</div>