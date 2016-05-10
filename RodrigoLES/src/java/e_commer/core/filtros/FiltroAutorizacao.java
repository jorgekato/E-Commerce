package e_commer.core.filtros;

import e_commer.dominio.Cliente;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Fatec
 */
public class FiltroAutorizacao implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cliente usuario = (Cliente) httpServletRequest.getSession().getAttribute("usuario");
        // Se o dado não tiver sido gravado na sessão ainda, o valor dele 
        // será null.
        if(usuario != null)
        {
            if (usuario.getNivel() != Cliente.Nivel.ADMINISTRADOR && usuario.getNivel() != Cliente.Nivel.COLABORADOR) {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        }else
        {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
