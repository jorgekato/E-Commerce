<%-- 
    Document   : account
    Created on : 07/04/2016, 10:39:00
    Author     : Henrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="content">
    <div class="account-in">
        <h2>Identificação</h2>
        <div class="col-md-7 account-top">
            <form action="${pageContext.request.contextPath}/do.logar" method="POST" id="validate">

                <p><%                    if (resultado != null && resultado.getMsg() != null) {
                        out.print(resultado.getMsg());
                    }
                    %></p>
                <div> 	
                    <span>Email*</span>
                    <input type="text" name="txtEmail"> 
                </div>
                <div> 
                    <span  class="pass">Password*</span>
                    <input type="password" name="txtPassWord">
                </div>				
                <input type="submit"  name='operacao' value='LOGAR'> 
            </form>
        </div>
        <div class="col-md-5 left-account ">            
            <a href="register.jsp" class="create">Cadastrar</a>
            <div class="clearfix"> </div>
        </div>
        <div class="clearfix"> </div>
    </div>	


</div>
