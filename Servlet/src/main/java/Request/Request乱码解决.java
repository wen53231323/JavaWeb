package Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/request3")
public class Request乱码解决 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决POST乱码：getReader()
        //设置字符输入流的编码，设置的字符集要和页面保持一致
        //request.setCharacterEncoding("UTF-8");//设置字符输入流的编码
        //获取username
        String username = request.getParameter("username");

        //System.out.println("解决post乱码：" + username);

        // 通用解决乱码方案
        // GET获取参数的方式：getQueryString
        // 乱码原因：tomcat进行URL解码，默认的字符集ISO-8859-1
        //1、先对乱码数据进行编码：转为字节数组
        //byte[] bytes = username.getBytes(StandardCharsets.ISO_8859_1);
        //2、字节数组解码
        //username = new String(bytes, StandardCharsets.UTF_8);
        //3、前两步骤合为一步：
        username = new String(username.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        System.out.println("解决get、post乱码：" + username);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}