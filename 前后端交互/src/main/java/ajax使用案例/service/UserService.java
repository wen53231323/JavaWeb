package ajax使用案例.service;


import ajax使用案例.DAO.Implements.User_implements;
import ajax使用案例.DAO.Interface.User_Interface;
import ajax使用案例.pojo.User;

import java.util.List;

public class UserService {
    private User_Interface Brand_DAO = new User_implements();

    // 登录方法
    public List<User> login(String username, String password) {
        List<User> user = Brand_DAO.select_ByPassword(username, password);
        return user;
    }


    // 注册方法
    public boolean register(User user) {

        // 根据用户名查询用户对象
        List<User> u = Brand_DAO.select_ByUsername(user.getUsername());

        if (u == null) {
            // 用户名不存在，注册
            Brand_DAO.add_user(user);
        }

        return u == null;

    }
}
