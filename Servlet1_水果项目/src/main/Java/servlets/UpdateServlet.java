package servlets;


import Util.SqlSessionUtil;
import Util.ViewBaseServlet;
import mapper.FruitMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends ViewBaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取参数
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String countStr = request.getParameter("count");
        String remark = request.getParameter("remark");

        // 将字符型转为数值型
        Integer id = Integer.parseInt(idStr);
        int price = Integer.parseInt(priceStr);
        Integer count = Integer.parseInt(countStr);


        // 执行更新
        // 调用工具类SqlSessionUtil中的静态方法静态方法getSqlSession()，获取MyBatis提供的操作数据库的会话对象SqlSession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 获取UserMapper的代理实现类对象（调用sql语句）
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        Fruit fruit = new Fruit(id, name, price, count, remark);
        mapper.updateFruit(fruit);

        // 不使用跳转，资源跳转需要手动重新刷新页面才会更新数据
        // super.processTemplate("index",request,response);
        // request.getRequestDispatcher("index.html").forward(request,response);

        // 此处需要重定向，重新给IndexServlet发请求，重新获取furitList，然后覆盖到session中，
        // 这样index.html页面上显示的session中的数据才是最新的
        response.sendRedirect("index");
    }
}

// java.lang.NumberFormatException: For input string: ""