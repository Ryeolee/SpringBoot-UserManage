package com.user.usermanage.user.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/change/nickname","/api/user/change/password").authenticated()

                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);


    }



    @Override
    public void configure(WebSecurity webSecurity) throws Exception{

        webSecurity
                .ignoring()
                .antMatchers(  "/api/user/auth/sign-up",
                        "/api/user/auth/sign-in",
                        "/api/verifiy",
                        "/api/verifiy/findIdentifier",
                        "/api/verifiy/email-issue",
                        "/api/verifiy/email-verify",
                        "/api/user/change/temporary-password",
                        "/api/user/auth/reissue-token"
                );

    }
}
