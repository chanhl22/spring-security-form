package hello.springsecurityform.form;

import hello.springsecurityform.account.Account;
import hello.springsecurityform.account.AccountContext;
import hello.springsecurityform.account.AccountRepository;
import hello.springsecurityform.account.UserAccount;
import hello.springsecurityform.book.Book;
import hello.springsecurityform.book.BookRepository;
import hello.springsecurityform.common.CurrentUser;
import hello.springsecurityform.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    @Autowired SampleService sampleService;

    @Autowired AccountRepository accountRepository;

    @Autowired
    BookRepository bookRepository;

//    @GetMapping("/")
//    public String index(Model model, @AuthenticationPrincipal UserAccount userAccount) {
//        if (userAccount == null) {
//            model.addAttribute("message", "Hello Spring Security");
//        } else {
//            model.addAttribute("message", "Hello, " + userAccount.getUsername());
//        }
//        return "index";
//    }

//    @GetMapping("/")
//    public String index(Model model, @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") Account account) {
//        if (account == null) {
//            model.addAttribute("message", "Hello Spring Security");
//        } else {
//            model.addAttribute("message", "Hello, " + account.getUsername());
//        }
//        return "index";
//    }

    @GetMapping("/")
    public String index(Model model, @CurrentUser Account account) {
        if (account == null) {
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello, " + account.getUsername());
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Info");
        return "info";
    }

//    @GetMapping("/dashboard")
    public String dashboardV1(Model model, Principal principal) {
        model.addAttribute("message", "Hello, " + principal.getName());
        sampleService.dashboardV1();
        return "dashboard";
    }

//    @GetMapping("/dashboard")
    public String dashboardV2(Model model, Principal principal) {
        model.addAttribute("message", "Hello, " + principal.getName());
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.dashboardV2();
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboardV3(Model model, Principal principal) {
        model.addAttribute("message", "Hello, " + principal.getName());
        sampleService.dashboardV3();
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin, " + principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("message", "Hello User, " + principal.getName());
        model.addAttribute("books", bookRepository.findCurrentUserBooks());
        return "user";
    }

    @GetMapping("/async-handler")
    @ResponseBody
    public Callable<String> asyncHandler() {
        SecurityLogger.log("MVC");
        return () -> {
            SecurityLogger.log("Callable");
            return "Async Handler";
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async service");
        return "Async Service";
    }
}
