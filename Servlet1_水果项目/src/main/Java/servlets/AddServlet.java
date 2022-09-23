package servlets;

import Util.SqlSessionUtil;
import mapper.FruitMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Fruit;
import Util.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        // 获取前端传递的表单内容
        String name = request.getParameter("name");
        Integer price = Integer.parseInt(request.getParameter("price")) ;
        Integer count = Integer.parseInt(request.getParameter("count"));
        String remark = request.getParameter("remark");

        // 创建对象添加数据
        Fruit fruit = new Fruit(null,name , price , count , remark ) ;
        // 调用工具类SqlSessionUtil中的静态方法静态方法getSqlSession()，获取MyBatis提供的操作数据库的会话对象SqlSession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 获取UserMapper的代理实现类对象（调用sql语句）
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
        // 将数据添加进入表中
        mapper.addFruit(fruit);

        // 不使用跳转，资源跳转需要手动重新刷新页面才会更新数据
        // super.processTemplate("index",request,response);
        // request.getRequestDispatcher("index.html").forward(request,response);
        // 重定向到首页，会重新获取furitList，然后覆盖到session中，
        // 这样index.html页面上显示的session中的数据才是最新的
        response.sendRedirect("index");

    }
}
