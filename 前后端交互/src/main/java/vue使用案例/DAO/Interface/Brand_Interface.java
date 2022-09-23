package vue使用案例.DAO.Interface;

import vue使用案例.pojo.Brand;

import java.util.List;

public interface Brand_Interface {
    // 查询所有品牌
    List<Brand> Select_AllBrand();

    // 添加品牌
    void Add_Brand(Brand brand);


    // 根据id查询品牌
    List<Brand> Select_ById(int id);

    // 修改更新品牌信息
    void Update_Brand(Brand brand);

}
