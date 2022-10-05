package me.whiteship.demospringsecurityform.form;

import me.whiteship.demospringsecurityform.account.Account;
import me.whiteship.demospringsecurityform.account.AccountContext;
import me.whiteship.demospringsecurityform.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {

    public void dashboard() {
        // ThreadLocal 타입의 필드는 별도의 매개변수 없이 AccountContext로 접근 가능하다.
        Account account = AccountContext.getAccount();
        System.out.println("=================");
        System.out.println(account.getUsername());
    }

//    public void dashboard() {
//        /**
//         * principal 객체를 참조하지 않더라도, SecurityContextHolder를 통해서 Authentication 정보를 참조할 수 있다.
//         */
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        /**
//         * principal: 인증한 사용자 정보, "누구"에 해당하는 정보.
//         *            UserDetailsService에서 리턴한 정보 (UserDetails 객체)
//         */
//        Object principal = authentication.getPrincipal();
//
//        /**
//         * authorities: 사용자가 갖고 있는 권한 정보
//         */
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        /**
//         * 인증 이후에는 credential 값 필요 없음. (빈 값.)
//         */
//        Object credential = authentication.getCredentials();
//
//        /**
//         * 인증 여부
//         */
//        boolean authenticated = authentication.isAuthenticated();
//    }
}
