package tema11;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "Filtro", urlPatterns = {"/*"})
public class Filtro implements Filter {

    public Filtro() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        StringBuilder buffer = new StringBuilder();
        for (Enumeration parametrosPeticion = request.getParameterNames();
                parametrosPeticion.hasMoreElements();) {
            String nombreParametro = (String) parametrosPeticion.nextElement();
            String valoresDeParametros[] = request.getParameterValues(nombreParametro);
            int numeroParametros = valoresDeParametros.length;
            buffer.append(nombreParametro);
            buffer.append(" = ");
            for (int i = 0; i < numeroParametros; i++) {
                buffer.append(valoresDeParametros[i]);
                buffer.append(" | ");
                if (i < numeroParametros - 1) {
                    buffer.append(",");
                }
            }
        }
        System.out.println("Recibida peticion Desde la IP: "
                + request.getRemoteAddr());
        if (!buffer.toString().equals("")) {
            System.out.println("\tla peticion tiene los parametros: " + buffer);
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
    }
}