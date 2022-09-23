package vue使用案例.service;


import vue使用案例.DAO.Implements.Brand_implements;
import vue使用案例.DAO.Interface.Brand_Interface;
import vue使用案例.pojo.Brand;

import java.util.List;

public class BrandService {
    private Brand_Interface Brand_DAO = new Brand_implements();

    // 查询所有
    public List<Brand> selectAll() {
        List<Brand> brands = Brand_DAO.Select_AllBrand();
        return brands;
    }



    // 添加
    public void add(Brand brand) {
        Brand_DAO.Add_Brand(brand);
    }


    // 根据id查询
    public List<Brand> selectById(int id) {
        // 调用BrandMapper.selectAll()
        List<Brand> brand = Brand_DAO.Select_ById(id);
        return brand;
    }


    // 修改
    public void update(Brand brand) {
        Brand_DAO.Update_Brand(brand);

    }
}
