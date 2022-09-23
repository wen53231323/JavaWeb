package servlets;

import Util.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/search")
public class searchServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        // 加载session或创建session
        HttpSession session = request.getSession();

        // 获取表单提交的内容
        String keyword = request.getParameter("keyword");

        // 如果keyword为null，需要设置为空字符串""，否则查询时会拼接成 %null% , 我们期望的是 %%
        if (keyword == null || keyword.length() == 0) {
            keyword = "";
        }

        // 将keyword保存（覆盖）到session中
        session.setAttribute("keyword", keyword);

        // 重定向到首页
        response.sendRedirect("index");
    }
}
