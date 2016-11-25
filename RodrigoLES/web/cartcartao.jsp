<%-- 
    Document   : cartocartão
    Created on : 24/06/2016, 00:17:15
    Author     : Henrique
--%>


<%@page import="e_commer.core.util.FormatDouble"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<div class="content">
    <div class="check-out">


        <%      if (carrinho != null) {

                for (EntidadeDominio e : carrinho.getEntidades()) {
                    carrinhos = (CarrinhoCompra) e;
                }
            }
            if (carrinhos != null) {
                List<AbstractItem> entidades = carrinhos.getItens();
                StringBuilder sbRegistro = new StringBuilder();
                StringBuilder sbLink = new StringBuilder();
                StringBuilder upDate = new StringBuilder();
                StringBuilder sbExcluir = new StringBuilder();

                if (entidades != null) {
                    for (int i = 0; i < entidades.size(); i++) {

                        if (ItemArtesanato.class.getName().equals(entidades.get(i).getClass().getName())) {
                            ItemArtesanato item = (ItemArtesanato) entidades.get(i);
                            sbRegistro.setLength(0);
                            sbLink.setLength(0);
                            upDate.setLength(0);
                            sbExcluir.setLength(0);

                            out.print(sbRegistro.toString());
                        } else if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {
                            ItemProduto item = new ItemProduto();
                            item = (ItemProduto) entidades.get(i);

                            sbRegistro.setLength(0);
                            sbLink.setLength(0);
                            upDate.setLength(0);
                            sbExcluir.setLength(0);

                            out.print(sbRegistro.toString());
                        }

                    }
                }
            }
        %>


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
            <h2>Dados do cartão</h2>
            <div class="col-md-7 account-top">
                <form action="${pageContext.request.contextPath}/SalvarPedidos" method="POST">
                    <div> 	
                        <span>Número do cartão:</span>
                        <input type="text" name="txtNumCartao"> 
                    </div>
                    <div> 
                        <span  class="pass">Nome impresso no cartão</span>
                        <input type="text" name="txtNomeCartao">
                    </div>
                    <div> 
                        <span  class="pass">Validade:</span>
                        <input type="text" name="txtMesCartao" placeholder="Mês">
                        <input type="text" name="txtAnoCartao" placeholder="Ano">
                    </div>
                    <div> 
                        <span  class="pass">Código de segurança:</span>
                        <input type="text" name="txtCodigoCartao">
                    </div>
                    <div> 
                        <span  class="pass">Pagar:</span>
                        <select id="Select4" name="D4">

                            <%
                                if (carrinho != null) {

                                    for (EntidadeDominio e : carrinho.getEntidades()) {
                                        carrinhos = (CarrinhoCompra) e;
                                    }
                                }
                                if (carrinhos != null) {
                                    StringBuilder sbRegistro = new StringBuilder();

                                    if (carrinhos.getCredito() != null) {
                                        int nParcelas = 0;
                                        for (int i = 6; i > 0; i--) {
                                            if (i == 1) {
                                                nParcelas = i;
                                                break;
                                            } else if ((carrinhos.getTotal() - carrinhos.getCredito().getSaldo()) / i < 50) {
                                                continue;

                                            } else {
                                                nParcelas = i;
                                                break;
                                            }
                                        }
                                        for (int i = 0; i < nParcelas; i++) {

                                            sbRegistro.setLength(0);
                                            sbRegistro.append("<option>");
                                            sbRegistro.append(i + 1);
                                            sbRegistro.append("x de R$");
                                            sbRegistro.append(FormatDouble.formataDouble((carrinhos.getTotal() - carrinhos.getCredito().getSaldo()) / (i + 1)));
                                            sbRegistro.append(" sem juros</option>");
                                            out.print(sbRegistro.toString());
                                        }
                                    } else {
                                        int nParcelas = 0;
                                        for (int i = 6; i > 0; i--) {
                                            if (i == 1) {
                                                nParcelas = i;
                                                break;
                                            } else if ((carrinhos.getTotal()) / i < 50) {
                                                continue;

                                            } else {
                                                nParcelas = i;
                                                break;
                                            }
                                        }
                                        for (int i = 0; i < nParcelas; i++) {

                                            sbRegistro.setLength(0);
                                            sbRegistro.append("<option>");
                                            sbRegistro.append(i + 1);
                                            sbRegistro.append("x de R$");
                                            sbRegistro.append(FormatDouble.formataDouble(carrinhos.getTotal() / (i + 1)));
                                            sbRegistro.append(" sem juros</option>");
                                            out.print(sbRegistro.toString());
                                        }
                                    }
                                }


                            %>

                        </select>
                    </div>

                    <input type="hidden" name="txtStatus" value="APROVADO" />
                    <input type="hidden" name="txtformapag" value="CARTAO" />
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