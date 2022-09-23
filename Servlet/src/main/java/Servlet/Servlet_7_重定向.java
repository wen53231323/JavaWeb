package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//添加loadOnStartup属性并修改为0或者正整数，则会在服务器启动的时候调用init()方法
@WebServlet(urlPatterns = "/Servlet_7_重定向", loadOnStartup = 1)
public class Servlet_7_重定向 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("设置重定向....");

        // 动态获取虚拟目录（项目目录）
        String contextPath = request.getContextPath();
        // 设置重定向
        response.sendRedirect(contextPath+"/Servlet_8_jieshou");

        // 重定向到 远方の博客
        // response.sendRedirect("https://wen53231323.github.io/");

    }
}
