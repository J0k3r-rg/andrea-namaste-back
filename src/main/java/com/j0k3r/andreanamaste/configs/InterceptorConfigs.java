package com.j0k3r.andreanamaste.configs;

import com.j0k3r.andreanamaste.interceptors.RegistryInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class InterceptorConfigs implements WebMvcConfigurer {

    @Autowired
    private RegistryInterceptor registryInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(registryInterceptor);
    }

}
