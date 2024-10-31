package top.soft.bookonline.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.soft.bookonline.dao.UserDao;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.util.JdbcUtil;

public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.getDataSource());

    @Override
    public int insertUser(User user) {
        // 在插入之前检查账号是否已存在
        if (findUserByAccount(user.getAccount()) != null) {
            throw new RuntimeException("账号已存在，请使用其他账号注册");
        }

        // 如果账号不存在，则执行插入操作
        String sql = "INSERT INTO t_user (account, password, nickname, avatar) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getAccount(), user.getPassword(), user.getNickname(), user.getAvatar());
    }

    @Override
    public User findUser(User userDto) {
        return findUserByAccountAndPassword(userDto.getAccount(), userDto.getPassword());
    }

    @Override
    public User findUserByAccount(String account) {
        try {
            String sql = "SELECT * FROM t_user WHERE account = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(User.class),
                    account
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null; // 如果查询不到结果，返回 null
        }
    }

    @Override
    public User findUserByAccountAndPassword(String account, String password) {
        try {
            String sql = "SELECT * FROM t_user WHERE account = ? AND password = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(User.class),
                    account,
                    password
            );
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null; // 如果查询不到结果，返回 null
        }
    }
}
