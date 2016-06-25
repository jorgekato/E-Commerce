<%-- 
    Document   : register
    Created on : 27/03/2016, 23:34:29
    Author     : Henrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content">
    <div class="account-in register-top">
        <h2>Register</h2>
        <div class=" account-top register">
            ${mensagem}
            <form action="${pageContext.request.contextPath}/SalvarCliente" method="POST">
                <div> 	
                    <span>Name*</span>
                    <input type="text" name="txtNome"> 
                </div>
                <div> 	
                    <span>Email*</span>
                    <input type="text" name="txtEmail"> 
                </div>
                <div> 	
                    <span>Confirmar Email*</span>
                    <input type="text"> 
                </div>
                <div> 
                    <span  class="pass">Password*</span>
                    <input type="password" name="txtPassWord">
                </div>
                <div> 
                    <span  class="pass">Password*</span>
                    <input type="password" >
                </div>
                 <div> 	
                    <span>CPF*</span>
                    <input type="text" name="txtCpf"> 
                </div>
                <div> 	
                    <span>Sexo*</span>
                    <select name="txtSexo" >
                        <option value="" disabled="">Selecione..</option>
                        <option value="MASCULINO">Masculino</option>
                        <option value="FEMININO">Feminino</option>                        
                    </select>
                </div> 
                <div> 	
                    <span>Telefone*</span>
                    <input type="text" name="txtTelefone"> 
                </div>
                <div> 	
                    <span>Data Nascimento*</span>
                    <input type="text" name="txtDataNascimento" id="data"> 
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