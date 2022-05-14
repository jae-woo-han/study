package com.study.board.exception;

import com.study.board.vo.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    ResponseEntity<ResponseVO> apiExceptionHandler(String message, ResponseVO responseData){
        return new ResponseEntity<>(responseData,null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
