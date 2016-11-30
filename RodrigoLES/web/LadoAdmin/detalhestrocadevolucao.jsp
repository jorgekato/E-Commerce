<%-- 
    Document   : detalhestrocadevolucao
    Created on : 07/05/2016, 16:52:12
    Author     : Jorge
--%>
<%@page import="e_commer.core.util.FormatDouble"%>
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
    <h3>Detalhes Troca e Devolução</h3>
    <%        //Resultado resultado = (Resultado) session.getAttribute("resultado");
        resultado = (Resultado) request.getAttribute("trocadevolucao");

        if (resultado != null) {
            TrocaDevolucao td = (TrocaDevolucao) resultado.getEntidades().get(0);
    %>
    <div class="clearfix"> </div>
    <form action="SalvarTrocaDevolucao" method="post">
        <input type="hidden" name="txtIdPed" value="<%= td.getPedido().getId()%>"/>
        <input type="hidden" name="txtIdtd" value="<%= td.getId()%>"/>
        <input type="hidden" name="txtCliId" value="<%= td.getPedido().getCliente().getId()%>"/>

        <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3" width="1000%" CELLPADDING="4" CELLSPACING="3">
            <tr>
                <th colspan="6"><strong>Pedido</strong></th>
            </tr>
            <tr>
                <td>Nº Pedido</td>
                <td>Data</td>
                <td>Cliente</td>
                <td>Email</td>
                <td>Status</td>
            </tr>
            <tr>
                <td><%= td.getPedido().getId()%></td>
                <td><%= ConverteDate.converteDateString(td.getDtCadastro())%></td>
                <td><%= td.getPedido().getCliente().getNome()%></td>
                <td><%= td.getPedido().getCliente().getEmail()%></td>
                <td><%= td.getStatus()%></td>
            </tr>
        </table>
        <h3>Produto e Motivo</h3>
        <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive">
            <%
                for (int i = 0; i < td.getPedido().getItens().size(); i++) {//qtde de itens
                    if (ItemArtesanato.class.getName().equals(td.getPedido().getItens().get(i).getClass().getName())) {
                        ItemArtesanato item = (ItemArtesanato) td.getPedido().getItens().get(i);
            %>

            <tr>
                <th>Cod Produto</th>
                <td><input type="text" name="txtIdpro" value="<%= item.getArtesanato().getId()%>" readonly/></td>
            </tr>
            <tr>
                <th>Produto</th>
                <td><input type="text" name="txtNome" value="<%= item.getArtesanato().getNome()%>" readonly/></td>
            </tr>
            <tr>
                <th>Quantidade</th>
                <td><input type="text" name="txtQtde" value="<%= item.getQuantidade()%>" readonly/></td>
            </tr>
            <tr>
                <th>Valor Unit</th>
                <td><input type="text" name="txtVlrUnit" value="<%= FormatDouble.formataDouble(item.getArtesanato().getPrecoUnit())%>" readonly/></td>
            </tr>
            <tr>
                <th>Total</th>
                <td><input type="text" name="txtSubTotal" value="<%= FormatDouble.formataDouble(item.getQuantidade() * item.getArtesanato().getPrecoUnit())%>" readonly/></td>
            </tr>
            <tr>
                <th>Qtde para Troca/Devolução</th>
                <td><input type="text" name="txtQtdeDev" value="<%= td.getQuantidade()%>" readonly/></td>
            </tr>
            <tr>
                <th>Total da Devolução</th>
                <td><input type="text" name="txtTotalDev" value="<%= FormatDouble.formataDouble(td.getQuantidade() * item.getArtesanato().getPrecoUnit())%>" readonly/></td>
            </tr>
            <%
            } else if (ItemProduto.class.getName().equals(td.getPedido().getItens().get(i).getClass().getName())) {
                ItemProduto item = (ItemProduto) td.getPedido().getItens().get(i);
            %>
            <tr>
                <th>Cod Produto</th>
                <td><input type="text" name="txtIdpro" value="<%= item.getProduto().getId()%>" readonly/></td>
            </tr>
            <tr>
                <th>Produto</th>
                <td><input type="text" name="txtNome" value="<%= item.getProduto().getNome()%>" readonly/></td>
            </tr>
            <tr>
                <th>Quantidade</th>
                <td><input type="text" name="txtQtde" value="<%= item.getQuantidade()%>" readonly/></td>
            </tr>
            <tr>
                <th>Valor Unit</th>
                <td><input type="text" name="txtVlrUnit" value="<%= FormatDouble.formataDouble(item.getProduto().getPrecoUnit())%>" readonly/></td>
            </tr>
            <tr>
                <th>Total</th>
                <td><input type="text" name="txtSubTotal" value="<%= FormatDouble.formataDouble(item.getQuantidade() * item.getProduto().getPrecoUnit())%>" readonly/></td>
            </tr>
            <tr>
                <th>Qtde para Troca/Devolução</th>
                <td><input type="text" name="txtQtdeDev" value="<%= td.getQuantidade()%>" readonly/></td>
            </tr>
            <tr>
                <th>Total da Devolução</th>
                <td><input type="text" name="txtTotalDev" value="<%= FormatDouble.formataDouble(td.getQuantidade() *item.getProduto().getPrecoUnit())%>" readonly/></td>
            </tr>
            <%
                    }//else
                }//for i
%> 
            <tr><td>==========</td></tr>
            
            <tr>
                <th>Motivo</th>
                <td><input type="text" name="txtMotivo" value="<%= td.getMotivo()%>" readonly/></td>
            </tr>
            <tr>
                <th>Anotação</th>
            </tr>
            <tr>
                <td colspan="2"><textarea name="txtAnotacao" cols="40" readonly><%= td.getAnotacao()%></textarea></td>
            </tr>
            <tr>
                <th>Ação</th>
                <td>
                    <%if (!td.getAcao().equals("")) {%>
                    <input type="text" name="txtAcao" value="<%= td.getAcao()%>" readonly/>
                    <%
                    }else{
                    %>
                    <select name="txtAcao">
                        <option value="" disabled="" selected></option>
                        <option value="CREDITO NA LOJA">Crédito na Loja</option>
                        <option value="REEMBOLSO">Reembolso</option>
                        <option value="SUBSTITUICAO DO PRODUTO">Substituição do Produto</option>
                    </select>
                    <%}%>
                </td>
            </tr>
            <tr>
                <th>Novo Status</th>
                <td>
                    <%if (td.getStatus().equals("CONCLUIDO")){%>
                    <input type="text" name="txtStatus" value="<%= td.getStatus()%>" readonly/>
                    <%
                    }else{
                    %>
                    <select name="txtStatus">
                        <option value="" disabled="" selected></option>
                        <option value="ENVIADO">Enviado</option>
                        <option value="AGUARDANDO O PRODUTO">Aguardando Produto</option>
                        <option value="CONCLUIDO">Concluido</option>
                        <option value="CANCELADO">Cancelado</option>
                    </select> 
                    <%}%>
                </td>
            </tr>
            <tr>
                <th>Comentario</th>
            </tr>
            <tr>
                <td>
                    <textarea name="txtComentario" cols="40"></textarea>
                </td>
            </tr>
            <tr>
                <td><input type="submit" name="operacao" value="ALTERAR"/></td>
                <td><input type="submit" name="operacao" value="HISTORICO"/></td>
            </tr>
        </table>
        <%
            }//if(entidade)
        %> 



    </form>
</div>




