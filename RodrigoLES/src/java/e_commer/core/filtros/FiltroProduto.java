/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e_commer.core.filtros;

import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.dao.ProdutoDAO;
import e_commer.dominio.Produto;
import e_commer.dominio.EntidadeDominio;
import java.io.IOException;
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
public class FiltroProduto implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public FiltroProduto() {
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
        
        Produto produto = new Produto();
        ProdutoDAO proDAO = new ProdutoDAO();
        List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();
        
        produtos = proDAO.consultar(produto);
        
        for(int i = 0;i< produtos.size();i++)
        {
            produto = (Produto)produtos.get(i);            
        }
        
        Resultado resultado = new Resultado();
        resultado.setEntidades(produtos);
        request.setAttribute("produtos", resultado);
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
