package com.study.board.vo;

import lombok.Getter;

@Getter
public class PageVO<T> {
    private int totalCount;
    private int totalPage;
    private int currentPage=1;
    private int viewPostCount=10;
    private T data;

    public PageVO(int totalCount) {
        this.totalCount = totalCount;
        int pagingNum = totalCount/viewPostCount;
        this.totalPage=(totalCount%viewPostCount>0)?pagingNum+1:pagingNum;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        int dividePage = totalCount/10;
        this.totalPage = (totalCount%10>0?dividePage+1:dividePage);
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setViewPostCount(int viewPostCount) {
        this.viewPostCount = viewPostCount;
    }
    public void setCurrentPage(int currentPage){
        this.currentPage = currentPage;
    }
}
