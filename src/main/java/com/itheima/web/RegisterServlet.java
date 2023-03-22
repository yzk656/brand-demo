package com.itheima.web; /**
 * @ClassName: ${NAME}
 * @Description: TODO
 * @Author: 杨振坤
 * @date: 2023/3/20 15:01
 */

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*1. 获取用户名和密码*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        /*获取用户输入的验证码*/
        String checkCode = request.getParameter("checkCode");

        /*获取程序生成的验证码*/
        HttpSession session=request.getSession();
        String checkCode1 = (String) session.getAttribute("checkCode");


        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        /*比对*/
        if (!checkCode1.equalsIgnoreCase(checkCode)) {
            /*不允许注册*/
            request.setAttribute("register_msg", "验证码错误！！！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        /*2. 调用Service进行注册*/
        boolean flag = userService.register(user);
        /*3. 判断注册是否成功*/
        if (flag) {
            /*注册成功，跳转到登录页面*/
            request.setAttribute("register_msg", "注册成功，请登录！！！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            /*注册失败，跳转到注册页面*/
            request.setAttribute("register_msg", "抱歉，用户名已经存在");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
