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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // ------------------------------准备工作------------------------------
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        // 加载session或创建session
        HttpSession session = request.getSession();

        // 调用工具类SqlSessionUtil中的静态方法静态方法getSqlSession()，获取MyBatis提供的操作数据库的会话对象SqlSession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        // 获取UserMapper的代理实现类对象（调用sql语句）
        FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);

        // ---------------------------------pageNo处理---------------------------------
        // 设置默认页码为1
        Integer pageNo = 1;
        // 从session中获取页面
        String pageNoStr = request.getParameter("pageNo");
        // 如果页面不为null，并且不为空，则获取页码数并类型转换。否则，pageNo默认就是1
        if (pageNoStr != null) {
            pageNo = Integer.parseInt(pageNoStr);
        }


        // ---------------------------------keyword处理---------------------------------

        // 从session中获取搜索内容
        Object keywordObj = session.getAttribute("keyword");
        // 强转为字符串
        String keyword = (String) keywordObj;
        // 如果不为null则转为字符，否则设置为空
        if (keyword == null) {
            keyword = "";
        }

        // ---------------------------------调用mybatis方法---------------------------------
        // 使用实现类对象，调用Mapper接口的方法
        // 总记录条数
        int fruitCount = mapper.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount + 5 - 1) / 5;

        // 获取数据库中的字段信息
        // 模糊查询与名字包含相似或内容相似的数据
        // 且分页查询，每页显示五条数据
        List<Fruit> fruitList = mapper.getFruitList(keyword, pageNo);

        // ---------------------------------添加session域-------------------------------
        // 将页码数添加到session域中
        session.setAttribute("pageNo", pageNo);
        // 将总页码数添加进session域中
        session.setAttribute("pageCount", pageCount);
        // 将数据库中的查询的字段保存到session作用域
        session.setAttribute("fruitList", fruitList);


        // 此处的视图名称是 index，thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        // 所以真实的视图名称是：      /       index       .html
        super.processTemplate("fruit_all", request, response);
    }
}
