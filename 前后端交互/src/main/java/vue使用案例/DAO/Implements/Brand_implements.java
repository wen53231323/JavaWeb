package vue使用案例.DAO.Implements;

import vue使用案例.DAO.DAO_util;
import vue使用案例.DAO.Interface.Brand_Interface;
import vue使用案例.pojo.Brand;

import java.util.List;

public class Brand_implements extends DAO_util<Brand> implements Brand_Interface {
    // 查询所有品牌
    @Override
    public List<Brand> Select_AllBrand() {
        return super.executeQuery("select * from tb_brand");
    }

    // 添加品牌
    @Override
    public void Add_Brand(Brand brand) {
        String sql = "insert into tb_brand values(null,?,?,?,?,?)";
        super.executeUpdate(sql, brand.getBrandName(), brand.getCompanyName(), brand.getOrdered(), brand.getDescription(), brand.getStatus());
    }

    // 根据id查询品牌
    @Override
    public List<Brand> Select_ById(int id) {
        return super.executeQuery("select * from tb_brand where id = ?", id);
    }

    // 修改更新品牌信息
    @Override
    public void Update_Brand(Brand brand) {
        String sql = "update tb_brand set brand_name = ?,company_name = ?,ordered = ?,description = ?,status = ? where id = ?";
        super.executeUpdate(sql, brand.getBrandName(),brand.getCompanyName(),brand.getOrdered(),brand.getDescription(),brand.getStatus(),brand.getId());
    }
}
