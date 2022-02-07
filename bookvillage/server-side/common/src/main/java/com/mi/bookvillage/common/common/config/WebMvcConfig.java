package com.mi.bookvillage.common.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${resources.location}")
    private String location;

    @Value("${resources.path_patterns}")
    String path_patterns;

    /**
     * 정적리소스 연결을 위한... -- 더 알아봐야 함
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("Loading Images");

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/Users/miyoung/ebrainSoft_study/resources/book_village/images/upload")
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }


    /**
     * Interceptor 등록 / 제외 URL 추가 ---- 2022.02.07 Interceptor 기능 주석처리 ( Annotation 으로 구현 )
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(jwtTokenInterceptor)
               // .addPathPatterns("/**")
               // .excludePathPatterns("/**/login", "/book/list", "/book/detail/*");

    }



}
