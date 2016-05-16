<%-- 
    Document   : cadartesanato
    Created on : 12/03/2016, 10:45:18
    Author     : Henrique
--%>
<%@page import="e_commer.dominio.Categorias"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page import=" e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="content">
    <%        Artesanato artesanato = (Artesanato) request.getAttribute("artesanato");
    %>
    <div class="account-in">
        <div>
            <header><h1>Cadastro de artesanatos</h1></header></div>
        <div>
            <form action="${pageContext.request.contextPath}/SalvarArtesanato" method="POST">
                <label for="txtId">Id:</label>
                <input type="text" id="txtId" name="txtId" value=
                       <%
                           if (artesanato != null) {
                               out.print("'" + artesanato.getId() + "' readonly>");
                           } else {
                               out.print(">");
                           }
                       %>


                       <p>Nome: <input type="text" name="nome" value=
                                <%
                                    if (artesanato != null) {
                                        out.print("'" + artesanato.getNome() + "' readonly>");
                                    } else {
                                        out.print(">");
                                    }
                                %>
                </p>

                <p>Categoria: <select name="catId" id="categorias">
                        <%
                            if (artesanato != null) {%>
                        <option value="<%= artesanato.getCategoria().getId()%>"> <%= artesanato.getCategoria().getNomeCategoria()%></option>
                        <%

                            } else {
                                categoria = (Resultado) request.getAttribute("categorias");
                                if (categoria != null) {
                                    List<EntidadeDominio> entidades = categoria.getEntidades();
                                    StringBuilder sbRegistro = new StringBuilder();
                                    StringBuilder sbLink = new StringBuilder();

                                    if (entidades != null) {
                                        for (int i = 0; i < entidades.size(); i++) {
                                            Categorias cat = (Categorias) entidades.get(i);
                                            sbRegistro.setLength(0);
                                            sbLink.setLength(0);

                                            sbRegistro.append("<option value=\"");
                                            sbRegistro.append(cat.getId());
                                            sbRegistro.append("\">");
                                            sbRegistro.append(cat.getNomeCategoria());
                                            sbRegistro.append("</option>");
                                            out.print(sbRegistro.toString());
                                        }
                                    }
                                }
                            }
                        %>


                    </select></p>
                <p>Valor unitário: <input type="text" name="valorUnit" value="<%                    if (artesanato != null) {
                        out.print(artesanato.getPrecoUnit());
                    }
                                          %>"/></p>
                <p>Cores: <input type="text" name="cores" value="<%
                    if (artesanato != null) {
                        out.print(artesanato.getCores());
                    }
                                 %>"/></p>

                <!-- aumentar a textarea -->
                <p><label for="desccricao">Descrição:</label>
                    <textarea name="descricao" id="coment"><%
                        if (artesanato != null) {
                            out.print(artesanato.getDescricao());
                        }
                        %></textarea></p>
                <p>Imagem: Campo para inserir a imagem</p>  
                <p><input type="radio" name="txtFlgAtivo" value="TRUE"  <%
                    if (artesanato != null) {
                        if (artesanato.getFlg_ativo()) {
                            out.print("checked=\"checked\"");
                        }
                    }
                          %>/>Ativo </p>
                <p><input type="radio" name="txtFlgAtivo" value="FALSE" <%
                    if (artesanato != null) {
                        if (!artesanato.getFlg_ativo()) {
                            out.print("checked=\"checked\"");
                        }
                    }
                          %>/>Desativado</p>

                <%
                    if (artesanato != null) {
                        String dtCadastro = ConverteDate.converteDateString(artesanato.getDtCadastro());
                        out.print("<label for='txtDtCadastro'>Data de Cadastro:</label>");
                        out.print("<input type='text' id='txtDtCadastro' name='txtDtCadastro' value='" + dtCadastro + "' readonly />");
                    }


                %>


                <%                    if (artesanato != null) {
                        out.print("<input type='submit' id='operacao' name='operacao' value='ALTERAR'/>");
                        out.print("<input type='submit' id='operacao' name='operacao' value='EXCLUIR'/>");
                    } else {
                        out.print("<input type='submit' id='operacao' name='operacao' value='SALVAR'/>");
                    }

                %>
            </form>
        </div>

    </div>	


</div>
<!---->
