<%-- 
    Document   : produtostore
    Created on : 06/04/2016, 15:16:07
    Author     : Henrique
--%>

<%@page import="e_commer.core.util.ManipulaImagem"%>
<%@page import="e_commer.dominio.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content">

    <div class="content-bottom store">
        <h3>produtos</h3>

        <%  resultado = (Resultado) request.getAttribute("resultado");
            if (resultado != null) {
                List<EntidadeDominio> entidades = resultado.getEntidades();
                StringBuilder sbRegistro = new StringBuilder();
                StringBuilder sbLink = new StringBuilder();
                int contador = 0, nAux = 0;

                if (entidades != null) {
                    for (int i = 0; i < entidades.size(); i++) {

                        Produto pro = (Produto) entidades.get(i);

                        sbRegistro.setLength(0);
                        sbLink.setLength(0);

                        if (contador % 4 == 0) {
                            nAux = contador + 4;
                            sbRegistro.append("<div class=\"bottom-grid\">");
                        }
                        sbRegistro.append("<div class=\"col-md-3 store-top\">");
                        sbRegistro.append("<div class=\"bottom-grid-top\">");

                        sbLink.append("<a href=SalvarProduto?");
                        sbLink.append("txtId=");
                        sbLink.append(pro.getId());
                        sbLink.append("&");
                        sbLink.append("operacao=");
                        sbLink.append("VISUALIZAR1>");
                        //sbLink.append("<img class=\"img-responsive\" src=\"images/sh.png\" alt=\"\" />");
                        sbLink.append("<img class=\"img-responsive\" src=\"data:image/jpg;base64, ");
                        sbLink.append(ManipulaImagem.setImagemDimensao(pro.getFoto().getImagem(), 175, 144));
                        sbLink.append("\" alt=\"\" />");
                        sbLink.append("</a>");

                        sbRegistro.append(sbLink.toString());
                        sbRegistro.append("<div class=\"five\">");
                        sbRegistro.append("<h6 class=\"one\">-50%</h6>");
                        sbRegistro.append("</div>");
                        sbRegistro.append("<div class=\"pre\">");
                        sbRegistro.append("<p>");
                        sbRegistro.append(pro.getNome());
                        sbRegistro.append("</p>");
                        sbRegistro.append("<span>R$");
                        sbRegistro.append(pro.getPrecoUnit());
                        sbRegistro.append("</span>");
                        sbRegistro.append("<div class=\"clearfix\"> </div>");
                        sbRegistro.append("</div></a>");

                        sbRegistro.append("</div>");
                        sbRegistro.append(" </div>");
                        contador++;
                        if (contador == nAux) {
                            nAux = contador + 4;
                            sbRegistro.append("<div class=\"clearfix\"> </div></div>");
                        }

                        out.print(sbRegistro.toString());

                    }

                }

            }

        %>

    </div>
</div>
