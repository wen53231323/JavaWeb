package Controllers;


import Util.SqlSessionUtil;
import Util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mapper.FruitMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Fruit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

// 水果类，类中有水果的增删改查方法
public class FruitController {

    // 调用工具类SqlSessionUtil中的静态方法静态方法getSqlSession()，获取MyBatis提供的操作数据库的会话对象SqlSession
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    // 获取UserMapper的代理实现类对象（调用sql语句）
    FruitMapper mapper = sqlSession.getMapper(FruitMapper.class);

    // 首页方法，默认
    private String index(String keyword, Integer pageNum, HttpServletRequest request) {
        // ------------------------------准备工作------------------------------
        // 加载session或创建session
        HttpSession session = request.getSession();

        // ---------------------------------页码pageNum处理---------------------------------
        // 设置默认页码为1
        pageNum = 1;
        // 从session中获取页面
        String pageNumStr = request.getParameter("pageNum");
        // 如果页面不为null，并且不为空，则获取页码数并类型转换。否则，pageNum默认就是1
        if (pageNumStr != null) {
            pageNum = Integer.parseInt(pageNumStr);
        }

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
        return "fruit_all";
    }

    // 对水果进行更新
    // 方法形参为前端传递的字段
    private String update(Integer id, String name, Integer price, Integer count, String remark) {
        // 执行更新
        Fruit fruit = new Fruit(id, name, price, count, remark);
        mapper.updateFruit(fruit);
        // 资源跳转
        return "redirect:fruit.do";

    }

    // 跳转到某个水果详情
    private String toUpdate(Integer id, HttpServletRequest request) {
        if (id != null) {
            // 根据fid获取特定的水果库存信息
            Fruit fruitbyid = mapper.getFruitById(id);

            // 将数据放在request域中
            request.setAttribute("fruitbyid", fruitbyid);
            return "fruit_update";
        }else {
            return "错误";
        }
    }

    // 删除水果的方法
    private String del(Integer fid) {
        if (fid != null) {
            // 添加进数据库
            mapper.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "错误";
    }


    // 添加水果的方法
    private String add(String fname, Integer price, Integer fcount, String remark) {
        // 创建对象添加数据
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        // 将数据添加进入表中
        mapper.addFruit(fruit);
        return "redirect:fruit.do";
    }


}
