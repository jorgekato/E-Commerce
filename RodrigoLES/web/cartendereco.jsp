<%-- 
    Document   : cartendereco
    Created on : 09/04/2016, 10:31:18
    Author     : Henrique
--%>

<%@page import="e_commer.core.util.ConverteDate"%>
<%@page import="e_commer.dominio.Artesanato"%>
<div class="content">
    <div class="check-out">


        <%      if (carrinho != null) {
        %>
        <TABLE class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" BORDER="5"    WIDTH="50%"   CELLPADDING="4" CELLSPACING="3">
            <TR>
                <TH COLSPAN="6"><BR>
                    <H3>Carrinho de compras</H3>
                </TH>
            </TR>

            <TR>
                <TH ALIGN='CENTER'><input type="checkbox"></input></TH>
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
            <TR>
                <TD COLSPAN="5">
                </TD>
                 <TD>
                     <input type="submit" name="operacao" value="COMPRAR" />
                </TD>
            </TR>
        </TABLE>
        <br />
        <br/>
        
    </div>
        

</div>
