package Servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//添加loadOnStartup属性并修改为0或者正整数，则会在服务器启动的时候调用init()方法
@WebServlet(urlPatterns = "/Servlet_4_session会话", loadOnStartup = 1)
public class Servlet_4_session会话 extends HttpServlet {
    //service()方法：提供服务方法，每次Servlet被访问，都会调用该方法
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前的session会话，没有则创建一个新的session会话
        HttpSession session = request.getSession();
        System.out.println("获取到的session：" + session);
        System.out.println("获取到的session ID：" + session.getId());

        // 向当前session保存作用域保存一个数据
        session.setAttribute("username", "wen");

        // 从当前session保存作用域获取一个数据
        Object username = session.getAttribute("username");
        System.out.println("从session保存作用域获取数据："+username);

    }
}


