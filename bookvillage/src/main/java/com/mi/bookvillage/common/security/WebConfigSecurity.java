package com.mi.bookvillage.common.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    /* WebSecurityConfigurerAdapter 상속시,
     *
     * - configure(WebSecurity web)
     *      : 서비스 전체에 영향을 미치는 설정 (보안 생략 -- css, images, js 등 / 디버그 세팅 / 방화벽 설정 등)
     * - configure(HttpSecurity http)
     *      : HttpSecurity 클래스를 주입함으로써 특정 http요청에 대한 웹 기반 보안을 구성할 수 있도록 도와준다.
     *      : HttpServletRequest 접근에 대해 권한을 얻을 수 있음
     * - configure(AuthenticationManagerBuilder auth)
     *      : AuthenticationManager()를 획득하기 위해 사용
     */


    /* 정적 리소스 시큐리티 제외 */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Jwt token 사용 -- 인증 정보 서버에 저장 X
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/upload/**").permitAll()
                .antMatchers("/book/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/customer/**").permitAll()
                .antMatchers("/point/**").permitAll()
                .antMatchers("/rental/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()  // http basic 기반으로 로그인화면 뜸 -> disable 설정
                .csrf().disable()   // csrf disable
                .cors();  // cors 활성화
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }




}
