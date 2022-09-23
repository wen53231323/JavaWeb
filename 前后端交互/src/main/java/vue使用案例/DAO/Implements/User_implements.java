package vue使用案例.DAO.Implements;

import vue使用案例.DAO.DAO_util;
import vue使用案例.DAO.Interface.User_Interface;
import vue使用案例.pojo.User;

import java.util.List;

public class User_implements extends DAO_util<User> implements User_Interface {
    // 根据用户名和密码查询用户对象
    @Override
    public List<User> select_ByPassword(String username, String password) {
        return super.executeQuery("select * from tb_user where username = ? and password = ?", username, password);
    }

    // 根据用户名查询用户对象
    @Override
    public List<User> select_ByUsername(String username) {
        return super.executeQuery("select * from tb_user where username = ?", username);
    }

    // 添加用户
    @Override
    public void add_user(User user) {
        String sql = "insert into t_fruit values(0,?,?)";
        super.executeUpdate(sql, user.getUsername(), user.getPassword());

    }
}
