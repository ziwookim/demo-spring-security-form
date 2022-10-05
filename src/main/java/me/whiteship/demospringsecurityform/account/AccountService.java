package me.whiteship.demospringsecurityform.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {
    /**
     * UserDetailsService
     * user 정보를 UserDetails 타입으로 가져오는 DAO(Data Access Object) 인터페이스.
     * 구현은 마음대로!
     *
     * 실제로 인증처리를 하는 인터페이스는 UserDetailsService가 아닌,
     * AuthenticationManager이다.
     */

    @Autowired AccountRepository accountRepository;

    @Autowired PasswordEncoder passwordEncoder;

    /**
     * param 값인 'username'에 해당하는 user 정보를 'UserDetails' 타입으로 리턴 해야한다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * UserDetails
         * 애플리케이션이 갖고 있는 user 정보와 Spring Security가 사용하는  Authentication 객체 사이의 어댑터
         */

        Account account = accountRepository.findByUsername(username);

        if(account == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    public Account createNew(Account account) {
        // TODO {noop}123
        // password encoding 처리
//        account.encodePassword();
        account.encodePassword(passwordEncoder);
//        account.setPassword("{noop}" + account.getPassword());
        return this.accountRepository.save(account);
    }
}
