package com.j0k3r.andreanamaste.configs;

import com.j0k3r.andreanamaste.interceptors.RegistryInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
public class InterceptorConfigs implements WebMvcConfigurer {

    @Autowired
    private RegistryInterceptor registryInterceptor;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(registryInterceptor);
    }
}
