package me.whiteship.demospringsecurityform.config;

import me.whiteship.demospringsecurityform.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 50)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired AccountService accountService;

    public SecurityExpressionHandler expressionHandler() {
//    public AccessDecisionManager accessDecisionManager() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

//        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
//        webExpressionVoter.setExpressionHandler(handler);
//
//        // Voter List
//        List<AccessDecisionVoter<? extends  Object>> voters = Arrays.asList(webExpressionVoter);
//        return new AffirmativeBased(voters);

        return handler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .mvcMatchers("/", "/info", "/account/**", "/signup").permitAll() // 모두 허용
                .mvcMatchers("/admin").hasRole("ADMIN") // ADMIN 관한이 있어야함
                .mvcMatchers("/user").hasRole("USER")
                .anyRequest().authenticated() // 인증만되면 모두 접근 가능
                .expressionHandler(expressionHandler());
//                .accessDecisionManager(accessDecisionManager());

        // "/info" 모든 requests 허용
        // "/admin" 은 "ADMIN" 권한 있는 request만 허용
        // expressionHandler -> roleHierarchy 적용 (Admin은 User권한보다 상위 권한이다.
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
