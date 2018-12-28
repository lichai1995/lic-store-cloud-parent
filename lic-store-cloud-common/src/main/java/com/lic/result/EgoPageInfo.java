package com.lic.result;


import java.io.Serializable;
import java.util.List;

public class EgoPageInfo implements Serializable {
    //当前页

    private Integer currentPage;
    //每页显示条数
    private Integer pageSize;
    //总记录数
    private Long count;
    //总页数
    private Integer totalPage;
    //上一页
    private Integer prePage;
    //下一页
    private Integer nextPage;
    //是否有上一页
    private Boolean havePre;
    //是否有下一页
    private Boolean haveNext;
    //首页
    private Integer firstPage;
    //尾页
    private Integer finalPage;
    //列表
    private List<?> rows;

    public EgoPageInfo(Integer currentPage,Integer pageSize,Long count){
        super();
        this.currentPage=currentPage;
        this.pageSize=pageSize;
        this.count=count;
        this.totalPage = (int) Math.ceil(count.doubleValue()/pageSize.doubleValue());
        this.havePre=!(currentPage == 1);
        this.haveNext=(currentPage.equals(totalPage));
        if(havePre){
            this.prePage=currentPage -1 ;
        }
        if(haveNext){
            this.nextPage=currentPage-1;
        }
        this.firstPage=1;
        this.finalPage=totalPage;
    }
    public EgoPageInfo(){}


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Boolean getHavePre() {
        return havePre;
    }

    public void setHavePre(Boolean havePre) {
        this.havePre = havePre;
    }

    public Boolean getHaveNext() {
        return haveNext;
    }

    public void setHaveNext(Boolean haveNext) {
        this.haveNext = haveNext;
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public Integer getFinalPage() {
        return finalPage;
    }

    public void setFinalPage(Integer finalPage) {
        this.finalPage = finalPage;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
