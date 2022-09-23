package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter("*.do")
public class Filter_2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤器2放行前-------");
        //放行
        chain.doFilter(request,response);
        System.out.println("过滤器2放行后-------");
    }

    @Override
    public void destroy() {

    }
}
