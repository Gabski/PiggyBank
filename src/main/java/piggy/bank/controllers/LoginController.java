package piggy.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends AppController {

    @GetMapping({"/login"})
    public String login(Model model) {
        return "pages/login";
    }

    @GetMapping({"/welcome"})
    public String welcome(Model model) {
        return "pages/login";
    }

    @GetMapping({"/logout"})
    public String logout(Model model) {
        return "pages/login";
    }

}
