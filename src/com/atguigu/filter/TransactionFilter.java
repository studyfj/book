package com.atguigu.filter;

import com.atguigu.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/08/03 8:15
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("经过了过滤器!");
        try{
            filterChain.doFilter(servletRequest, servletResponse);
            // 提交事务
            JdbcUtils.commitAndClose();
        }catch (Exception e) {
            // 回滚事务
            JdbcUtils.rollbackAndClose();
            // 异常抛给tomcat服务器
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
