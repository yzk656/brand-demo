package com.itheima.web; /**
 * @ClassName: ${NAME}
 * @Description: TODO
 * @Author: 杨振坤
 * @date: 2023/3/19 8:38
 */

import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    private BrandService service = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*1. 接收Id*/
        String id = request.getParameter("id");
        /*调用Service查询*/
        Brand brand = service.selectById(Integer.parseInt(id));
        /*3. 存储到Request域中*/
        request.setAttribute("brand", brand);

        /*4. 转发到update.jsp*/
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }


}
