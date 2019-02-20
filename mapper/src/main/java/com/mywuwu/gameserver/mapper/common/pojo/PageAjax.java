package com.mywuwu.gameserver.mapper.common.pojo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装分页请求结果
 * @author CZH
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class PageAjax<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer currentPage;//当前是第currentPage页

    private Integer total;//共total页

    private Integer pageSize;//每页sizes条

    private List<T> tableData = new ArrayList<T>();

    public PageAjax() {
    }

    public PageAjax(Integer currentPage, Integer total, Integer pageSize) {
        this.currentPage = currentPage;
        this.total = total;
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getTableData() {
        return tableData;
    }

    public void setTableData(List<T> tableData) {
        this.tableData = tableData;
    }


}
