<%-- 
    Document   : msggeral
    Created on : 19/03/2016, 01:29:17
    Author     : Henrique
--%>
<%@page import="e_commer.core.aplicacao.Resultado"%>

            <div class="content">
                <div class="account-in">
                    <%
                        resultado = (Resultado) request.getAttribute("resultado");
                    %>
                    <div>
                        <header><h1>Mensagem</h1></header></div>
                            <%
                                if (resultado != null && resultado.getMsg() != null) {
                                    out.print(resultado.getMsg());
                                }

                            %>
                    <BR>

                </div>	


            </div>
            <!---->
            