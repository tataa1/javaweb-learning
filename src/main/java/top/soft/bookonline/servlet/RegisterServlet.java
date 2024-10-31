package top.soft.bookonline.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import top.soft.bookonline.service.UserService;
import top.soft.bookonline.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求和响应的字符编码为 UTF-8，防止中文乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        // 获取用户提交的账号和密码
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        try {
            // 调用用户服务进行注册
            userService.signUp(account, password);
            // 注册成功，重定向到主页
            resp.sendRedirect(req.getContextPath() + "/login.html");
        } catch (IllegalArgumentException e) {
            // 如果捕获到 IllegalArgumentException，表示账号已存在
            req.setAttribute("errorMessage", "账号已存在，请使用其他账号注册");
            // 转发请求回到注册页面，显示错误信息
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将 GET 请求也转交给 doPost 处理，确保逻辑统一
        doPost(req, resp);
    }
}
