package com.springboot.excetion;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理整个web 异常  推荐使用本方法
 */
@ControllerAdvice
public class GlobalExcetionHandler {

    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public ModelAndView handlerDIY(Exception e){

        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        mav.addObject("message",e);
        return mav;
    }
}
