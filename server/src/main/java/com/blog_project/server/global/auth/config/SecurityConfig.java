package com.blog_project.server.global.auth.config;

import com.blog_project.server.domain.member.service.MemberService;
import com.blog_project.server.global.auth.filter.JwtAuthenticationFilter;
import com.blog_project.server.global.auth.filter.JwtVerificationFilter;
import com.blog_project.server.global.auth.handler.MemberAccessDeniedHandler;
import com.blog_project.server.global.auth.handler.MemberAuthenticationEntryPoint;
import com.blog_project.server.global.auth.handler.MemberAuthenticationFailureHandler;
import com.blog_project.server.global.auth.handler.MemberAuthenticationSuccessHandler;
import com.blog_project.server.global.auth.jwt.JwtTokenizer;
import com.blog_project.server.global.auth.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final CorsFilter corsFilter;
    private final MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler()) // 인증 실패시 처리
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/members").permitAll()
                        .antMatchers("/members", "/members/login").permitAll()
                        .antMatchers("/members/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/content").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PATCH, "/content/*").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/content/*").hasRole("ADMIN")
                        .antMatchers("/content").permitAll()
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws  Exception{

            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);

            jwtAuthenticationFilter.setFilterProcessesUrl("/members/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());


            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(corsFilter)
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }

}
