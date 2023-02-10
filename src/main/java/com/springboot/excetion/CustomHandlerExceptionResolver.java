package com.springboot.excetion;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义异常处理器
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE) //最高优先级 可以作为默认的全局异常处理规则
@Component
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            response.sendError(411, "自定义异常处理器"); // 之后会去 处理器中 处理 该异常 ，跳转对应的 异常页面 ErrorViewResolver
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
