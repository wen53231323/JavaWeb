package Response;

import sun.misc.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 响应字节数据：设置字节数据的响应体
 */
@WebServlet("/response4")
public class Response响应字节数据 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 读取文件
        FileInputStream fis = new FileInputStream("E:\\图片、视频、音乐\\图片\\静态图片\\1.png");

        //2. 获取response字节输出流
        ServletOutputStream os = response.getOutputStream();

        //3. 完成流的复制（copy）
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = fis.read(buff)) != -1) {
            os.write(buff, 0, len);
        }
        //使用工具类，简化流的复制（copy）
        //IOUtils.copy(fis,os);

        //4.关闭流
        fis.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}