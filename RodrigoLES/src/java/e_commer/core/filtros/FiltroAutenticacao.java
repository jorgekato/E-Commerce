/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.filtros;

import e_commer.core.aplicacao.Resultado;
import e_commer.dominio.Cliente;
import e_commer.dominio.EntidadeDominio;
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
    
    private static final boolean debug = false;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
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
        
        //Resultado clientes = (Resultado) httpServletRequest.getSession().getAttribute("usuario");

        Cliente usuario = (Cliente) httpServletRequest.getSession().getAttribute("usuario");

//        if (clientes != null) {
//            for (EntidadeDominio e : clientes.getEntidades()) {
//                usuario = (Cliente) e;
//            }
//        }
        
        
        
        //Cliente usuario = (Cliente) httpServletRequest.getSession().getAttribute("usuario");
        // Se o dado não tiver sido gravado na sessão ainda, o valor dele 
        // será null.
        if (usuario == null) {
            request.getRequestDispatcher("/account.jsp").forward(request, response);
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
