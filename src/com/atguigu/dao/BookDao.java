package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/30 7:18
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public interface BookDao {

    /**
     *
     * @param book
     * @return
     */
    int addBook(Book book);

    int deleteBookById(Integer id);

    int updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
