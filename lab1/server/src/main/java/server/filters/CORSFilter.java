package server.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
                
        // if (!httpRequest.getHeader("Origin").equals("http://127.0.0.1:5500")) {
        //     chain.doFilter(request, response);
        //     return;
        // }
        
        // Разрешаем доступ с любого источника
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        
        // Разрешаем передачу cookies и заголовков авторизации
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Ничего не требуется
    }

    @Override
    public void destroy() {
        // Ничего не требуется
    }
}
