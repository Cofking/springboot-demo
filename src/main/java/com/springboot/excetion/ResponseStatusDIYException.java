package com.springboot.excetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  @ResponseStatus+自定义异常 需要配置  server.error.include-message: always  才可以显示 message 属性
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason="自定义错误异常")
public class ResponseStatusDIYException extends  RuntimeException{

    public ResponseStatusDIYException(){

    }
    public ResponseStatusDIYException(String message){
        super(message);
    }

}
