package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//添加loadOnStartup属性并修改为0或者正整数，则会在服务器启动的时候调用init()方法
@WebServlet(urlPatterns = "/Servlet_5_请求转发", loadOnStartup = 1)
public class Servlet_5_请求转发 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("获取请求...");
        // 打印request
        System.out.println(request);
        // 存储数据到request域中（范围,数据是存储在request对象）
        request.setAttribute("key","hello world");
        // 将请求转发到指定路径
        request.getRequestDispatcher("/Servlet_6_接收转发").forward(request,response);

    }
}
