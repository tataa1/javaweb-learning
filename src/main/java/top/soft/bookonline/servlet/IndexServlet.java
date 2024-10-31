package top.soft.bookonline.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import top.soft.bookonline.entity.Book;
import top.soft.bookonline.service.BookService;
import top.soft.bookonline.service.impl.BookServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建 BookService 实例
        BookService bookService = new BookServiceImpl();

        // 获取书籍列表
        List<Book> bookList = bookService.getBooks();

           req.setAttribute("bookList",bookList);

        // 转发到 index.jsp
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}


