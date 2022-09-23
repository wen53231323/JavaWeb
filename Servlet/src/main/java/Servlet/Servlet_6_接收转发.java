package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//添加loadOnStartup属性并修改为0或者正整数，则会在服务器启动的时候调用init()方法
@WebServlet(urlPatterns = "/Servlet_6_接收转发", loadOnStartup = 1)
public class Servlet_6_接收转发 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求已经被转发...");
        //再次打印request
        System.out.println(request);

        // 根据key获取value值
        Object msg = request.getAttribute("key");
        System.out.println("获取转发的数据:"+msg);

    }
}
