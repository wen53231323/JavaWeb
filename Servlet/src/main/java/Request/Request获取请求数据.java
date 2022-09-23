package Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * request 获取请求数据
 */
@WebServlet("/request1")
public class Request获取请求数据 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // String getMethod()：获取请求方式（GET）
        String method = req.getMethod();
        System.out.println(method);//GET

        // String getContextPath()：获取虚拟目录(项目访问路径)
        String contextPath = req.getContextPath();
        System.out.println(contextPath);

        // StringBuffer getRequestURL(): 获取URL(统一资源定位符)
        StringBuffer url = req.getRequestURL();
        System.out.println(url.toString());

        // String getRequestURI()：获取URI(统一资源标识符)
        String uri = req.getRequestURI();
        System.out.println(uri);

        // String getQueryString()：获取请求参数（GET方式）
        String queryString = req.getQueryString();
        System.out.println(queryString);

        // 获取请求头数据
        // String getHeader(String name)：根据请求头名称获取对应值
        // user-agent：浏览器的版本信息
        String agent = req.getHeader("user-agent");
        System.out.println(agent);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取post 请求体：请求参数

        //1. 获取字符输入流
        BufferedReader br = req.getReader();
        //2. 读取数据
        String line = br.readLine();
        System.out.println(line);

    }
}
