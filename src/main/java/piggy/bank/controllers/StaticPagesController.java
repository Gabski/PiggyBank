package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import piggy.bank.entity.*;
import piggy.bank.repository.CurrencyRepository;
import piggy.bank.repository.CurrencyRepository2;
import piggy.bank.repository.UserRepository;

import java.util.List;

@Controller
public class StaticPagesController extends AppController {

    @Autowired
    CurrencyRepository currencyRepository;

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

        User user = new User();
        Currency currency = currencyRepository.findBySlug("FSE");
        model.addAttribute("registerType", RegisterType.create(user, currency));

        return "pages/register";
    }

    @ModelAttribute("typesOfCurrency")
    public List<Currency> loadCurrencyType() {
        return currencyRepository.findAll();
    }
}
