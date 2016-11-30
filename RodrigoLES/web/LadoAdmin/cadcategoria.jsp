<%-- 
    Document   : cadcliente
    Created on : 12/03/2016, 10:12:53
    Author     : Henrique
--%>
<%@page import="java.util.List"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page import=" e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Categorias"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="content">
    <%
        Categorias cat = (Categorias) request.getAttribute("categoria");
    %>
    <div class="account-in">
        <div>
            <header><h1>Cadastro de categoria</h1></header></div>
        <div class="account-top register">
            <form action="${pageContext.request.contextPath}/SalvarCategoria" method="POST" id="validate">
                
                <p>ID: <input type="text" id="txtId" name="txtId" value=
		
		<%		
			if(cat != null){ 
                            out.print("'"+cat.getId()+"' readonly/>");
                        }else
                            out.print(">");
		%>
		
		</p>
                <p>Categoria: <input type="text" name="txtNome" value="<%
                    if (cat != null){  
                        out.print(cat.getNomeCategoria()); 
                    }
                %>"/></p>
                
                <!-- aumentar a textarea -->
                <p><label for="descricao">Descrição:</label>
                    <textarea name="txtDescricao" id="coment" value="<%
                        if (cat != null){ 
                            out.print(cat.getDescricao());      
                        }
                    %>"></textarea></p>
                
                <p><label for="situacao">Situação: </label>
                    <input type="radio" name="txtFlgAtivo" value="TRUE"  <%
                    if (cat != null) {
                        if (cat.getFlg_ativo()) {
                            out.print("checked=\"checked\"");
                        }
                    }else{
                        out.print("checked=\"checked\"");
                    }
                          %>/>Ativo
                <input type="radio" name="txtFlgAtivo" value="FALSE" <%
                    if (cat != null) {
                        if (!cat.getFlg_ativo()) {
                            out.print("checked=\"checked\"");
                        }
                    }
                          %>/>Inativo</p>

                <%
                    if (cat != null) {
                        String dtCadastro = ConverteDate.converteDateString(cat.getDtCadastro());
                        out.print("<label for='txtDtCadastro'>Data de Cadastro:</label>");
                        out.print("<input type='text' id='txtDtCadastro' name='txtDtCadastro' value='" + dtCadastro + "' readonly />");
                    }


                %>

                <%                                 if (cat != null) {
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
