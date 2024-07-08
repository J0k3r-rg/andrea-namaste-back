package com.j0k3r.andreanamaste.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RegistryInterceptor implements HandlerInterceptor {

    private static MultiReadHttpServletRequest requestWrapper;

    private static CustomHttpServletResponseWrapper responseWrapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestWrapper = new MultiReadHttpServletRequest(request);
        responseWrapper = new CustomHttpServletResponseWrapper(response);
        return HandlerInterceptor.super.preHandle(request, responseWrapper, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
