package Controllers;


import DAO.Fruit;
import Util.StringUtil;
import service.Service_interface;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

// 水果类，类中有水果的增删改查方法
public class FruitController {

    // 获取封装的服务jdbc
    //private Service_interface fruit_service = new Service_implements();

    private Service_interface fruit_service = null;


    // 对水果进行更新
    // 方法形参为前端传递的字段
    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        // 执行更新
        fruit_service.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        // 资源跳转
        return "redirect:fruit.do";

    }

    // 某个水果详情
    private String edit(Integer fid, HttpServletRequest request) {
        // 如果 fid 不为null
        if (fid != null) {
            // 根据fid获取特定的水果库存信息
            Fruit fruitByFid = fruit_service.getFruitByFid(fid);
            // 将数据放在request域中
            request.setAttribute("fruit", fruitByFid);
            // 展现edit.html
            //super.processTemplate("edit", request, response);
            return "edit";
        }
        return "错误";
    }

    // 删除水果的方法
    private String del(Integer fid) {
        if (fid != null) {
            // 添加进数据库
            fruit_service.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "错误";
    }


    // 添加水果的方法
    private String add(String fname, Integer price, Integer fcount, String remark) {
        // 创建对象添加数据
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        // 将数据添加进入表中
        fruit_service.addFruit(fruit);
        return "redirect:fruit.do";
    }

    // 首页方法，默认
    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest request) {
        // 加载session或创建session
        HttpSession session = request.getSession();

        // 如果pageNo为空，设置默认页码为1
        if (pageNo == null) {
            pageNo = 1;
        }

        // ------------------------------分页和查询------------------------------
        // 如果oper!=null 说明 通过表单的查询按钮点击过来的
        // 如果oper是空的，说明 不是通过表单的查询按钮点击过来的
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1;
            // 如果keyword为null，需要设置为空字符串""，否则查询时会拼接成 %null% , 我们期望的是 %%
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            // 将keyword保存（覆盖）到session中
            session.setAttribute("keyword", keyword);
        } else {
            // 说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            // 此时keyword应该从session作用域获取

            // 如果不为null则转为字符，否则设置为空
            if (keyword != null) {
                keyword = (String) keyword;
            } else {
                keyword = "";
            }
        }

        // 获取总页数
        int pageCount = fruit_service.getPageCount(keyword);


        // 获取数据库中的字段信息
        // 模糊查询与名字包含相似或内容相似的数据
        // 且分页查询，每页显示五条数据
        List<Fruit> fruitList = fruit_service.getFruitList(keyword, pageNo);

        // --------------------------------------添加session域，重新更新当前页的值------------------------------------
        // 将页码数添加到session域中
        session.setAttribute("pageNo", pageNo);
        // 将总页码数添加进session域中
        session.setAttribute("pageCount", pageCount);
        // 将数据库中的查询的字段保存到session作用域
        session.setAttribute("fruitList", fruitList);
        return "index";
    }
}
