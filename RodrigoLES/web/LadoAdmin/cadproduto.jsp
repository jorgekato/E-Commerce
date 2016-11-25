<%-- 
    Document   : cadproduto
    Created on : 11/03/2016, 16:51:01
    Author     : Henrique
--%>



<%@page import="e_commer.dominio.Imagem"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
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
        <div class="account-top register">

            <form action="${pageContext.request.contextPath}/SalvarProduto" method="POST" enctype="multipart/form-data" id="validate">
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
                            if (produto != null) {
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
                                            sbRegistro.append("\"");
                                            if (produto.getCategoria().getNomeCategoria().equals(cat.getNomeCategoria())) {
                                                sbRegistro.append("selected");
                                            }
                                            sbRegistro.append(">");
                                            sbRegistro.append(cat.getNomeCategoria());
                                            sbRegistro.append("</option>");
                                            out.print(sbRegistro.toString());
                                        }
                                    }
                                }
                        %>

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
                                            sbRegistro.append("\"");
                                            sbRegistro.append(">");
                                            sbRegistro.append(cat.getNomeCategoria());
                                            sbRegistro.append("</option>");
                                            out.print(sbRegistro.toString());
                                        }
                                    }
                                }
                            }
                        %>

                    </select></p>

                <p>Quantidade: <input type="text" name="qtde" value=<%
                    if (produto != null) {
                        out.print("'" + produto.getQuantidade() + "'");
                    }
                                      %>
                                      ></input></p>

                <p>Qtde Maxima Venda: <input type="text" name="qtdeMaxVenda" value=<%
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
                    <textarea name="descricao" id="coment"><%
                        if (produto != null) {
                            out.print(produto.getDescricao());
                        }
                        %></textarea></p><br/>
                <!--Inicio do tratamento de imagem -->
                <p>
                    <%
                        Imagem[] img = null;
                        if (produto != null) {
                            img = produto.getFoto();
                        }
                        for (int i = 0; i < 3; i++) {
                    %>
                    <label for="txtImagem">Imagem <%out.print(i + 1); %>:</label>
                    <br>
                    <img src="data:image/jpg;base64, <%
                        if (produto != null && img[i] != null) {
                            for (int j = 0; j < 3; j++) {
                                if (img[j].getPosicao() == i) {
                                    out.print(ManipulaImagem.setImagemDimensao(img[j].getImagem(), 160, 160));
                                    break;
                                } else {
                                    continue;
                                }
                            }
                        } else {
                            String caminho = request.getServletContext().getRealPath("/images/sem_imagem.png");
                            File file = new File(caminho);
                            InputStream fis = new FileInputStream(file);
                            Imagem imagem0 = new Imagem();
                            imagem0.setImagem(ManipulaImagem.convertImagemBytes(fis));
                            out.print(ManipulaImagem.setImagemDimensao(imagem0.getImagem(), 160, 160));
                        }%>" id="imgImagem" class="preview" alt="preview da imagem" />                    
                    <br>
                    <input type="file" id="imgUpload" name="imgUpload<%out.print(i + 1); %>" onchange="imagemPreview();" 
                           accept="image/jpeg"/>
                    <br />
                    <%
                        }
                    %>                     
                    <!--Fim tratamento de imagens -->
                <p><input type="radio" name="txtFlgAtivo" value="TRUE"  <%
                    if (produto
                            != null) {
                        if (produto.getFlg_ativo()) {
                            out.print("checked=\"checked\"");
                        }
                    } else {
                        out.print("checked=\"checked\"");
                    }
                          %>/>Ativo </p>
                <p><input type="radio" name="txtFlgAtivo" value="FALSE" <%
                    if (produto
                            != null) {
                        if (!produto.getFlg_ativo()) {
                            out.print("checked=\"checked\"");
                        }

                    }
                          %>/>Desativado</p>

                <%
                    if (produto
                            != null) {
                        String dtCadastro = ConverteDate.converteDateString(produto.getDtCadastro());
                        out.print("<label for='txtDtCadastro'>Data de Cadastro:</label>");
                        out.print("<input type='text' id='txtDtCadastro' name='txtDtCadastro' value='" + dtCadastro + "' readonly />");
                    }


                %>

                <%                    if (produto
                            != null) {
                        out.print("<input type='submit'  name='operacao' value='ALTERAR'/>");
                    } else {
                        out.print("<input type='submit'  name='operacao' value='SALVAR'/>");
                    }

                %>


            </form>
        </div>

    </div>	


</div>
<!---->

