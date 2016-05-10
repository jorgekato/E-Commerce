<%-- 
    Document   : minhaconta
    Created on : 07/04/2016, 17:19:06
    Author     : Henrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h3>Featured products</h3>
            ${mensagem}
        </div>

    </div>


    <div class="clearfix"> </div>
</div>