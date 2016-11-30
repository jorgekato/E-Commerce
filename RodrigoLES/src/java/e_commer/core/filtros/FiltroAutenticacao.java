/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.filtros;

import e_commer.controle.web.command.ICommand;
import e_commer.controle.web.command.impl.ConsultarCommand;
import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.CarrinhoCompra;
import e_commer.dominio.Cliente;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Login;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Henrique
 */
public class FiltroAutenticacao implements Filter {

    public FiltroAutenticacao() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String operacao = request.getParameter("operacao");
        Cliente cli = null;
        //Login log = null;
        Resultado resultado = null;

        ICommand command = null;
        if (operacao != null) {

            if (operacao.equals("LOGAR")) {

                cli = new Cliente();
                command = new ConsultarCommand();

                String txtEmail = httpServletRequest.getParameter("txtEmail");
                String password = httpServletRequest.getParameter("txtPassWord");

                if (txtEmail != null && !txtEmail.trim().equals("")) {
                    cli.setEmail(txtEmail);
                }

                resultado = command.execute(cli);

                if (resultado.getEntidades() != null && resultado.getEntidades().size() > 0) {
                    for (EntidadeDominio e : resultado.getEntidades()) {

                        cli = (Cliente) e;
                    }

                    if (cli.getLogin().getPassword().equals(password)) {

                        Login log = new Login();
                        log.setPassword("");
                        cli.setLogin(log);
                        httpServletRequest.getSession().setAttribute("usuario", cli);

                        if (cli.getNivel() != Cliente.Nivel.ADMINISTRADOR && cli.getNivel() != Cliente.Nivel.COLABORADOR) {

                            Resultado carrinho = (Resultado) httpServletRequest.getSession().getAttribute("carrinho");

                            CarrinhoCompra carrinhos = null;

                            if (carrinho != null) {
                                for (EntidadeDominio e : carrinho.getEntidades()) {
                                    carrinhos = (CarrinhoCompra) e;
                                }
                            }
                            if (carrinhos != null) {
                                httpServletRequest.getRequestDispatcher("cart.jsp").forward(request, response);
                            } else {
                                httpServletRequest.getRequestDispatcher("index.jsp").forward(request, response);
                            }
                        } else {
                            httpServletRequest.getRequestDispatcher("LadoAdmin/adminprinc.jsp").forward(request, response);
                        }
                    } else {
                        resultado.setMsg("Usuário e/ou Senha inválidos!");
                        httpServletRequest.setAttribute("resultado", resultado);
                        httpServletRequest.getRequestDispatcher("account.jsp").forward(request, response);
                    }
                } else {
                    resultado.setMsg("Usuário e/ou Senha inválidos!");
                    httpServletRequest.setAttribute("resultado", resultado);
                    httpServletRequest.getRequestDispatcher("account.jsp").forward(request, response);
                }

            } else if (operacao.equals("SAIR")) {
                httpServletRequest.getSession().invalidate();
                httpServletRequest.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

    }

}
