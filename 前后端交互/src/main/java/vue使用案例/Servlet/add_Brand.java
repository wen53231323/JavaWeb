package vue使用案例.Servlet;


import com.alibaba.fastjson.JSON;
import vue使用案例.pojo.Brand;
import vue使用案例.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/vue_add_Brand")
public class add_Brand extends HttpServlet {

    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}