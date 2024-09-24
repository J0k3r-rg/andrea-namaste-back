package com.j0k3r.andreanamaste.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Component
public class RegistryInterceptor implements HandlerInterceptor {

    @SuppressWarnings("unused")
    private static MultiReadHttpServletRequest requestWrapper;

    private static CustomHttpServletResponseWrapper responseWrapper;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        requestWrapper = new MultiReadHttpServletRequest(request);
        responseWrapper = new CustomHttpServletResponseWrapper(response);
        return HandlerInterceptor.super.preHandle(request, responseWrapper, handler);
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
