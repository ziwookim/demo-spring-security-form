package me.whiteship.demospringsecurityform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE - 15)
//@EnableWebSecurity (생략 가능 - Spring boot 자동 설정이 자동으로 추가 해준다.)
public class AnotherSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * .antMatcher: antMatchers("/secured") matches only the exact /secured URL
     * .mvcMatcher: mvcMatchers("/secured") matches /secured as well as /secured/, /secured.html, /secured.xyz
     * @Order <- filter 우선 순위
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .antMatcher("/account/**")
                .authorizeHttpRequests()
                .anyRequest().permitAll();
    }
}
