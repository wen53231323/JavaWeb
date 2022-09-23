package Servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
@WebServlet(urlPatterns = {"/Servlet_9_获取初始化参数"},
        initParams = {
                @WebInitParam(name = "key_1", value = "value_1"),
                @WebInitParam(name = "key_2", value = "value_2")
        })
*/
public class Servlet_9_获取初始化参数 extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // 获取config对象
        ServletConfig config = getServletConfig();
        // 获取初始化参数值
        String key1 = config.getInitParameter("key_1");
        System.out.println(key1);// value_1

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 在初始化方法中，获取应用域servletContext对象
        ServletContext servletContext1 = getServletContext();
        // 获取初始化值
        String test1 = servletContext1.getInitParameter("test");
        System.out.println(test1);// oracle.jdbc.DriverManager

        // 在服务方法中通过request对象，获取应用域servletContext对象
        ServletContext servletContext2 = request.getServletContext();
        // 获取初始化值
        String test2 = servletContext2.getInitParameter("test");
        System.out.println(test2);// oracle.jdbc.DriverManager


        // 获取当前的session会话，没有则创建一个新的session会话
        HttpSession session = request.getSession();
        // 通过session，获取应用域servletContext对象
        ServletContext servletContext3 = session.getServletContext();
        // 获取初始化值
        String test3 = servletContext3.getInitParameter("test");
        System.out.println(test3);// oracle.jdbc.DriverManager

    }


}
