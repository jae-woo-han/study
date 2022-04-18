package com.study.board.vo;

import lombok.Data;

@Data
public class PageVO<T> {
    private int totalCount;
    private int totalPage;
    private int currentPage=1;
    private T data;

    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        int dividePage = totalCount/10;
        this.totalPage = (totalCount%10>0?dividePage+1:dividePage);
    }
}
