package top.soft.bookonline.service.impl;

import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.dao.impl.UserDaoImpl;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.service.UserService;

import java.time.LocalDateTime;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User signIn(String account, String password) {
        // 根据账号和密码查找用户，用于登录
        return userDao.findUserByAccountAndPassword(account, password);
    }

    @Override
    public User signUp(String account, String password) {
        // 检查账号是否已存在
        if (userDao.findUserByAccount(account) != null) {
            throw new IllegalArgumentException("账号已存在，请使用其他账号注册");
        }

        // 如果账号不存在，创建新用户并设置属性
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setCreateTime(LocalDateTime.now()); // 设置用户注册时间

        // 插入用户数据到数据库
        userDao.insertUser(user);

        return user;
    }
}
