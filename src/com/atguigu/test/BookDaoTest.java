package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/30 7:23
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "JVM入门到精通", "周志明", new BigDecimal(999), 110000, 0, null));
    }

    @Test
    public void deleteBook() {
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "JVM入门到精通", "周志明", new BigDecimal("100.0"), 110000, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookDao.queryBooks());
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }
    @Test
    public void queryForPageItems() {
        System.out.println(bookDao.queryForPageItems(0,4));
    }
    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }
    @Test
    public void queryForPageItemsByPrice() {
        System.out.println(bookDao.queryForPageItemsByPrice(0,4,10,50));
    }
}