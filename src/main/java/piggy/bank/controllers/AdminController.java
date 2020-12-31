package piggy.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController extends AppController {


    @GetMapping({"/admin/test"})
    public String home(Model model) {

        return "admin/test";
    }
}

