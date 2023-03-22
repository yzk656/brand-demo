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
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*1. 获取用户名和密码*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        /*获取复选框信息*/
        String remember = request.getParameter("remember");


        /*2. 查询用户是否存在*/
        User user = userService.login(username, password);

        /*3. 判断用户是否为null*/
        if (user != null) {
            /*判断用户是否勾选记住我*/
            /*为了防止空指针异常，可以吧字符串放在前面*/
            if ("1".equals(remember)) {
                /*勾选了，发送Cookie*/

                /*1. 创建Cookie对象*/
                Cookie c_username=new Cookie("username",username);
                Cookie c_password=new Cookie("password",password);

                /*设置Cookie的存活时间*/
                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);

                /*2. 发送*/
                response.addCookie(c_username);
                response.addCookie(c_password);

            }


            /*将登录成功后user对象，存储到session中*/
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            /*登陆成功*/
            response.sendRedirect("/selectAllServlet");
        } else {
            /*登录失败*/

            /*存储错误信息到request*/
            request.setAttribute("login_msg", "用户名或密码错误");

            /*跳转到login.jsp*/
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
