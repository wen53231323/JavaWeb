package ajax使用案例.Servlet;


import com.alibaba.fastjson.JSON;
import ajax使用案例.pojo.Brand;
import ajax使用案例.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/ajax_add_Brand")
public class Add_Brand extends HttpServlet {

    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.getParameter 不能接收json的数据
       /*
       String brandName = request.getParameter("brandName");
       System.out.println(brandName);
       */
        //（1）接收数据,
        // 获取请求体数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        // 将JSON字符串转为Java对象
        Brand brand = JSON.parseObject(params, Brand.class);


        //2. 调用service 添加到数据库
        brandService.add(brand);

        //3. 响应成功标识
        response.getWriter().write("success");
    }
}