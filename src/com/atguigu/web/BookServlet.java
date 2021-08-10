package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.JdbcUtils;
import com.atguigu.utils.WebUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/30 7:49
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class BookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNO = JdbcUtils.parseInt(req.getParameter("pageNo"),0);
        pageNO += 1;
        // 获取请求参数封装JavaBean
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        // 调用bookService保存数据库
        bookService.addBook(book);

        // 一定要重定向  /manager/bookServlet?action=list
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNO);

    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数id
        int i = JdbcUtils.parseInt(req.getParameter("id"), 0);
        // 调用bookService操作数据库
        bookService.deleteBookById(i);

        // 重定向图书列表
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));



    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数图书编号
        int id = JdbcUtils.parseInt(req.getParameter("id"),0);
        // 调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        // 保存到图书到request域中
        req.setAttribute("book", book);
        // 请求转发到pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);


    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求参数，封装对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 调用bookService的方法保存到数据库
        bookService.updateBook(book);
        // 重定向到图书列表管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过bookService查询全部图书
        List<Book> books = bookService.queryBooks();
        // 保存到request域中
        req.setAttribute("books", books);
        // 情求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }

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

        page.setUrl("manager/bookServlet?action=page");
        // 保存request域中
        req.setAttribute("page",page);
        // 请求转发pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
