package hello.springsecurityform.form;

import hello.springsecurityform.account.Account;
import hello.springsecurityform.account.AccountContext;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

//    public void dashboard() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Object credentials = authentication.getCredentials();
//        boolean authenticated = authentication.isAuthenticated();
//    }

    public void dashboard() {
        Account account = AccountContext.getAccount();
        System.out.println("=============");
        System.out.println(account.getUsername());
    }
}
