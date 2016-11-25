<%-- 
    Document   : meusdados
    Created on : 09/04/2016, 20:35:01
    Author     : Henrique
--%>

<%@page import="e_commer.dominio.Endereco"%>
<%@page import="e_commer.core.util.ConverteDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content">
    <div class="col-md-3 col-md">
        <div class=" possible-about">
            <h4>Sort Products</h4>
            <div class="tab1">
                <ul class="place">
                    <li class="sort">
                        <a href="SalvarPedidos?txtCliId=<%= cliente.getId()%>&operacao=CONSULTAR1">Meus Pedidos</a></li>
                    <li class="by"></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>
            <div class="tab2">
                <ul class="place">
                    <li class="sort">
                        <a href="SalvarEndereco?txtId=<%= cliente.getId()%>&operacao=VISUALIZAR3">Meus Dados</a></li>
                    <li class="by"></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>
            <div class="tab3">
                <ul class="place">
                    <li class="sort">                        
                        <a href="SalvarTrocaDevolucao?txtCliId=<%= cliente.getId()%>&operacao=CONSULTAR1">Minhas Trocas e Cancelamentos</a></li>

                    <li class="by"></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>
            <div class="tab4">
                <ul class="place">
                    <li class="sort">
                        <a href="SalvarCredito?txtCliId=<%= cliente.getId()%>&operacao=CONSULTAR1">Meus Vale-Creditos</a></li>
                    <li class="by"></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>        
            <div class="tab5">

            </div>

            <!--script-->
            <script>
                $(document).ready(function () {
                    $(".tab1 .single-bottom").hide();
                    $(".tab2 .single-bottom").hide();
                    $(".tab3 .w_nav2").hide();
                    $(".tab4 .single-bottom").hide();
                    $(".tab5 .star-at").hide();
                    $(".tab1 ul").click(function () {
                        $(".tab1 .single-bottom").slideToggle(300);
                        $(".tab2 .single-bottom").hide();
                        $(".tab3 .w_nav2").hide();
                        $(".tab4 .single-bottom").hide();
                        $(".tab5 .star-at").hide();
                    })
                    $(".tab2 ul").click(function () {
                        $(".tab2 .single-bottom").slideToggle(300);
                        $(".tab1 .single-bottom").hide();
                        $(".tab3 .w_nav2").hide();
                        $(".tab4 .single-bottom").hide();
                        $(".tab5 .star-at").hide();
                    })
                    $(".tab3 ul").click(function () {
                        $(".tab3 .w_nav2").slideToggle(300);
                        $(".tab4 .single-bottom").hide();
                        $(".tab5 .star-at").hide();
                        $(".tab2 .single-bottom").hide();
                        $(".tab1 .single-bottom").hide();
                    })
                    $(".tab4 ul").click(function () {
                        $(".tab4 .single-bottom").slideToggle(300);
                        $(".tab5 .star-at").hide();
                        $(".tab3 .w_nav2").hide();
                        $(".tab2 .single-bottom").hide();
                        $(".tab1 .single-bottom").hide();
                    })
                    $(".tab5 ul").click(function () {
                        $(".tab5 .star-at").slideToggle(300);
                        $(".tab4 .single-bottom").hide();
                        $(".tab3 .w_nav2").hide();
                        $(".tab2 .single-bottom").hide();
                        $(".tab1 .single-bottom").hide();
                    })
                });
            </script>
            <!-- script -->
        </div>
        <div class="content-bottom-grid">

        </div>
        <!---->
        <div class="money">

        </div>
    </div>
    <div class="col-md-9">
        <div class="shoe">


        </div>
        <div class="content-bottom">
            <h3>Meus Dados</h3>
            <%
                Resultado clientes = (Resultado) request.getAttribute("clientes");

            %>    
            ${mensagem}
            <form action="SalvarCliente" method="post">
            <!-- dados obtidos atraves da sessao   -->
            <p><label for="nome">Nome.:</label>
                <input type="text" id="nome" name="txtNome" value="<%= cliente.getNome()%>" size="50" readonly/></p>
            <p><label for="nome">* Email.:</label>
                <input type="text" id="email" name="txtEmail" value="<%= cliente.getEmail()%>" size="30"/></p>
            <p><label for="telefone">* Telefone.:</label>
                <input type="text" id="telefon" name="txtTelefone" value="<%= cliente.getTelefone()%>"/></p>
            <p><label for="nome">CPF.:</label>
                <input type="text" id="cpf" name="txtCpf" value="<%= cliente.getCpf()%>" readonly/></p>
            <p><label for="nome">Data de Nascimento.:</label>
                <input type="text" id="dtNasc" name="txtDataNascimento" value="<%= ConverteDate.converteDateString(cliente.getDtNascimento())%>" readonly/></p>
            <p><label for="sexo">Sexo.:</label>
                <input type="text" id="sexo" name="txtSexo" value="<%= cliente.getSexo()%>" readonly/></p>
            <p>* Campos editáveis</p>
            <input type="submit" name="operacao" value="ALTERAR1"/>
            <h3>Meus Endereços</h3>
            <table class="table table-striped table-bordered bootstrap-datatable datatable table-responsive" border="3"  width="80%">
                
                <%
                Cliente cli = (Cliente) clientes.getEntidades().get(0);
                    for (int i = 0; i < cli.getEndereco().size(); i++) {
                        Endereco end = (Endereco) cli.getEndereco().get(i);
                %>
                <tr>
                    <td>
                        <div>
                            <p><label for="logradouro"><%= end.getLogradouro()%> </label>
                            <label for="numero"><%= end.getNumero()%></label></p>
                            <%if (end.getComplemento() != null) {%>
                        <p><label for="complemento"><%= end.getComplemento()%></label></p>
                            <%}%>
                        <p><label for="bairro"><%= end.getBairro()%></label></p>
                        <p><label for="cidade"><%= end.getCidade().getNome()%>/ </label>
                            <label for="estado"><%= end.getCidade().getEstado().getNome()%></label></p>
                        <p><label for="cep"><%= end.getCep()%></label></p>
                        </div>
                    </td>
                    <td>
                        <div>
                            <%if (i != 0){%>
                            <a href="SalvarEndereco?txtId=<%= cliente.getId()%>&endId=<%= end.getId()%>&situacao=false&operacao=ALTERAR">Excluir</a>
                            <%}%>
                        </div>
                    </td>
                </tr>
                
                <%
                    }
                %>
            </table>
            <input type="hidden" id="idCli" name="txtId" value="<%= cliente.getId()%>" />
            <input type="hidden" id="flgAtivo" name="txtFlgAtivo" value="TRUE"/>
            <a href="cadendereco.jsp"><font color="red">Novo Endereço</font></a><br><br><br>
            
        </form>
        </div>

    </div>


    <div class="clearfix"> </div>
</div>