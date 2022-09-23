package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// 指定编码
// 方式一：通过注解指定encoding = "UTF-8"
// 方式二：通过xml配置文件指定encoding = "UTF-8"
// 方式三：通过指定默认值encoding = "UTF-8"
// 设置编码
// ((HttpServletRequest) servletRequest).setCharacterEncoding(encoding);

@WebFilter(urlPatterns = {"*.do"},initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
public class Filter_利用过滤器设置编码 implements Filter {
    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingStr = filterConfig.getInitParameter("encoding");
        if (encodingStr != null) {
            encoding = encodingStr;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 强转后设置编码为utf-8
        ((HttpServletRequest) servletRequest).setCharacterEncoding(encoding);
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
