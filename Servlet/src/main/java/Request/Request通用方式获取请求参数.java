package Request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

/**
 * request 通用方式获取请求参数
 */
@WebServlet("/request2")
public class Request通用方式获取请求参数 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //GET请求逻辑
        System.out.println("get....");

        //1. 获取所有参数的Map集合
        Map<String, String[]> map = req.getParameterMap();
        //遍历map集合
        for (String key : map.keySet()) {
            // username:zhangsan lisi
            System.out.print(key + ":");

            //获取值
            String[] values = map.get(key);
            //遍历map集合
            for (String value : values) {
                System.out.print(value + " ");
            }

            System.out.println();
        }

        System.out.println("-----------------");

        //2. 根据key获取参数值，数组
        String[] hobbies = req.getParameterValues("hobby");
        ////遍历map集合
        for (String hobby : hobbies) {

            System.out.println(hobby);
        }

        //3. 根据key 获取单个参数值
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("username：" + username);
        System.out.println("password：" + password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("post....");

        this.doGet(req, resp);

        /*System.out.println("post....");

        //1. 获取所有参数的Map集合
        Map<String, String[]> map = req.getParameterMap();
        for (String key : map.keySet()) {
            // username:zhangsan lisi
            System.out.print(key+":");

            //获取值
            String[] values = map.get(key);
            for (String value : values) {
                System.out.print(value + " ");
            }

            System.out.println();
        }

        System.out.println("------------");

        //2. 根据key获取参数值，数组
        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies) {

            System.out.println(hobby);
        }

        //3. 根据key 获取单个参数值
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username);
        System.out.println(password);*/

    }
}
