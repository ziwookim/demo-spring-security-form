package me.whiteship.demospringsecurityform.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired AccountRepository accountRepository;

    /**
     * param 값인 'username'에 해당하는 user 정보를 'UserDetails' 타입으로 리턴 해야한다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
        account.encodePassword();
//        account.setPassword("{noop}" + account.getPassword());
        return this.accountRepository.save(account);
    }
}
