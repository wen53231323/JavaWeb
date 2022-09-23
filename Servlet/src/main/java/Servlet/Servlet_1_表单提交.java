package Servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 继承HttpServlet
public class Servlet_1_表单提交 extends HttpServlet {
    // doPost处理post请求
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码（Tomcat8之后只有post需要设置编码，获取参数动作之前）
        request.setCharacterEncoding("UTF-8");
        // 获取提交的用户名
        String username = request.getParameter("username");
        // 获取提交的密码（字符串型）
        String passwordStr = request.getParameter("password");
        // 将字符串型的密码装换为int型
        Integer password = Integer.parseInt(passwordStr);

        // 打印查看
        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);

    }
}
