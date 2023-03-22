package com.itheima.web.filter; /**
 * @ClassName: ${NAME}
 * @Description: 登录验证的过滤器
 * @Author: 杨振坤
 * @date: 2023/3/22 16:31
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        /**
         * 总结：
         *  1. 如果是访问注册或者登录接口就直接放行
         *  2. 如果不是访问登录或者注册，在进行判断是否登录过
         */



        /*判断访问路径是否和登录和注册有关*/
        String[] urls = {"/login.jsp", "/css/", "/imgs/", "/loginServlet", "register.jsp", "/registerServlet", "/checkCodeServlet"};


        /*判断session中是否存在User*/
        HttpServletRequest request1 = (HttpServletRequest) request;

        /*获取资源访问路径*/
        String requestURL = request1.getRequestURL().toString();

        /*循环判断*/
        for(String i:urls){
            if(requestURL.contains(i)){
                chain.doFilter(request,response);

                return;
            }
        }

        HttpSession session = request1.getSession();
        Object user = session.getAttribute("user");

        /*2. 判断是否存在User*/
        if (user == null) {
            /*没有登录，跳转到登录页面*/
            request1.setAttribute("login_msg", "您尚未登录");
            request1.getRequestDispatcher("/login.jsp").forward(request1, response);
        }

        chain.doFilter(request, response);

    }
}
