/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.filtros;

import e_commer.controle.web.command.impl.ConsultarCommand;
import e_commer.controle.web.vh.impl.PedidoViewHelper;
import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.dao.ProdutoDAO;
import e_commer.dominio.CarrinhoCompra;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Pedido;
import e_commer.dominio.Produto;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
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
public class FiltroQtdeEstoqueCompra implements Filter {

    public FiltroQtdeEstoqueCompra() {
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

        
        HttpServletRequest request1 = (HttpServletRequest)request;   
        Resultado r1 = (Resultado)request1.getSession().getAttribute("carrinho");
        CarrinhoCompra cart = (CarrinhoCompra)r1.getEntidades().get(0);
        //CarrinhoCompra cart = (CarrinhoCompra) request1.getSession().getAttribute("carrinho");
        ConsultarCommand command = new ConsultarCommand();

        Resultado resultado = command.execute(cart);

        if (resultado.getMsg() != null) {
            Resultado r = (Resultado)request1.getSession().getAttribute("carrinho");
            r.setMsg(resultado.getMsg());
            request.setAttribute("carrinho", r);
            request.getRequestDispatcher("cart.jsp").forward(request, response);

        } else {
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
