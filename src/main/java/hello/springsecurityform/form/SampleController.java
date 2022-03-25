package hello.springsecurityform.form;

import hello.springsecurityform.account.AccountContext;
import hello.springsecurityform.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class SampleController {

    @Autowired SampleService sampleService;

    @Autowired AccountRepository accountRepository;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello, " + principal.getName());
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
}
