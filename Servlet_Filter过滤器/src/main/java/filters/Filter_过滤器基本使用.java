package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// 请求参数message是否等于monster，等于则放行，不等于则将请求跳转到另外一个页面
@WebFilter(urlPatterns="*.test")
public class Filter_过滤器基本使用 implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 打印一句话表明Filter执行了
        System.out.println("过滤器执行--------------------------");

        // 获取请求参数
        String message = request.getParameter("message");

        // 检查是否满足过滤条件，请求参数是否等于monster
        if ("monster".equals(message)) {
            System.out.println("执行放行-------------------------");
            // 执行放行
            // FilterChain对象代表过滤器链，chain.doFilter(request, response)方法效果：
            // 将请求放行到下一个Filter，如果当前Filter已经是最后一个Filter了，那么就将请求放行到原本要访问的目标资源
            chain.doFilter(request, response);

        }else{
            System.out.println("没有放行-------------------------");
            // 跳转页面
            request.getRequestDispatcher("/过滤器.html").forward(request, response);

        }

    }

    @Override
    public void destroy() {

    }
}
