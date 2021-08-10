package com.atguigu.pojo;

import javax.security.auth.kerberos.KerberosPrincipal;
import java.util.List;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/30 10:32
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */

/**
 * page分页的模型对象
 * @param <T> 具体的模型的javaBean对象
 */
public class Page<T> {

    public static final Integer PAGE_SIZE = 4;
    // 当前页码
    private Integer pageNo;

    // 总页码
    private Integer pageTotal;

    // 当前页显示数量
    private Integer pageSize = PAGE_SIZE;

    // 总记录数
    private Integer pageTotalCount;

    // 当前页数据
    private List<T> items;

    // 分页条的请求地址
    private String url;

    public Integer getPageNo() {
        return pageNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageNo(Integer pageNo) {
        // 数据边界检查
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
