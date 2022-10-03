package me.whiteship.demospringsecurityform.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * 이렇게 회원가입 페이지 생성해서는 절대 안된다.
     * 편의를 위해서 이렇게 진행.
     */
    @GetMapping("/account/{role}/{username}/{password}")
    public Account createAccount(@ModelAttribute Account account) {
//        return accountRepository.save(account);
        return accountService.createNew(account);
    }

}
