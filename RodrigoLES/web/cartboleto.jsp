<%-- 
    Document   : cartboleto
    Created on : 24/06/2016, 00:18:35
    Author     : Henrique
--%>



<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<div class="content">
    <div class="check-out">
        <div class="content-bottom store">
            <h3>Forma de Pagamento</h3>
            <div class="bottom-grid">
                <div class="col-md-3 store-top">
                    <div class="bottom-grid-top">
                        <a href="cartcartao.jsp"><img class="img-responsive" src="images/logo-paypalsmall.png" alt="" >
                            <div class="five">

                            </div>
                            <div class="pre">
                                <p>Cartão de Crédito</p>

                                <div class="clearfix"> </div>
                            </div></a>
                    </div>
                </div>
                <div class="col-md-3 store-top">
                    <div class="bottom-grid-top">
                        <a href="cartboleto.jsp"><img class="img-responsive" src="images/boletosmall.png" alt="" >
                            <div class="five">

                            </div>
                            <div class="pre">
                                <p>Boleto</p>

                                <div class="clearfix"> </div>
                            </div></a>
                    </div>
                </div>
                <div class="clearfix"> </div>
            </div>
        </div>

        <div class="account-in">
            <h2>Dados do boleto</h2>
            <div class="col-md-7 account-top">
                <form action="${pageContext.request.contextPath}/SalvarPedidos" method="POST">
                    <p><a href="#" >Cliqui aqui para imprimir o boleto!</a></p>

                    <input type="hidden" name="txtStatus" value="APROVADO" />
                    <input type="hidden" name="txtformapag" value="BOLETO" />
                    <input type="submit" id='operacao' name='operacao' value='SALVAR'> 
                </form>
            </div>
            <div class="col-md-5 left-account ">            

                <div class="clearfix"> </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>


</div>