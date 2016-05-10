<%-- 
    Document   : cartmsg
    Created on : 11/04/2016, 13:37:07
    Author     : Henrique
--%>

<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<div class="content">
    <div class="check-out">

        <H3>
            <%                
                if (resultado != null && resultado.getMsg() != null) {
                    out.print(resultado.getMsg());
                }
            %>
        </h3>
        
        <p>Exibir os dados do pedido e do cliente</p><br />


        <%
                //Resultado resultado = (Resultado) session.getAttribute("resultado");
                Resultado pedido = (Resultado) request.getAttribute("pedidos");

                if (pedido != null && pedido.getMsg() != null) {
                    out.print(pedido.getMsg());
                }
                if (resultado != null) {
                    List<EntidadeDominio> entidades = pedido.getEntidades();
                    StringBuilder sbRegistro = new StringBuilder();
                    StringBuilder sbLink = new StringBuilder();

                    sbRegistro.setLength(0);
                    sbLink.setLength(0);

                    sbRegistro.append("<div class=\"bottom-grid\">");
                    sbRegistro.append("<div class=\"col-md-3 store-top\">");
                    sbRegistro.append("<div class=\"bottom-grid-top\">");

                    sbRegistro.append("Pedido numero: ");
                    sbRegistro.append(pedido.getEntidades().get(0));

                    //sbRegistro.append("<div class=\"five\">");
                    //sbRegistro.append("<h6 class=\"one\">-50%</h6>");
                    sbRegistro.append("</div>");
                    sbRegistro.append("<div class=\"pre\">");
                    sbRegistro.append("<p>");
                    sbRegistro.append(entidades.get(0).getId());
                    sbRegistro.append("</p>");
                    sbRegistro.append("<span>R$");
                    //sbRegistro.append(art.getPrecoUnit());
                    sbRegistro.append("</span>");
                    sbRegistro.append("<div class=\"clearfix\"> </div>");
                    sbRegistro.append("</div></a>");

                    sbRegistro.append("</div>");
                    sbRegistro.append(" </div>");

                    sbRegistro.append("<div class=\"clearfix\"> </div></div>");

                    out.print(sbRegistro.toString());

                }

           
        %>
    </div>


</div>
