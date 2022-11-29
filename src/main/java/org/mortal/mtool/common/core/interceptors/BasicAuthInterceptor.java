package org.mortal.mtool.common.core.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.mortal.mtool.common.core.Constants;
import org.mortal.mtool.common.core.annotations.ApiIntro;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/28 15:04
 * @description 身份校验拦截
 */
//@Component
@Slf4j
public class BasicAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("BasicAuthInterceptor preHandle url->{}", request.getRequestURL());

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ApiIntro apiIntro = handlerMethod.getMethodAnnotation(ApiIntro.class);
            if (apiIntro != null) {
                log.info("api intro->{}", apiIntro.value());
                MDC.put(Constants.ACCESS_METHOD, handlerMethod.getMethod().getName());
                MDC.put(Constants.ACCESS_METHOD_INTRO, apiIntro.value());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

        log.info("BasicAuthInterceptor postHandle url->{}", request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        log.info("BasicAuthInterceptor afterCompletion url->{}", request.getRequestURL());
    }
}
