<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="e_commer.dominio.Cliente"%>
<%@page import="e_commer.dominio.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="e_commer.dominio.EntidadeDominio"%>
<%@page import="e_commer.core.aplicacao.Resultado"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="e_commer.core.aplicacao.Resultado, e_commer.dominio.*, java.util.*"%>


<div class="content">
    <!--Exibe mensagem se cliente foi cadastrado    -->
    <%
        //Resultado resultado = (Resultado) session.getAttribute("resultado");
        Resultado pedido = (Resultado) request.getAttribute("pedidos");
    %>
    <h1>Consulta de pedidos</h1>

    <form action="${pageContext.request.contextPath}/SalvarPedidos" method="post">
        <p> <label for="id">Nº Pedido:</label>
            <input type="text" id="id" name="txtId">
            <label for="nome">Nome Cliente:</label> 
            <input type="text" id="nome" name="txtNome">
            <input type="submit" id="operacao" name="operacao" value="CONSULTAR"/></p>
    </form>
    <%
        if (pedido != null && pedido.getMsg() != null) {
            out.print(pedido.getMsg());
        }
    %>
    <div class="clearfix"> </div>
    <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="100%" CELLPADDING="4" CELLSPACING="3">
        <tr>
            <th colspan="4"><strong>Pedidos</strong></th>
        </tr>
        <tr>
            <th>Nº Pedido</th>
            <th>Cliente</th>
            <th>DATA</th>
            <th>SITUAÇÃO</th>

        </tr>
        <%
            if (pedido != null) {
                List<EntidadeDominio> entidades = pedido.getEntidades();
                StringBuilder sbRegistro = new StringBuilder();
                StringBuilder sbLink = new StringBuilder();

                if (entidades != null) {
                    for (int i = 0; i < entidades.size(); i++) {
                        Pedido p = (Pedido) entidades.get(i);
                        sbRegistro.setLength(0);
                        sbLink.setLength(0);

                        sbRegistro.append("<tr align='center'>");

                        sbLink.append("<a href=SalvarPedidos?");
                        sbLink.append("txtId=");
                        sbLink.append(p.getId());
                        sbLink.append("&");
                        sbLink.append("operacao=");
                        sbLink.append("VISUALIZAR");
                        sbLink.append(">");
                        
                        //id
                        sbRegistro.append("<TD>");
                        sbRegistro.append(sbLink.toString());
                        sbRegistro.append(p.getId());
                        sbRegistro.append("</a>");
                        sbRegistro.append("</TD>");
                        //nome
                        sbRegistro.append("<TD>");
                        sbRegistro.append(sbLink.toString());
                        sbRegistro.append(p.getCliente().getNome());
                        sbRegistro.append("</a>");
                        sbRegistro.append("</TD>");

                        //data de nascimento
                        sbRegistro.append("<TD>");
                        sbRegistro.append(sbLink.toString());
                        String dtCad = ConverteDate.converteDateString(p.getDtCadastro());
                        sbRegistro.append(dtCad);
                        sbRegistro.append("</a>");
                        sbRegistro.append("</TD>");

                        //ativo
                        sbRegistro.append("<TD>");
                        sbRegistro.append(sbLink.toString());
                        sbRegistro.append(p.getStatus());
                        sbRegistro.append("</a>");
                        sbRegistro.append("</TD>");

                        sbRegistro.append("</TR>");

                        out.print(sbRegistro.toString());

                    }//for
                }//if(entidade)
            }//if(resultado)

        %>        
    </table>
</div>
