package piggy.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPagesController extends AppController {

    @GetMapping({"/"})
    public String home(Model model) {
        return "pages/home";
    }

    @GetMapping({"/contact"})
    public String contact(Model model) {
        return "pages/contact";
    }

    @GetMapping({"/register"})
    public String register(Model model) {
        return "pages/register";
    }
}
