package top.soft.bookonline.dao;

import top.soft.bookonline.dao.impl.BookDaoImpl;
import top.soft.bookonline.entity.Book;

import java.util.List;

public class BookDaoTest {

    void selectAll(){

        BookDao bookDao=new BookDaoImpl();
        List<Book> books=bookDao.selectAll();
        System.out.println(books.size());
    }

    void getBookById(){
        Book book=new BookDaoImpl().getBookById(1);
        System.out.println(book);
    }
}
