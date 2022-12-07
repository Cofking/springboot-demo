package com.springboot.excetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason="自定义错误异常")
public class ResponseStatusDIYException extends  RuntimeException{

    public ResponseStatusDIYException(){

    }
    public ResponseStatusDIYException(String message){
        super(message);
    }

}
