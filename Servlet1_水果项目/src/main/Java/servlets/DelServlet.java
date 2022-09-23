package servlets;


import Util.SqlSessionUtil;
import Util.ViewBaseServlet;
import mapper.FruitMapper;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del")
public class DelServlet extends ViewBaseServlet {

    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        // 获取前端传递的id
        String idStr = request.getParameter("id");
        // 调用工具包中的isNotEmpty，判断字符串是否不为null且不为空
        if(idStr!=null && !"".equals(idStr)){
            // 将字符串转换为数值型
            int id = Integer.parseInt(idStr);

            // 执行删除
            // 调用工具类SqlSessionUtil中的静态方法静态方法getSqlSession()，获取MyBatis提供的操作数据库的会话对象SqlSession
            SqlSession sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper的代理实现类对象（调用sql语句）
            FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
            mapper.delFruit(id);

            // 重定向到首页，会重新获取furitList，然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
            // 不使用跳转，资源跳转需要手动重新刷新页面才会更新数据
            response.sendRedirect("index");
        }
    }
}
