package org.mortal.mtool.common.core.configs;

import lombok.extern.slf4j.Slf4j;
import org.mortal.mtool.common.core.interceptors.BasicAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/28 15:10
 * @description config
 */
@Configuration
@Slf4j
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        registry.addInterceptor(new BasicAuthInterceptor()).addPathPatterns("/**").order(1);
    }
}
