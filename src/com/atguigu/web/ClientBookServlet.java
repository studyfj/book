package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/31 7:58
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class ClientBookServlet extends BookServlet{
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数pageNo和pageSize
        int pageNo = JdbcUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = JdbcUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用BookService.page(pageNo, pageSize):page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("client/bookServlet?action=page");

        // 保存request域中
        req.setAttribute("page",page);
        // 请求转发pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * 处理价格区间搜索功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数pageNo和pageSize
        int pageNo = JdbcUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = JdbcUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = JdbcUtils.parseInt(req.getParameter("min"), 0);
        int max = JdbcUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        // 调用BookService.page(pageNo, pageSize):page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小价格的参数，追加参数
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        // 保存request域中
        req.setAttribute("page",page);
        // 请求转发pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
