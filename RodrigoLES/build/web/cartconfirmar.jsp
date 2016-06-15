<%-- 
    Document   : cartconfirmar
    Created on : 11/04/2016, 13:40:12
    Author     : Henrique
--%>

<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<div class="content">
    <div class="check-out">


        <%      if (carrinho != null) {
        %>
        <TABLE BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
            <TR>
                <TH COLSPAN="6"><BR>
                    <H3>Itens</H3>
                </TH>
            </TR>

            <TR>
                <TH ALIGN='CENTER'><input type="checkbox"/></TH>
                <TH>Nome</TH>
                <TH>Quantidade</TH>                
                <TH>Status:</TH>
                <TH>UpDate:</TH>
                <TH>Excluir todos:</TH>
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
                                sbRegistro.append("<input type=\"checkbox\"></input>");
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                sbRegistro.append(item.getArtesanato().getNome());
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("<TD>");
                                //sbRegistro.append(sbLink.toString());
                                sbRegistro.append("<input type=\"number\" name=\"quantidade\" id=\"idade\" min=\"1\" max=\"120\" value=\"");
                                sbRegistro.append(item.getQuantidade());
                                sbRegistro.append("\" />");
                                //sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                sbRegistro.append(item.getArtesanato().getPrecoUnit() * item.getQuantidade());
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                upDate.append("<a href=SalvarCarrinho?");
                                upDate.append("idItem=");
                                upDate.append(i);
                                upDate.append("&");
                                upDate.append("qtde=");
                                upDate.append(4); //ver como colocar o valor do campo qtde aqui
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
                                sbRegistro.append("<input type=\"checkbox\"></input>");
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                sbRegistro.append(item.getProduto().getNome());
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("<TD>");
                                //sbRegistro.append(sbLink.toString());
                                sbRegistro.append("<input type=\"number\" name=\"qtde\" id=\"idade\" min=\"1\" max=\"120\" value=\"");
                                sbRegistro.append(item.getQuantidade());
                                sbRegistro.append("\" />");
                                //sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                sbRegistro.append("<TD>");
                                sbRegistro.append(sbLink.toString());
                                sbRegistro.append(item.getProduto().getPrecoUnit() * item.getQuantidade());
                                sbRegistro.append("</a>");
                                sbRegistro.append("</TD>");

                                upDate.append("<a href=SalvarCarrinho?");
                                upDate.append("idItem=");
                                upDate.append(i);
                                upDate.append("&");
                                upDate.append("qtde=");
                                upDate.append("5"); //ver como colocar o valor do campo qtde aqui
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
                            }

                        }
                    }

                }
            %>



        </TABLE>
        <br />
        <br/>



        <TABLE BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
            <TR>
                <TH ><BR>
                    <!--<TH COLSPAN="2"><BR>-->
                    <H3>Endereço de entrega</h3>
                </TH>
            </TR>


            <%
                if (cliente != null) {
                    StringBuilder sbCliente = new StringBuilder();
                    sbCliente.setLength(0);

                    sbCliente.append("<TR ALIGN='CENTER'>");
                    sbCliente.append("<TD>");
                    sbCliente.append("Rua: ");
                    sbCliente.append(cliente.getEndereco().getLogradouro());
                    sbCliente.append("</TD>");
                    sbCliente.append("</TR>");

                    sbCliente.append("<TR ALIGN='CENTER'>");
                    sbCliente.append("<TD>");
                    sbCliente.append("Numero ");
                    sbCliente.append(cliente.getEndereco().getNumero());
                    sbCliente.append("</TD>");
                    sbCliente.append("</TR>");

                    sbCliente.append("<TR ALIGN='CENTER'>");
                    sbCliente.append("<TD>");
                    sbCliente.append("Bairro: ");
                    sbCliente.append(cliente.getEndereco().getBairro());
                    sbCliente.append("</TD>");
                    sbCliente.append("</TR>");

                    sbCliente.append("<TR ALIGN='CENTER'>");
                    sbCliente.append("<TD>");
                    sbCliente.append("Cidade: ");
                    sbCliente.append(cliente.getEndereco().getCidade().getNome());
                    sbCliente.append("</TD>");
                    sbCliente.append("</TR>");

                    sbCliente.append("<TR ALIGN='CENTER'>");
                    sbCliente.append("<TD>");
                    sbCliente.append("Rua: ");
                    sbCliente.append(cliente.getEndereco().getCidade().getEstado().getNome());
                    sbCliente.append("</TD>");
                    sbCliente.append("</TR>");

                    sbCliente.append("<TR ALIGN='CENTER'>");
                    sbCliente.append("<TD>");
                    sbCliente.append("CEP: ");
                    sbCliente.append(cliente.getEndereco().getCep());
                    sbCliente.append("</TD>");
                    sbCliente.append("</TR>");

                    out.print(sbCliente.toString());
                }

            %>
            <a href="novoEndereco.jsp"><font color="red"><b>Alterar Endereço</b></font></a>


        </TABLE>

        <a href="cartpayment.jsp"><input type="button" name="operacao" value="CONFIRMAR"/></a>

    </div>


</div>
