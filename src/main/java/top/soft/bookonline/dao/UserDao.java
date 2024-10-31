package top.soft.bookonline.dao;

import top.soft.bookonline.entity.User;

public interface UserDao {
    int insertUser(User user);
    User findUser(User user);
    User findUserByAccount(String account);
    User findUserByAccountAndPassword(String account, String password);
}



