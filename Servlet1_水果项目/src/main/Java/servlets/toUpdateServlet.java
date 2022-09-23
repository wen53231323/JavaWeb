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

@WebServlet("/toupdate")
public class toUpdateServlet extends ViewBaseServlet {

    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        // 调用工具类SqlSessionUtil中的静态方法静态方法getSqlSession()，获取MyBatis提供的操作数据库的会话对象SqlSession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 获取UserMapper的代理实现类对象（调用sql语句）
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);

        // 获取前端传递的id
        String idStr = request.getParameter("id");

        // 如果 字符串不为null 并且 不等于空字符串（可以封装到工具类中）
        if (idStr!=null && !"".equals(idStr)){
            // 将字符型转为数值型
            int id = Integer.parseInt(idStr);

            // 根据fid获取特定的水果库存信息
            Fruit fruitbyid =  mapper.getFruitById(id);

            // 将数据放在request域中
            request.setAttribute("fruitbyid",fruitbyid);

            // 展现fruit_update.html
            super.processTemplate("fruit_update", request, response);


        }



    }
}
