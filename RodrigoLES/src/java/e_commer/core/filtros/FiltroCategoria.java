package e_commer.core.filtros;

import e_commer.controle.web.command.impl.ConsultarCommand;
import e_commer.core.aplicacao.Resultado;
import e_commer.core.impl.dao.CategoriasDAO;
import e_commer.core.impl.dao.ConsultaDAO;
import e_commer.dominio.Categorias;
import e_commer.dominio.EntidadeDominio;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Henrique
 */
public class FiltroCategoria implements Filter {

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
        
        Categorias categoria = new Categorias();
        ConsultarCommand command = new ConsultarCommand();
        
        Resultado resultado = command.execute(categoria);
        
        //CategoriasDAO catDAO = new CategoriasDAO();
        List<EntidadeDominio> categorias = resultado.getEntidades();
        
        //categorias = catDAO.consultar(categoria);
        
        for(int i = 0;i< categorias.size();i++)
        {
            categoria = (Categorias)categorias.get(i);
            if(!categoria.getFlg_ativo()){
                categorias.remove(i);
                i--;
            }
        }
        
        resultado = new Resultado();
        resultado.setEntidades(categorias);
        request.setAttribute("categorias", resultado);
        chain.doFilter(request, response);
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {
    }

}
