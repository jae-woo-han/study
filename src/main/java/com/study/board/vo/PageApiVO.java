package com.study.board.vo;

import lombok.Getter;

/**
 * <Pre>
 * Paging 객체, paging 하는 객체 포함 안하는 형태
 *
 * Field 설명
 *  int totalCount    : 페이징하는 리스트 전체 수
 *  int totalPage     : totalCount 기반으로 계산한 전체 페이지 수
 *  int currentPage   : 현재 보고 있는 페이지; default value 1
 *  int viewPostCount : page 당 게시할 데이터 수; default value 10
 *  int viewPageNum   : 한 화면에서 보여줄 최대 페이지 수; default value 10
 * </Pre>
 * @author jaewoo
 */
@Getter
public class PageApiVO {

    private int totalCount;
    private int totalPage;
    private int currentPage=1;
    private int viewPostCount=10;
    private int viewPageNum=10;

    public PageApiVO(int totalCount){
        this.totalCount = totalCount;
        int pagingNum = totalCount/viewPostCount;
        this.totalPage=(totalCount%viewPostCount>0)?pagingNum+1:pagingNum;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
