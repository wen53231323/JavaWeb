package servlets;


import Util.SqlSessionUtil;
import Util.StringUtil;
import Util.ViewBaseServlet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.FruitMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {
    // 调用工具类SqlSessionUtil中的静态方法静态方法getSqlSession()，获取MyBatis提供的操作数据库的会话对象SqlSession
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    // 获取UserMapper的代理实现类对象（调用sql语句）
    FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

// ======================方式一：根据获取的值不同，调用不同的方法========================
//        缺点：项目的业务规模扩大后，意味着有很多Servlet，会造成switch - case代码冗余
        // 获取前端传递的operate
//        String operate = request.getParameter("operate");
//        switch (operate) {
//            case "index":
//                index(request, response);
//                break;
//            case "add":
//                add(request, response);
//                break;
//            case "update":
//                update(request, response);
//                break;
//
//            case "edit":
//                edit(request, response);
//
//                break;
//            case "del":
//                del(request, response);
//                break;
//        }


        // ========================方式二：反射========================
        // 使用反射技术，规定前端请求携带operate参数，参数值和方法名一致，
        // 根据接收到operate的值，调用对应的方法进行响应，如果找不到对应的方法，则抛异常
        // 设置编码
        request.setCharacterEncoding("utf-8");

        // 获取前端传递的operate
        String operate = request.getParameter("operate");

        // 如果为空则设置为index，然后会调用index方法
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        try {
            // 获取方法对象
            Method method = getClass().getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);

            // 如果方法不为null，则执行对应的方法
            if (method != null) {
                System.out.println("=========================================" + this);
                System.out.println(this.getServletName());// servlets.FruitServlet
                method.invoke(this, request, response);
                return;
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("没有找到名称为" + operate + "的方法");


    }

    // 首页方法，默认
    private void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // ------------------------------准备工作------------------------------
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        // 加载session或创建session
        HttpSession session = request.getSession();

        // ---------------------------------页码pageNum处理---------------------------------
        // 设置默认页码为1
        Integer pageNum = 1;
        // 从session中获取页面
        String pageNumStr = request.getParameter("pageNum");
        // 如果页面不为null，并且不为空，则获取页码数并类型转换。否则，pageNum默认就是1
        if (pageNumStr != null) {
            pageNum = Integer.parseInt(pageNumStr);
        }

        // ---------------------------------获取搜索输入的keyword---------------------------------
        // 获取表单提交的内容
        String keyword = request.getParameter("keyword");

        // ---------------------------------使用mybatis分页查询---------------------------------
        // 在查询功能之前开启分页（当前页码，每页5条数据）
        PageHelper.startPage(pageNum, 5);
        // 查询所有数据
        List<Fruit> fruitList = mapper.getFruitList(keyword);
        // 查询功能之后，可以获得分页相关的所有数据
        PageInfo<Fruit> page = new PageInfo<>(fruitList, 5);

        // ---------------------------------添加session域-------------------------------
        // 将总页码数添加进session域中
        session.setAttribute("page", page);
        // 将数据库中的查询的字段保存到session作用域
        session.setAttribute("fruitList", fruitList);

        // 渲染视图
        super.processTemplate("fruit_all", request, response);
    }

    // 对水果进行更新
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取参数并转换
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
        pojo.Fruit fruit = new pojo.Fruit(id, name, price, count, remark);
        mapper.updateFruit(fruit);


        // 重定向，目的是重新给IndexServlet发请求
        response.sendRedirect("fruit.do");
    }

    // 跳转到某个水果详情
    private void toUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取前端传递的id
        String idStr = request.getParameter("id");

        // 如果 字符串不为null 并且 不等于空字符串（可以封装到工具类中）
        if (idStr != null && !"".equals(idStr)) {
            // 将字符型转为数值型
            int id = Integer.parseInt(idStr);

            // 根据fid获取特定的水果库存信息
            pojo.Fruit fruitbyid = mapper.getFruitById(id);

            // 将数据放在request域中
            request.setAttribute("fruitbyid", fruitbyid);

            // 展现fruit_update.html
            super.processTemplate("fruit_update", request, response);
        }

    }

    // 删除水果的方法
    private void del(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取前端传递的id
        String idStr = request.getParameter("id");
        // 调用工具包中的isNotEmpty，判断字符串是否不为null且不为空
        if (idStr != null && !"".equals(idStr)) {
            // 将字符串转换为数值型
            int id = Integer.parseInt(idStr);

            // 执行删除
            mapper.delFruit(id);

            // 重定向到首页
            response.sendRedirect("fruit.do");
        }
    }

    // 添加水果的方法
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        // 获取前端传递的表单内容
        String name = request.getParameter("name");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Integer count = Integer.parseInt(request.getParameter("count"));
        String remark = request.getParameter("remark");

        // 创建对象添加数据
        pojo.Fruit fruit = new pojo.Fruit(null, name, price, count, remark);
        // 将数据添加进入表中
        mapper.addFruit(fruit);

        // 重定向，目的是重新给IndexServlet发请求，
        response.sendRedirect("fruit.do");

    }
}
