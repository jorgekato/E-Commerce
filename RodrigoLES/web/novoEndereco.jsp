<%-- 
    Document   : novoEndereco
    Created on : 26/05/2016, 10:58:47
    Author     : Jorge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content">
    <div class="account-in register-top">
        <h2>Meus Endereços</h2>
        <div class=" account-top register">
            <form action="${pageContext.request.contextPath}/SalvarCliente" method="POST">
                <div> 	
                    <span>Name*</span>
                    <input type="text" name="txtNome"> 
                </div>
                 <div> 	
                    <span>Telefone*</span>
                    <input type="text" name="txtTelefone"> 
                </div>
                <div> 	
                    <span>Endereço*</span>
                    <input type="text" name="txtEndereco"> 
                </div>
                <div> 	
                    <span>Numero*</span>
                    <input type="text" name="txtEnderecoNumero"> 
                </div>
                <div> 	
                    <span>CEP*</span>
                    <input type="text" name="txtEnderecoCEP"> 
                </div>
                <div> 	
                    <span>Complemento*</span>
                    <input type="text" name="txtEnderecoComplemento"> 
                </div>
                <div> 	
                    <span>Bairro*</span>
                    <input type="text" name="txtEnderecoBairro"> 
                </div>
                <div> 	
                    <span>Cidade*</span>
                    <input type="text" name="txtEnderecoCidade"> 
                </div>   
                <div> 	
                    <span>Estado*</span>
                    <input type="text" name="txtEnderecoEstado"> 
                </div> 
               
                <input type="hidden" name="txtFlgAtivo" value="TRUE" />
                <input type="hidden" name="txtNivel" value="CLIENTE" />
                <input type="submit" name="operacao" value="SALVAR"> 
            </form>
        </div>

    </div>	


</div>
