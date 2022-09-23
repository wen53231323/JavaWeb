package DAO;
import java.util.List;

public class DAO_implements extends DAO_util<Fruit> implements DAO_interface {
    // 查询所有数据
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    // 模糊查询与名字包含相似或内容相似的数据
    // 且分页查询，每页显示五条数据
    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return super.executeQuery(
                "select * from t_fruit where fname like ? or remark like ? limit ? , 5",
                "%" + keyword + "%", "%" + keyword + "%", (pageNo - 1) * 5
        );
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.load("select * from t_fruit where fid = ? ", fid);
    }


    // 更新数据
    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname = ? , price = ? , fcount = ? , remark = ? where fid = ? ";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {

        super.executeUpdate(
                "delete from t_fruit where fid = ? ", fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
    }

    // 查询库存总记录条数
    @Override
    public int getFruitCount(String keyword) {
        // 查询出的数为long类型
        return ((Long) super.executeComplexQuery(
                "select count(*) from t_fruit where fname like ? or remark like ?",
                "%" + keyword + "%", "%" + keyword + "%"
        )[0]).intValue();
    }
}

// 39行报错  ClassCastException: java.lang.Long cannot be cast to java.lang.Integer