package top.soft.bookonline.service;

import org.junit.jupiter.api.Test;
import top.soft.bookonline.dao.BookDao;
import top.soft.bookonline.dao.impl.BookDaoImpl;
import top.soft.bookonline.entity.Book;

import java.util.List;

class BookDaoTest {

    @Test
    void getBooks() {
        BookDao bookDao = new BookDaoImpl();
        List<Book> books = bookDao.selectAll();
        System.out.println(books.size());
    }

    @Test
    void getBookById() {
        Book book = new BookDaoImpl().getBookById(1);
        System.out.println(book);
    }
}