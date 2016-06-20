<%-- 
    Document   : cadproduto
    Created on : 11/03/2016, 16:51:01
    Author     : Henrique
--%>



<%@page import="e_commer.core.util.ManipulaImagem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="e_commer.dominio.Categorias"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page import=" e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="content">
    <%        Produto produto = (Produto) request.getAttribute("produto");
    %>
    <div class="account-in">
        <div>
            <header><h1>Cadastro de produtos</h1></header></div>
        <div>
            <form action="${pageContext.request.contextPath}/SalvarProduto" method="POST" enctype="multipart/form-data">
                <!--<p>Código de Barra: <input type="text"/></p>-->
                <label for="txtId">Id:</label>
                <input type="text" id="txtId" name="txtId" value=

                       <%
                           if (produto != null) {
                               out.print("'" + produto.getId() + "' readonly");
                           }
                       %>

                       ></input>
                <p>Nome: <input type="text" name="nome" value= 

                                <%
                                    if (produto != null) {
                                        out.print("'" + produto.getNome() + "'");
                                    }
                                %>
                                ></p>
                <p>Marca: <input type="text" name="marca" value= 

                                 <%
                                     if (produto != null) {
                                         out.print("'" + produto.getMarca() + "'");
                                     }
                                 %>
                                 ></p>
                <p>Modelo: <input type="text" name="modelo" value= 

                                  <%
                                      if (produto != null) {
                                          out.print("'" + produto.getModelo() + "'");
                                      }
                                  %>
                                  ></p>
                <p>Categoria: <select name="categorias" id="categorias">
                        <%
                            if (produto != null) {%>
                        <option value="<%= produto.getCategoria().getId()%>"> <%= produto.getCategoria().getNomeCategoria()%></option>
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

                <p>Quantidade: <input type="text" name="qtde" value= 

                                      <%
                                          if (produto != null) {
                                              out.print("'" + produto.getQuantidade() + "'");
                                          }
                                      %>
                                      ></input></p>

                <p>Qtde Maxima Venda: <input type="text" name="qtdeMaxVenda" value= 

                                             <%
                                                 if (produto != null) {
                                                     out.print("'" + produto.getQtdeMaxVenda() + "'");
                                                 }
                                             %>
                                             ></input></p>

                <p>Valor unitário: <input type="text" name="valorUnit" value= 

                                          <%
                                              if (produto != null) {
                                                  out.print("'" + produto.getPrecoUnit() + "'");
                                              }
                                          %>
                                          ></input></p>
                <p>Estoque mínimo: <input type="text" name="estoMin" value= 

                                          <%
                                              if (produto != null) {
                                                  out.print("'" + produto.getEstoqueMin() + "'");
                                              }
                                          %>
                                          ></p>

                <!-- aumentar a textarea -->
                <p><label for="descricao">Descrição:</label>
                    <textarea name="descricao" id="coment" 


                              ><%
                                  if (produto != null) {
                                      out.print(produto.getDescricao());
                                  }
                        %></textarea></p>
                <p> 
                <label for="txtImagem">Imagem:</label>
                <br>
                <img src="data:image/jpg;base64, <% if (produto != null) {
                        out.print(ManipulaImagem.setImagemDimensao(produto.getFoto().getImagem(), 160, 160));
                    }%>" id="imgImagem" class="preview" alt="preview da imagem" />
                <img src="data:image/jpg;base64, <% if (produto != null) {
                        out.print(ManipulaImagem.setImagemDimensao(produto.getFoto().getImagem(), 160, 160));
                    }%>" id="imgImagem" class="preview" alt="preview da imagem" />
                <br>
                <input type="file" id="imgUpload" name="imgUpload" onchange="imagemPreview();" 
                       accept="image/jpeg"/></p>
                <br />
                <br />
                <p><input type="radio" name="txtFlgAtivo" value="TRUE"  <%
                    if (produto != null) {
                        if (produto.getFlg_ativo()) {
                            out.print("checked=\"checked\"");
                        }
                    }
                          %>/>Ativo </p>
                <p><input type="radio" name="txtFlgAtivo" value="FALSE" <%
                    if (produto != null) {
                        if (produto.getFlg_ativo()) {
                            out.print("checked=\"checked\"");
                        }

                    }
                          %>/>Desativado</p>

                <%
                    if (produto != null) {
                        String dtCadastro = ConverteDate.converteDateString(produto.getDtCadastro());
                        out.print("<label for='txtDtCadastro'>Data de Cadastro:</label>");
                        out.print("<input type='text' id='txtDtCadastro' name='txtDtCadastro' value='" + dtCadastro + "' readonly />");
                    }


                %>

                <%                    if (produto != null) {
                        out.print("<input type='submit' id='operacao' name='operacao' value='ALTERAR'/>");
                    } else {
                        out.print("<input type='submit' id='operacao' name='operacao' value='SALVAR'/>");
                    }

                %>


            </form>
        </div>

    </div>	


</div>
<!---->

