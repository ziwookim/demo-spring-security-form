package me.whiteship.demospringsecurityform.config;

import me.whiteship.demospringsecurityform.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired AccountService accountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .mvcMatchers("/", "/info", "/account/**").permitAll()
                // "/info" 모든 requests 허용
                .mvcMatchers("/admin").hasRole("ADMIN")
                // "/admin" 은 "ADMIN" 권한 있는 request만 허용
//                .anyRequest().authenticated();
                .anyRequest().permitAll();
        // 기타 등등 requests에 대해서는 인증 하기만 하면 된다.
        // .and()
        http.formLogin();
        // formLogin을 사용하겠다.
        // .and()
        http.httpBasic();
        // httpBasic을 사용하겠다.
    }

    /**
     * 사용할 userDetailsService는 'accountService'임을 명시.
     * 하지만 @Autowired AccountService로 bean 등록만 되어 있어도 알아서 인식한다.
     * (주석 처리)
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(accountService);
//    }

    /**
     * in-memory 데이터 날림.
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                // noop: Spring Security 내부에 장착된 기본 password Encoder (암호화 X)
//                // 암호화를 원한다면 {noop} 대신 암호화 방식 옵션 값을 넣어주면 된다.
//                .withUser("jiwoo").password("{noop}123").roles("USER").and()
//                .withUser("admin").password("{noop}!@#").roles("ADMIN");
//    }
}
