<%-- 
    Document   : singleproduto
    Created on : 28/03/2016, 18:51:56
    Author     : Henrique
--%>

<%@page import="e_commer.core.util.ManipulaImagem"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page import=" e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Produto"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content">
    <%        Produto produto = (Produto) request.getAttribute("produto");
    %>

    <div class="col-md-9">
        <div class="col-md-5 single-top">	
            <ul id="etalage">
                <!--Implementar For para exibir mais de uma imagem, caso necessário -->
                <%for (int i = 0; i < produto.getFoto().length; i++) {
                %>
                <li>
                    <img class="etalage_thumb_image img-responsive" src="<% if (produto != null) {
                                if (produto.getFoto()[i] != null) {
                                    out.print("data:image/jpg;base64," + ManipulaImagem.setImagemDimensao(produto.getFoto()[i].getImagem(), 360, 480));
                                }                            
                        }%>" alt="" >
                    <img class="etalage_source_image img-responsive" src="<% if (produto != null) {
                            if (produto.getFoto()[i] != null) {
                                out.print("data:image/jpg;base64," + ManipulaImagem.setImagemDimensao(produto.getFoto()[i].getImagem(), 1080, 1440));
                            }
                        }%>" alt="" >
                </li>
                <%}%>
            </ul>

        </div>	
        <div class="col-md-7 single-top-in">
            <div class="single-para">
                <h4><%
                    if (produto

                    
                        != null) {
                        out.print(produto.getNome());
                    }
                    %></h4><br/>
                    <%
                        if (produto

                        
                            != null) {
                            out.print(produto.getDescricao());
                        }
                    %>
                <div class="para-grid">
                    <span  class="add-to">R$<%
                        if (produto

                        
                            != null) {
                            out.print(produto.getPrecoUnit());
                        }
                        %></span>
                        <%if(produto.getQuantidade () 
                                > 0){%>
                    <a href="${pageContext.request.contextPath}/SalvarCarrinho?<%
                        StringBuilder sbLink = new StringBuilder();
                        sbLink.setLength(0);

                        sbLink.append("txtId=");
                        sbLink.append(produto.getId());
                        sbLink.append("&");
                        sbLink.append("qtde=");
                        sbLink.append("1");
                        sbLink.append("&");
                        sbLink.append("tipo=");
                        sbLink.append("PRODUTO");
                        sbLink.append("&");
                        sbLink.append("operacao=");
                        sbLink.append("ADICIONAR");

                        out.print(sbLink.toString());

                       %>" class=" cart-to">Adicionar</a>
                    <%}

                        
                        
                        else{%>
                    <a href="#" class=" cart-to">Out of Stock</a>
                    <%}%>
                    <div class="clearfix"></div>
                </div>
                <h5><%= produto.getQuantidade()%> itens no estoque</h5>
                
                <div class="share">
                    <h4>Compartilhar Produto :</h4>
                    <ul class="share_nav">
                        <li><a href="#"><img src="images/facebook.png" title="facebook"></a></li>
                        <li><a href="#"><img src="images/twitter.png" title="Twiiter"></a></li>
                        <li><a href="#"><img src="images/rss.png" title="Rss"></a></li>
                        <li><a href="#"><img src="images/gpluse.png" title="Google+"></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="clearfix"> </div>
        <ul id="flexiselDemo1">
            <li><img src="images/branca de neve feltro mini.jpg" /><div class="grid-flex"><a href="#">Branca de Neve em feltro</a><p>Rs 69.99</p></div></li>
            <li><img src="images/cinderela de pano mini.jpg" /><div class="grid-flex"><a href="#">Cinderela em pano</a><p>Rs 38.45</p></div></li>
            <li><img src="images/mdf quadrada mini.jpg" /><div class="grid-flex"><a href="#">Caixa em mdf</a><p>Rs 10.00</p></div></li>
            <li><img src="images/furador-gigante-alavanca-floco-de-neve mini.jpg" /><div class="grid-flex"><a href="#">Furador</a><p>Rs 19.50</p></div></li>
            <li><img src="images/quadro-maternidade-bastidor-ursinha-bebe mini.jpg" /><div class="grid-flex"><a href="#">Sit</a><p>Rs 23.99</p></div></li>
        </ul>
        <script type="text/javascript">
            $(window).load(function () {
                $("#flexiselDemo1").flexisel({
                    visibleItems: 5,
                    animationSpeed: 1000,
                    autoPlay: true,
                    autoPlaySpeed: 3000,
                    pauseOnHover: true,
                    enableResponsiveBreakpoints: true,
                    responsiveBreakpoints: {
                        portrait: {
                            changePoint: 480,
                            visibleItems: 1
                        },
                        landscape: {
                            changePoint: 640,
                            visibleItems: 2
                        },
                        tablet: {
                            changePoint: 768,
                            visibleItems: 3
                        }
                    }
                });

            });
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.flexisel.js"></script>
        <!---->
    </div>
        <!-- coluna de busca e indicação -->
    <div class="col-md-3 col-md">
        <div class=" possible-about">
            <h4>Fitrar Produtos</h4>
            <div class="tab1">
                <ul class="place">

                    <li class="sort">Filtrar por <span>preço</span></li>
                    <li class="by"><img src="images/do.png" alt=""></li>
                    <div class="clearfix"> </div>
                </ul>
                <div class="single-bottom">


                    <a href="#">
                        <input type="checkbox"  id="brand" value="">
                        <label for="brand"><span></span><b>Rs.3000-Rs.4000</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="brand1" value="">
                        <label for="brand1"><span></span> <b>Rs.3000-Rs.2000</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="brand2" value="">
                        <label for="brand2"><span></span> <b>Rs.2000-Rs.1000</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="brand3" value="">
                        <label for="brand3"><span></span> <b>Rs.1000-Rs.500</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="brand4" value="">
                        <label for="brand4"><span></span><b>Rs.500-below</b></label>
                    </a>

                </div>

            </div>
            <div class="tab2">
                <ul class="place">

                    <li class="sort">Filtrar por <span>marca</span></li>
                    <li class="by"><img src="images/do.png" alt=""></li>
                    <div class="clearfix"> </div>
                </ul>

                <div class="single-bottom">


                    <a href="#">
                        <input type="checkbox"  id="nike" value="">
                        <label for="nike"><span></span><b>Acrilex</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="nike1" value="">
                        <label for="nike1"><span></span> <b>Cristal</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="nike2" value="">
                        <label for="nike2"><span></span><b> Madeirex</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="nike3" value="">
                        <label for="nike3"><span></span> <b>Mundial</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="nike4" value="">
                        <label for="nike4"><span></span><b>Toque e crie</b></label>
                    </a>
                </div>

            </div>
            <div class="tab3">
                <ul class="place">

                    <li class="sort">Filtrar por <span>cor</span> </li>
                    <li class="by"><img src="images/do.png" alt=""></li>
                    <div class="clearfix"> </div>
                </ul>
                <ul class="w_nav2">
                    <li><a class="color1" href="#"></a></li>
                    <li><a class="color2" href="#"></a></li>
                    <li><a class="color3" href="#"></a></li>
                    <li><a class="color4" href="#"></a></li>
                    <li><a class="color5" href="#"></a></li>
                    <li><a class="color6" href="#"></a></li>
                    <li><a class="color7" href="#"></a></li>
                    <li><a class="color8" href="#"></a></li>
                    <li><a class="color9" href="#"></a></li>
                    <li><a class="color10" href="#"></a></li>
                    <li><a class="color12" href="#"></a></li>
                    <li><a class="color13" href="#"></a></li>
                    <li><a class="color14" href="#"></a></li>
                    <li><a class="color15" href="#"></a></li>
                    <li><a class="color5" href="#"></a></li>
                    <li><a class="color6" href="#"></a></li>
                    <li><a class="color7" href="#"></a></li>
                    <li><a class="color8" href="#"></a></li>
                    <li><a class="color9" href="#"></a></li>
                    <li><a class="color10" href="#"></a></li>
                </ul>
            </div>
            <div class="tab4">
                <ul class="place">

                    <li class="sort">Filtrar por <span>desconto</span> </li>
                    <li class="by"><img src="images/do.png" alt=""></li>
                    <div class="clearfix"> </div>
                </ul>
                <div class="single-bottom">


                    <a href="#">
                        <input type="checkbox"  id="up" value="">
                        <label for="up"><span></span><b>Upto 10%</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="up1" value="">
                        <label for="up1"><span></span> <b>10%-20%</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="up2" value="">
                        <label for="up2"><span></span> <b>20%-30%</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="up3" value="">
                        <label for="up3"><span></span> <b>30%-40%</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="up4" value="">
                        <label for="up4"><span></span><b>40%-50%</b></label>
                    </a>

                </div>
            </div>
            <div class="tab5">
                <ul class="place">

                    <li class="sort">Filtrar por <span>classificação</span> </li>
                    <li class="by"><img src="images/do.png" alt=""></li>
                    <div class="clearfix"> </div>
                </ul>
                <div class="star-at">
                    <div class="two">
                        <a href="#"> <i></i>  </a>	
                        <a href="#"> <i></i>  </a>
                        <a href="#"> <i></i>  </a>
                        <a href="#"> <i></i>  </a>
                        <a href="#"> <i></i>  </a>
                    </div>
                    <div class="two">
                        <a href="#"> <i></i>  </a>	
                        <a href="#"> <i></i>  </a>
                        <a href="#"> <i></i>  </a>
                        <a href="#"> <i></i>  </a>

                    </div>
                    <div class="two">
                        <a href="#"> <i></i>  </a>	
                        <a href="#"> <i></i>  </a>
                        <a href="#"> <i></i>  </a>

                    </div>
                    <div class="two">
                        <a href="#"> <i></i>  </a>	
                        <a href="#"> <i></i>  </a>

                    </div>
                </div>

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
       <!-- <div class="content-bottom-grid">
            <h3>Best Sellers</h3>
            <div class="latest-grid">
                <div class="news">
                    <a href="single.html"><img class="img-responsive" src="images/si.jpg" title="name" alt=""></a>
                </div>
                <div class="news-in">
                    <h6><a href="single.html">Product name here</a></h6>
                    <p>Description Lorem ipsum </p>
                    <ul>
                        <li>Price: <span>$110</span> </li><b>|</b>
                        <li>Country: <span>US</span></li>
                    </ul>
                </div>
                <div class="clearfix"> </div>
            </div>
            <div class="latest-grid">
                <div class="news">
                    <a href="single.html"><img class="img-responsive" src="images/si1.jpg" title="name" alt=""></a>
                </div>
                <div class="news-in">
                    <h6><a href="single.html">Product name here</a></h6>
                    <p>Description Lorem ipsum </p>
                    <ul>
                        <li>Price: <span>$110</span> </li><b>|</b>
                        <li>Country: <span>US</span></li>
                    </ul>
                </div>
                <div class="clearfix"> </div>
            </div>
            <div class="latest-grid">
                <div class="news">
                    <a href="single.html"><img class="img-responsive" src="images/si.jpg" title="name" alt=""></a>
                </div>
                <div class="news-in">
                    <h6><a href="single.html">Product name here</a></h6>
                    <p>Description Lorem ipsum</p>
                    <ul>
                        <li>Price: <span>$110</span> </li><b>|</b>
                        <li>Country: <span>US</span></li>
                    </ul>
                </div>
                <div class="clearfix"> </div>
            </div>
            <div class="latest-grid">
                <div class="news">
                    <a href="single.html"><img class="img-responsive" src="images/si1.jpg" title="name" alt=""></a>
                </div>
                <div class="news-in">
                    <h6><a href="single.html">Product name here</a></h6>
                    <p>Description Lorem ipsum </p>
                    <ul>
                        <li>Price: <span>$110</span> </li><b>|</b>
                        <li>Country: <span>US</span></li>
                    </ul>
                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
        <!---->
        <div class="money">
            <h3>Opções de Pagamento</h3>
            <ul class="money-in">
                <li><a href="single.html"><img class="img-responsive" src="images/p1.png" title="name" alt=""></a></li>
                <li><a href="single.html"><img class="img-responsive" src="images/p2.png" title="name" alt=""></a></li>
                <li><a href="single.html"><img class="img-responsive" src="images/p3.png" title="name" alt=""></a></li>
                <li><a href="single.html"><img class="img-responsive" src="images/p4.png" title="name" alt=""></a></li>
                <li><a href="single.html"><img class="img-responsive" src="images/p5.png" title="name" alt=""></a></li>
                <li><a href="single.html"><img class="img-responsive" src="images/p6.png" title="name" alt=""></a></li>
                <li><a href="single.html"><img class="img-responsive" src="images/p1.png" title="name" alt=""></a></li>
                <li><a href="single.html"><img class="img-responsive" src="images/p4.png" title="name" alt=""></a></li>
                <li><a href="single.html"><img class="img-responsive" src="images/p2.png" title="name" alt=""></a></li>

            </ul>
        </div>      
    </div>
    <!-- fim da coluna de busca e indicação -->
    <div class="clearfix"> </div>
</div>