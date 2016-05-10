<%-- 
    Document   : teste
    Created on : 13/04/2016, 07:10:38
    Author     : Henrique
--%>

<%@page import="e_commer.dominio.Cliente"%>
<%@page import="e_commer.dominio.ItemArtesanato"%>
<%@page import="e_commer.dominio.ItemProduto"%>
<%@page import="e_commer.dominio.AbstractItem"%>
<%@page import="e_commer.dominio.CarrinhoCompra"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page import="e_commer.dominio.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->

<!DOCTYPE html>
<html>
    
    
    

    <head>
        <title>RH Informática</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <!-- Custom Theme files -->
        <!--theme-style-->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all" />	
        <!--//theme-style-->

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Mihstore Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--fonts-->
        <link href='http://fonts.googleapis.com/css?family=Cabin:400,500,600,700' rel='stylesheet' type='text/css'>
        <!--//fonts-->
        <!--//slider-script-->
        <script>$(document).ready(function (c) {
                $('.alert-close').on('click', function (c) {
                    $('.message').fadeOut('slow', function (c) {
                        $('.message').remove();
                    });
                });
            });
        </script>
        <script>$(document).ready(function (c) {
                $('.alert-close1').on('click', function (c) {
                    $('.message1').fadeOut('slow', function (c) {
                        $('.message1').remove();
                    });
                });
            });
        </script>
        <script>$(document).ready(function (c) {
                $('.alert-close2').on('click', function (c) {
                    $('.message2').fadeOut('slow', function (c) {
                        $('.message2').remove();
                    });
                });
            });
        </script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/etalage.css">
        <script src="${pageContext.request.contextPath}/js/jquery.etalage.min.js"></script>
        <script>
            jQuery(document).ready(function ($) {

                $('#etalage').etalage({
                    thumb_image_width: 300,
                    thumb_image_height: 400,
                    source_image_width: 900,
                    source_image_height: 1200,
                    show_hint: true,
                    click_callback: function (image_anchor, instance_id) {
                        alert('Callback example:\nYou clicked on an image with the anchor: "' + image_anchor + '"\n(in Etalage instance: "' + instance_id + '")');
                    }
                });

            });
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/move-top.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/easing.js"></script>
        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $(".scroll").click(function (event) {
                    event.preventDefault();
                    $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
                });
            });
        </script>	
        <!-- start menu -->
        <link href="${pageContext.request.contextPath}/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/megamenu.js"></script>
        <script>$(document).ready(function () {
                $(".megamenu").megamenu();
            });</script>				

    </head>
    <body> 
        <!--header-->
        <div class="container">
            <div class="header" id="home">	
                <div class="header-para">
                    <p>The quick brown <span>fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog.</span></p>	
                </div>	
                <ul class="header-in">
                    

                    ${usuario.getNome()}
                    <li><a href="account.jsp">Login</a> </li>


                    <li ><a href="contact.html" > CONTACT US</a></li>
                </ul>
                <div class="clearfix"> </div>
            </div>
            <!---->
            <div class="header-top">
                <div class="logo">
                    <a href="index.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" alt="" ></a>
                </div>
                <div class="header-top-on">
                    <ul class="social-in">
                        <li><a href="#"><i> </i></a></li>						
                        <li><a href="#"><i class="ic"> </i></a></li>
                        <li><a href="#"><i class="ic1"> </i></a></li>
                        <li><a href="#"><i class="ic2"> </i></a></li>
                        <li><a href="#"><i class="ic3"> </i></a></li>
                    </ul>
                </div>
                <div class="clearfix"> </div>
            </div>
            <div class="header-bottom">
                <div class="top-nav">

                    <ul class="megamenu skyblue">
                        <li class="active grid"><a  href="SalvarArtesanato?operacao=CONSULTAR1">Artesanatos</a>
                            <div class="megapanel">
                                <div class="row">
                                    <div class="col1">
                                        <div class="h_nav">
                                            <ul>
                                                <li><a href="store.html">Accessories</a></li>
                                            </ul>	
                                        </div>												
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="grid"><a  href="SalvarProduto?txtId=&operacao=CONSULTAR1">Produtos</a>
                            <div class="megapanel">
                                <div class="row">
                                    <div class="col1">
                                        <div class="h_nav">
                                            <ul>
                                                <li><a href="store.html">Accessories</a></li>
                                                <li><a href="store.html">Bags</a></li>
                                            </ul>	
                                        </div>												
                                    </div>
                                </div>
                            </div>
                        </li>

                    </ul> 
                </div>
                <div class="cart icon1 sub-icon1">
                    <h6 >Shopping cart:
                        <span class="item">
                            Qtde de itens
                        </span>
                        <span class="rate">70$</span>
                        <li><a href="#" class="round"> </a>
                            <ul class="sub-icon1 list">
                                <h3>Quantidade de Itens 
                                    //out.print(sbReg                                                   %>
                                </h3>                                
                                <div class="shopping_cart">

                                </div>
                                <div class="check_button"><a href="cart.jsp">View Cart</a></div>
                                <div class="clearfix"></div>
                            </ul>
                        </li>
                    </h6>

                </div>
                <div class="clearfix"> </div>
            </div>
            <div class="page">
                <h6><a href="#">Page Title</a><b>|</b>Page description The quick, brown <span class="for">fox jumps over a lazy dog. DJs flock by when TV ax quiz prog.</span></h6>
            </div>

            <div class="content">

                <div class="content-bottom store">
                    <h3>produtos</h3>
                    
                    

                </div>
            </div>
            <%-- any content can be specified here e.g.: --%>
            <div class="footer">
                <p class="footer-class">© 2015 Mihstore All Rights Reserved | Template by  <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>

                <a href="#home" class="scroll to-Top" >  GO TO TOP!</a>
                <div class="clearfix"> </div>
            </div>
        </div>

        <!---->
    </body>
</html>
