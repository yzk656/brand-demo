package com.itheima.web; /**
 * @ClassName: ${NAME}
 * @Description: 删除功能
 * @Author: 杨振坤
 * @date: 2023/3/19 19:36
 */

import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*1. 获取传过来的Id*/
        String id = request.getParameter("id");

        /*执行删除方法*/
        brandService.delete(Integer.parseInt(id));

        /*跳转到查询页面*/
        request.getRequestDispatcher("/selectAllServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
