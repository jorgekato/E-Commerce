<%-- 
    Document   : cartconfirmar
    Created on : 11/04/2016, 13:40:12
    Author     : Henrique
--%>

<%@page import="e_commer.core.util.FormatDouble"%>
<%@page import="e_commer.dominio.Endereco"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<div class="content">
    <div class="check-out">


        <%      if (carrinho != null) {
        %>
        <TABLE class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
            <TR>
                <TH COLSPAN="3"><BR>
                    <H3>Itens</H3>
                </TH>
            </TR>

            <TR>

                <TH>Nome</TH>
                <TH>Quantidade</TH>                
                <TH>Valor Unit.</TH>
            </TR>
            <%
                    for (EntidadeDominio e : carrinho.getEntidades()) {
                        carrinhos = (CarrinhoCompra) e;
                    }
                }
                if (carrinhos != null) {
                    List<AbstractItem> entidades = carrinhos.getItens();
                    StringBuilder sbRegistro = new StringBuilder();
                    StringBuilder sbLink = new StringBuilder();
                    StringBuilder upDate = new StringBuilder();
                    StringBuilder sbExcluir = new StringBuilder();

                    if (entidades != null) {
                        for (int i = 0; i < entidades.size(); i++) {

                            if (ItemArtesanato.class.getName().equals(entidades.get(i).getClass().getName())) {
                                ItemArtesanato item = (ItemArtesanato) entidades.get(i);
                                sbRegistro.setLength(0);
                                sbLink.setLength(0);
                                upDate.setLength(0);
                                sbExcluir.setLength(0);

                                sbRegistro.append("<TR ALIGN='CENTER'>");

                                sbLink.append("<a href=SalvarArtesanato?");
                                sbLink.append("txtId=");
                                sbLink.append(item.getArtesanato().getId());
                                sbLink.append("&");
                                sbLink.append("operacao=");
                                sbLink.append("VISUALIZAR1");
                                sbLink.append(">");

                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                sbRegistro.append(item.getArtesanato().getNome());
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("<TD>");
                                sbRegistro.append("<input type=\"number\" name=\"quantidade\" id=\"idade\" min=\"1\" max=\"120\" value=\"");
                                sbRegistro.append(item.getQuantidade());
                                sbRegistro.append("\" />");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                sbRegistro.append(FormatDouble.formataDouble(item.getArtesanato().getPrecoUnit() * item.getQuantidade()));
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                upDate.append("<a href=SalvarCarrinho?");
                                upDate.append("idItem=");
                                upDate.append(i);
                                upDate.append("&");
                                upDate.append("qtde=");
                                upDate.append(item.getQuantidade()); //ver como colocar o valor do campo qtde aqui
                                upDate.append("&");
                                upDate.append("operacao=");
                                upDate.append("ATUALIZAR");
                                upDate.append(">");

                                sbRegistro.append("<TD>");
                                sbRegistro.append(upDate.toString());
                                sbRegistro.append("Atualizar");
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbExcluir.append("<a href=SalvarCarrinho?");
                                sbExcluir.append("idItem=");
                                sbExcluir.append(i);
                                sbExcluir.append("&");
                                sbExcluir.append("operacao=");
                                sbExcluir.append("EXCLUIRITEM");
                                sbExcluir.append(">");

                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbExcluir.toString());
                                sbRegistro.append("Remover");
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("</TR>");

                                out.print(sbRegistro.toString());
                            } else if (ItemProduto.class.getName().equals(entidades.get(i).getClass().getName())) {
                                ItemProduto item = new ItemProduto();
                                item = (ItemProduto) entidades.get(i);

                                sbRegistro.setLength(0);
                                sbLink.setLength(0);
                                upDate.setLength(0);
                                sbExcluir.setLength(0);

                                sbRegistro.append("<TR ALIGN='CENTER'>");

                                sbLink.append("<a href=SalvarProduto?");
                                sbLink.append("txtId=");
                                sbLink.append(item.getProduto().getId());
                                sbLink.append("&");
                                sbLink.append("operacao=");
                                sbLink.append("VISUALIZAR1");
                                sbLink.append(">");

                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                sbRegistro.append(item.getProduto().getNome());
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("<TD>");
                                sbRegistro.append("<input type=\"number\" name=\"qtde\" id=\"idade\" min=\"1\" max=\"120\" value=\"");
                                sbRegistro.append(item.getQuantidade());
                                sbRegistro.append("\" />");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                sbRegistro.append(FormatDouble.formataDouble(item.getProduto().getPrecoUnit() * item.getQuantidade()));
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("</TR>");

                                out.print(sbRegistro.toString());
                            }

                        }
                    }
                    if(carrinhos.getCredito() != null){
                
            %> 
            <TR>
                <TD COLSPAN="3" align="right"><b>Desconto:</b><input type="text" name="total" value="<%= FormatDouble.formataDouble(carrinhos.getCredito().getSaldo()) %>"/></TD>                
            </TR>
            <TR>
                <TD COLSPAN="3" align="right"><b>Total:</b><input type="text" name="total" value="<%= FormatDouble.formataDouble(carrinhos.getTotal() - carrinhos.getCredito().getSaldo()) %>"/></TD>                
            </TR>
            <% }else { %>
            <TR>
                <TD COLSPAN="3" align="right"><b>Total:</b><input type="text" name="total" value="<%= FormatDouble.formataDouble(carrinhos.getTotal()) %>"/></TD>                
            </TR>
            <%
                }
                }//if carrinhos != null
            %>

        </TABLE>
        <br />
        <br/>


        <a href="SalvarEndereco?txtId=<%= cliente.getId()%>&operacao=VISUALIZAR3"><font color="red"><b>Alterar Endereço</b></font></a>

        <form action="SalvarCarrinho" method="get">
            <TABLE class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
                <TR>
                    <TH COLSPAN="2">
                <H3>Endereço de entrega</h3>
                </TH>
                </TR>
                <%
                    if (cliente != null) {
                        StringBuilder sbCliente = new StringBuilder();
                        List<EntidadeDominio> enderecos = cliente.getEndereco();

                        for (int i = 0; i < enderecos.size(); i++) {
                            Endereco e = (Endereco) enderecos.get(i);
                            sbCliente.setLength(0);

                %>
                <tr>
                    <td>
                        <div style="float: bottom; margin: 0px 10px 30px 20px;border-width: medium">
                            <p><label for="logradouro"><%= e.getLogradouro()%>, </label>
                                <label for="numero"><%= e.getNumero()%></label></p>
                                <%if (e.getComplemento() != null) {%>
                            <p><label for="complemento"><%= e.getComplemento()%></label></p>
                                <%}%>
                            <p><label for="bairro"><%= e.getBairro()%></label></p>
                            <p><label for="cidade"><%= e.getCidade().getNome()%>/ </label>
                                <label for="estado"><%= e.getCidade().getEstado().getNome()%></label></p>
                            <p><label for="cep"><%= e.getCep()%></label></p>
                        </div>
                    </td>
                    <td >
                        <div>
                            <%if (i == 0) {%> <!-- Endereco padrao de entrega sera sempre o primeiro endereco. 
                                               Valor é igual ao id do endereco. -->
                            <input type="radio" id="radio" name="endEntrega" value="<%= e.getId()%>" checked/>
                            <%} else {%>
                            <input type="radio" id="radio" name="endEntrega" value="<%= e.getId()%>"/>
                            <%}%>
                        </div>
                    </td>
                </tr>       

                <% }
                    }
                %>
            </TABLE><br>
            <h3>Tipo de Entrega</h3>
            <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" width="50%">
                <tr>
                    <th>Forma</th>
                    <th>Dias para Entrega</th>
                    <th>Valor</th>
                </tr>
                <tr>
                    <td><input type="radio" name="frete" checked/> Rápida </td>
                    <td>6 Dias</td>
                    <td>Grátis</td>
                </tr>      
            </table><br>
            <input type="submit" name="operacao" value="CONTINUAR"/>
        </form>
    </div>
</div>
