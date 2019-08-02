package cn.itcast.travel.domain;


import java.util.List;

/**
 * @program: travel--cn.itcast.travel.domain
 * @author: WaHotDog 2019-07-31 11:38
 **/


public class PageBeen<T> {
    private int totalCount;//当前总记录数
    private int totalPage;//当前总页数
    private int currentPage;//当前页
    private int pageSize;//每页条数
    private List<T> list;//每页显示的数据集合

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
