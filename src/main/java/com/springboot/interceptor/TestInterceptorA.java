package com.springboot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器测试
 * @author Admin
 */
@Slf4j
@Component
public class TestInterceptorA implements HandlerInterceptor {

    //前置拦截  目标方法之前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String basic = request.getHeader("Basic");
        if(StringUtils.hasLength(basic)){
            return true;
        }
        //返回 false 被拦截
        log.info("preHandle 拦截{}",request.getRequestURL());
        return true;
    }

    // 后置拦截  目标方法执行完后拦截
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("postHandle 拦截{}",request.getRequestURL());
    }

    //最后拦截  渲染完视图之后拦截   之前的环节发生任何异常都将直接执行本方法  前提是 成功执行了前置拦截
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("afterCompletion 拦截{}",request.getRequestURL());
    }

}
