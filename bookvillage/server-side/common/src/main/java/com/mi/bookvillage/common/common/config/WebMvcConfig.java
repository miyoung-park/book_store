package com.mi.bookvillage.common.common.config;

import com.mi.bookvillage.common.common.interceptor.JwTokenInterceptor;
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

    private final JwTokenInterceptor jwTokenInterceptor;

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
        // TODO: 인터셉터 - 커스텀 어노테이션 ( 어떤 로직으로 들어갈 지 모르기 때문에 잡을 수 없나 ? )
        //       해당 요청이 알맞는 url 을 통해 전송될 때 , 해당 메소드가 해당 어노테이션을 가지고 있는 지 없는지를 파악하는...
        //        Filter / Interceptor / AOP 로 구현하는 것의 차이점 , 시점 차이
        registry.addInterceptor(jwTokenInterceptor);


    }



}
