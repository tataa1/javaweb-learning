package top.soft.bookonline.dao.impl;

import top.soft.bookonline.dao.BookDao;
import top.soft.bookonline.entity.Book;
import top.soft.bookonline.util.JdbcUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class BookDaoImpl implements BookDao {
    // 声明 JdbcTemplate 对象
    private final JdbcTemplate template = new JdbcTemplate(JdbcUtil.getDataSource());

    @Override
    public List<Book> selectAll() {
        String sql = "SELECT * FROM t_book ORDER BY id DESC";
        return template.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book getBookById(int id) {
        String sql = "SELECT * FROM t_book WHERE id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
    }
}