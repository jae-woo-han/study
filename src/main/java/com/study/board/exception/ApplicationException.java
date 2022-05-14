package com.study.board.exception;

import com.study.board.vo.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApplicationException extends RuntimeException{
    ApplicationException(){}
    ApplicationException(String message){
        super(message);
    }
    ApplicationException(String message, ResponseVO responseData){
        super(message);
        ResponseEntity<ResponseVO> response = new ResponseEntity<>(responseData,null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
