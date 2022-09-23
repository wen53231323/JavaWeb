package Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应字符数据：设置字符数据的响应体
 */
@WebServlet("/response3")
public class Response响应字符数据 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的数据格式及数据的编码（设置后可以不设置content-type）
        response.setContentType("text/html;charset=utf-8");
        //获取字符输出流
        PrintWriter writer = response.getWriter();
        //设置content-type，告诉浏览器返回的数据类型是HTML类型数据，这样浏览器才会解析HTML标签
        //response.setHeader("content-type","text/html");

        //通过字符输出流写数据
        writer.write("你好，response");
        writer.write("<h1>你好，response</h1>");
        //细节：流不需要关闭
        //一次请求响应结束后，response对象就会被销毁掉，所以不要手动关闭流。

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}