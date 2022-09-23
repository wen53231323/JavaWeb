package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Fruit;

import java.util.List;

public interface FruitMapper {
    //获取所有的库存列表信息
    List<Fruit> getAllFruitList();

    //查询库存总记录条数
    Integer getFruitCount(@Param("keyword")String keyword);

    //获取 指定页码 上的库存列表信息 , 每页显示5条
    List<Fruit> getFruitList(@Param("keyword")String keyword,@Param("pageNo") Integer pageNo);

    //根据fid获取特定的水果库存信息
    Fruit getFruitById(@Param("id")Integer id);

    //修改指定的库存记录
    void updateFruit(Fruit fruit);

    //根据fid删除指定的库存记录
    void delFruit(@Param("id")Integer id);

    //添加新库存记录
    void addFruit(Fruit fruit);


}
