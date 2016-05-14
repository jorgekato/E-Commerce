package e_commer.controle.web.vh.impl;

import e_commer.controle.web.vh.IViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.Cliente;
import e_commer.dominio.Credito;
import e_commer.dominio.EntidadeDominio;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jorge
 */
public class CreditoViewHelper implements IViewHelper {

    private final String meusvalecreditos = "meusvalecreditos.jsp";
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");
        Credito cred = null;
        if(operacao.equals("CONSULTAR1")){
            cred = new Credito();
            Cliente cli = new Cliente();
            cli.setId(Integer.valueOf(request.getParameter("txtCliId")));
            cred.setCliente(cli);
        }
        return cred;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher d = null;
        String operacao = request.getParameter("operacao");
        if (resultado.getMsg() == null && operacao.equals("ENVIAR")) {
            request.setAttribute("mensagem", "Seu pedido foi enviado!\nLogo estaremos entrando em contato!");
            d = request.getRequestDispatcher("minhaconta.jsp");
        } else if (resultado.getMsg() == null && operacao.equals("CONSULTAR1")) {
            request.setAttribute("credito", resultado);
            d = request.getRequestDispatcher(meusvalecreditos);
        }
        
        d.forward(request, response);
    }
    
}
