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
            <%                if (resultado != null && resultado.getMsg() != null) {
                    out.print(resultado.getMsg());
                }
            %>
        </h3>
        
        ${mensagem}

        <%
            //resultado = (Resultado) session.getAttribute("resultado");
            Resultado pedido = (Resultado) request.getAttribute("pedidos");

            if (pedido != null && pedido.getMsg() != null) {
        %>
        <H1><%= pedido.getMsg()%></H1>
        <h3>Número do pedido: <%= pedido.getEntidades().get(0).getId()%></h3>
        <p>Você pode acompanhar seu pedido atraves do menu <a href="SalvarPedidos?txtCliId=<%= cliente.getId()%>&operacao=CONSULTAR1"><b>Meus Pedidos</b></a></p>
        <%
            }
        %>
    </div>


</div>
