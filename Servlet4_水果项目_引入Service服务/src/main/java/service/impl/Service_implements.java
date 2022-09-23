package service.impl;


import DAO.DAO_implements;
import DAO.DAO_interface;
import DAO.Fruit;
import service.Service_interface;

import java.util.List;

public class Service_implements implements Service_interface {

    private DAO_interface fruitDAO = new DAO_implements();
    //private DAO_interface fruitDAO = null;

    // //获取指定页面的库存列表信息
    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDAO.getFruitList(keyword, pageNo);
    }

    // 添加库存记录信息
    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    // 根据id查看指定库存记录
    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    // 删除特定库存记录
    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    // 获取总页数
    @Override
    public Integer getPageCount(String keyword) {
        int count = fruitDAO.getFruitCount(keyword);
        int pageCount = (count + 5 - 1) / 5;
        return pageCount;
    }
    // 修改特定库存记录
    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
