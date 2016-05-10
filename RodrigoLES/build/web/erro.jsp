<%-- 
    Document   : erro
    Created on : 07/04/2016, 10:26:45
    Author     : Henrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    Resultado resultado = (Resultado) request.getAttribute("resultado");
%>
<div class="content">
    <div class="four">
        <h3>Erro</h3>
        <p><%
            if (resultado != null && resultado.getMsg() != null) {
                out.print(resultado.getMsg());
            }
            %></p>
        <a href="index.jsp" class="more ">Voltar</a>

    </div>



</div>