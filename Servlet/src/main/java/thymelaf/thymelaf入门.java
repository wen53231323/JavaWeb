package thymelaf;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//添加loadOnStartup属性并修改为0或者正整数，则会在服务器启动的时候调用init()方法
@WebServlet(urlPatterns = "/Test_Servlet_thymelaf", loadOnStartup = 1)
public class thymelaf入门 extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 存储数据到request域中（范围,数据是存储在request对象）
        request.setAttribute("src/main/Java/test", "<span>hello thymelaf</span>");

        //①操作请求域--------------------------------------------------
        String key_1 = "key_1";
        String value_1 = "value_1";
        // 存储数据到request请求域中（范围,数据是存储在request对象）
        request.setAttribute(key_1, value_1);

        //②操作会话域-----------------------------------------------------
        // 通过request对象获取session对象
        HttpSession session = request.getSession();
        // 存储数据到session会话域中
        session.setAttribute("key_2", "value_2");

        //③操作应用域--------------------------------------------------------
        // 通过调用父类的方法获取ServletContext对象
        ServletContext servletContext = request.getServletContext();
        // 存储数据到ServletContext域中
        servletContext.setAttribute("key_3", "value_3");

        // 根据逻辑视图名称 得到 物理视图名称
        super.processTemplate("Test_Servlet_thymelaf", request, response);
    }
}
