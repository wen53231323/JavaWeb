package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/Servlet_2_乱码解决")
public class Servlet_2_乱码解决 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get方式目前不需要设置编码（基于tomcat8）
        //如果是get请求发送的中文数据，转码稍微有点麻烦（tomcat8之前）
        String username = request.getParameter("username");
        //1.将字符串打散成字节数组
        byte[] bytes = username.getBytes("ISO-8859-1");
        //2.将字节数组按照设定的编码重新组装成字符串
        username = new String(bytes,"UTF-8");

        // GET获取参数的方式：getQueryString
        // 乱码原因：tomcat进行URL解码，默认的字符集ISO-8859-1
        // 获取提交的username
        //String username = request.getParameter("username");
        //1、先对乱码数据进行编码：转为字节数组
        //byte[] bytes = username.getBytes("ISO-8859-1");
        //2、字节数组解码
        //username = new String(bytes, "utf-8");
        // 前两步骤合为一步：
        //username = new String(username.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println("解决get乱码：" + username);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置字符输入流的编码，设置的字符集要和页面保持一致
        request.setCharacterEncoding("UTF-8");
        //获取username
        String username = request.getParameter("username");
        System.out.println("解决post乱码：" + username);
    }

}
