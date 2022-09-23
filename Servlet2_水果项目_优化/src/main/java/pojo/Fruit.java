package pojo;

// pojo/vo: 值对象，普通java对象
// 表的映射类
public class Fruit {
    private Integer id ; // id
    private String name ;// 名称
    private Integer price ;// 单价
    private Integer count ; // 库存
    private String remark ; // 备注

    public Fruit() {
    }

    public Fruit(Integer id, String name, Integer price, Integer count, String remark) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", remark='" + remark + '\'' +
                '}';
    }
}
