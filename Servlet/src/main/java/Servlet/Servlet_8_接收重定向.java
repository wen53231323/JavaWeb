package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//添加loadOnStartup属性并修改为0或者正整数，则会在服务器启动的时候调用init()方法
@WebServlet(urlPatterns = "/Servlet_8_jieshou", loadOnStartup = 1)
public class Servlet_8_接收重定向 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Response重定向重定向成功....");
    }
}
