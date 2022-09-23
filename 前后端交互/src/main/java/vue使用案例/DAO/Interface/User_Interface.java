package vue使用案例.DAO.Interface;

import vue使用案例.pojo.User;

import java.util.List;

public interface User_Interface {
    // 根据用户名和密码查询用户对象
    List<User> select_ByPassword(String username, String password);
    // 根据用户名查询用户对象
    List<User> select_ByUsername(String username);
    // 添加用户
    void add_user(User user);

}
