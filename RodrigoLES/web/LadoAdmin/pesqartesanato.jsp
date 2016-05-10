<%-- 
    Document   : pesqartesanato
    Created on : 19/03/2016, 20:12:40
    Author     : Henrique
--%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.dominio.Artesanato"%>



<div class="content">
    <div class="account-in">
        <div>
            <header><h1>Pesquisa de artesanatos</h1></header></div>
                <%
                    resultado = (Resultado) request.getAttribute("resultado");
                %>

        <form action="${pageContext.request.contextPath}/SalvarArtesanato" method="post">
            <label for="txtId">Id:</label> <input type="text" id="txtId"
                                                  name="txtId" /> </br> <label for="txtDescricao">DESCRIÇÃO:</label> <input
                                                  type="text" id="txtDescricao" name="txtDescricao" /> <input
                                                  type="submit" id="operacao" name="operacao" value="CONSULTAR" />
        </form>
        <%
            if (resultado != null && resultado.getMsg() != null) {
                out.print(resultado.getMsg());
            }

        %>
        <BR>

        <TABLE BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
            <TR>
                <TH COLSPAN="4"><BR>
                    <H3>Artesanato</H3>
                </TH>
            </TR>

            <TR>
                <TH>ID</TH>
                <TH>Nome</TH>
                <TH>DtCadastro</TH>                
                <TH>Status:</TH>
            </TR>
            <%       if (resultado != null) {
                    List<EntidadeDominio> entidades = resultado.getEntidades();
                    StringBuilder sbRegistro = new StringBuilder();
                    StringBuilder sbLink = new StringBuilder();

                    if (entidades != null) {
                        for (int i = 0; i < entidades.size(); i++) {
                            Artesanato art = (Artesanato) entidades.get(i);
                            sbRegistro.setLength(0);
                            sbLink.setLength(0);

                            sbRegistro.append("<TR ALIGN='CENTER'>");

                            sbLink.append("<a href=SalvarArtesanato?");
                            sbLink.append("txtId=");
                            sbLink.append(art.getId());
                            sbLink.append("&");
                            sbLink.append("operacao=");
                            sbLink.append("VISUALIZAR");

                            sbLink.append(">");

                            sbRegistro.append("<TD>");
                            sbRegistro.append(sbLink.toString());
                            sbRegistro.append(art.getId());
                            sbRegistro.append("</a>");
                            sbRegistro.append("</TD>");

                            sbRegistro.append("<TD>");
                            sbRegistro.append(sbLink.toString());
                            sbRegistro.append(art.getNome());
                            sbRegistro.append("</a>");
                            sbRegistro.append("</TD>");

                            sbRegistro.append("<TD>");
                            sbRegistro.append(sbLink.toString());
                            String dtCad = ConverteDate.converteDateString(art.getDtCadastro());
                            sbRegistro.append(dtCad);
                            sbRegistro.append("</a>");
                            sbRegistro.append("</TD>");

                            sbRegistro.append("<TD>");
                            sbRegistro.append(sbLink.toString());
                            if (art.getFlg_ativo()) {
                                sbRegistro.append("Ativo");
                            } else {
                                sbRegistro.append("Inativo");
                            }
                            sbRegistro.append("</a>");
                            sbRegistro.append("</TD>");

                            sbRegistro.append("</TR>");

                            out.print(sbRegistro.toString());

                        }
                    }

                }

            %>
        </TABLE>
    </div>
    <!---->
