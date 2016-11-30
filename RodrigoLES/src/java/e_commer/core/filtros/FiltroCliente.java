/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.filtros;

import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.dao.ClienteDAO;
import e_commer.dominio.EntidadeDominio;
import e_commer.dominio.Cliente;
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

/**
 *
 * @author Henrique
 */
public class FiltroCliente implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public FiltroCliente() {
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
        
        Cliente cliente = new Cliente();
        ClienteDAO cliDAO = new ClienteDAO();
        List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
        
        clientes = cliDAO.consultar(cliente);
        
        for(int i = 0;i< clientes.size();i++)
        {
            cliente = (Cliente)clientes.get(i);            
        }
        
        Resultado resultado = new Resultado();
        resultado.setEntidades(clientes);
        request.setAttribute("clientes", resultado);
        chain.doFilter(request, response);
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
