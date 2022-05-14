package com.study.board.vo;

import lombok.Data;

/**
 *
 * api response 공통 객체
 * 결과 메세지, 데이터(객체)
 *
 * @author jae woo
 *
 */
@Data
public class ResponseVO<T> {
    private final String resultMessage;
    private final T data;
}
