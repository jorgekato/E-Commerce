<%-- 
    Document   : index
    Created on : 27/03/2016, 20:17:44
    Author     : Henrique
--%>
<%@page import="java.util.List"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page import=" e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    
    resultado = (Resultado) request.getAttribute("resultado");
%>
<div class="content">

    <div class="col-md-9">
        <div class="shoe">
            <img class="img-responsive" src="${pageContext.request.contextPath}/images/banner.jpg" alt="" >
            <div class="shop">
                <h4>SHOP <span>WOMEN</span></h4>
                <p>SHOES FALL 2014</p>
            </div>
        </div>
        <div class="content-bottom">

            <h3>Featured products</h3>

            <%       if (resultado != null) {
                    List<EntidadeDominio> entidades = resultado.getEntidades();
                    StringBuilder sbRegistro = new StringBuilder();
                    StringBuilder sbLink = new StringBuilder();
                    int contador = 0;

                    if (entidades != null) {
                        for (int i = 0; i < entidades.size(); i++) {

                            Artesanato art = (Artesanato) entidades.get(i);

                            sbRegistro.setLength(0);
                            sbLink.setLength(0);

                            sbRegistro.append("<div class=\"col-md-4 shirt\">");
                            sbRegistro.append("<div class=\"bottom-grid-top\">");

                            sbLink.append("<a href=SalvarArtesanato?");
                            sbLink.append("txtId=");
                            sbLink.append(art.getId());
                            sbLink.append("&");
                            sbLink.append("operacao=");
                            sbLink.append("VISUALIZAR1>");
                            sbLink.append("<img class=\"img-responsive\" src=\"images/sh.png\" alt=\"\" />");
                            sbLink.append("</a>");

                            sbRegistro.append(sbLink.toString());
                            sbRegistro.append("<div class=\"five\">");
                            sbRegistro.append("<h6 >-50%</h6>");
                            sbRegistro.append("</div>");
                            sbRegistro.append("<div class=\"pre\">");
                            sbRegistro.append("<p>");
                            sbRegistro.append(art.getNome());
                            sbRegistro.append("</p>");
                            sbRegistro.append("<span>R$");
                            sbRegistro.append(art.getPrecoUnit());
                            sbRegistro.append("</span>");
                            sbRegistro.append("<div class=\"clearfix\"> </div>");
                            sbRegistro.append("</div></a>");

                            sbRegistro.append("</div>");
                            sbRegistro.append(" </div>");
                            contador++;

                            out.print(sbRegistro.toString());

                        }

                    }

                }

            %>

        </div>
        <ul class="start">
            <!-- onde vai fazer a paginação-->
            <li><span>1</span></li>
            <li class="arrow"><a href="#">2</a></li>
            <li class="arrow"><a href="#">3</a></li>
            <li class="arrow"><a href="#">4</a></li>
            <li class="arrow"><a href="#">5</a></li>
            <li class="arrow"><a href="#">6</a></li>
        </ul>
    </div>

    <!-- coluna de busca e indicação -->
    <div class="col-md-3 col-md">
        <div class=" possible-about">
            <h4>Sort Products</h4>
            <div class="tab1">
                <ul class="place">

                    <li class="sort">Sort by <span>price</span></li>
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

                    <li class="sort">Sort by <span>brands</span></li>
                    <li class="by"><img src="images/do.png" alt=""></li>
                    <div class="clearfix"> </div>
                </ul>

                <div class="single-bottom">


                    <a href="#">
                        <input type="checkbox"  id="nike" value="">
                        <label for="nike"><span></span><b>Nike</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="nike1" value="">
                        <label for="nike1"><span></span> <b>Reebok</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="nike2" value="">
                        <label for="nike2"><span></span><b> Fila</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="nike3" value="">
                        <label for="nike3"><span></span> <b>Puma</b></label>
                    </a>
                    <a href="#">
                        <input type="checkbox"  id="nike4" value="">
                        <label for="nike4"><span></span><b>Sparx</b></label>
                    </a>
                </div>

            </div>
            <div class="tab3">
                <ul class="place">

                    <li class="sort">Sort by <span>colour</span> </li>
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

                    <li class="sort">Sort by <span>discount</span> </li>
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

                    <li class="sort">Sort by <span>rating</span> </li>
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
        <div class="content-bottom-grid">
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
            <h3>Payment Options</h3>
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