package ajax使用案例.Servlet;

import ajax使用案例.pojo.User;
import ajax使用案例.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class login  extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //（1）获取提交的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 获取复选框数据
        String remember = request.getParameter("remember");

        //（2）根据用户名和密码，调用service查询
        List<User> user = service.login(username, password);

        //（3）判断，如果用户存在，则重定向到 selectAllServlet路由，不存在则返回提示信息
        if (user != null && !user.isEmpty()) {
            //登录成功，跳转到查询所有的BrandServlet

            //判断用户是否勾选记住我
            if ("1".equals(remember)) {
                //勾选了，发送Cookie

                //1. 创建Cookie对象
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                // 设置Cookie的存活时间
                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);
                //2. 发送
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            //将登陆成功后的user对象，存储到session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            //动态获取虚拟目录（项目根路径）
            String contextPath = request.getContextPath();
            // 设置重定向
            response.sendRedirect(contextPath + "/index");
        } else {
            // 登录失败

            // 存储错误信息到request
            request.setAttribute("login_msg", "用户名或密码错误");

            // 请求转发
            request.getRequestDispatcher("index.html").forward(request,response);
        }
    }
}
