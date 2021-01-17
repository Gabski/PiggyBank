package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import piggy.bank.domain.AuthorityType;
import piggy.bank.entity.*;
import piggy.bank.repository.AccountRepository;
import piggy.bank.repository.CurrencyRepository;
import piggy.bank.repository.UserRepository;
import piggy.bank.service.AccountService;

import javax.validation.Valid;


@Controller
public class AdminController extends AppController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    AccountRepository accountRepository;


    @Autowired
    AccountService accountService;

    @Secured("ROLE_ADMIN")
    @GetMapping({"/admin/dashboard"})
    public String dashboard(Model model) {

        return "admin/dashboard";
    }


    @Secured("ROLE_ADMIN")
    @GetMapping({"/admin/currency"})
    public String currency(Model model) {
        model.addAttribute("currencyType", new CurrencyType());
        model.addAttribute("currencyList", currencyRepository.findAll());
        return "admin/currency";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping({"/admin/accounts"})
    public String accounts(Model model) {

        model.addAttribute("accountList", accountRepository.findAll());
        return "admin/accounts";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping({"/admin/users"})
    public String users(Model model) {

        model.addAttribute("userList", userRepository.findAll());
        return "admin/users";
    }


    @PostMapping({"/admin/currency/save"})
    public String saveCurrency(Model model, @ModelAttribute("currencyType") @Valid CurrencyType currencyType, BindingResult result) {

        model.addAttribute("currencyType", currencyType);

        if (result.hasErrors()) {
            model.addAttribute("alert", Snack.create("Błąd", "Nie zapisano, sprawdź błędy"));
            model.addAttribute("currencyList", currencyRepository.findAll());
            return "admin/currency";
        }

        Currency currency = new Currency();
        currency.setSlug(currencyType.getSlug());
        currency.setName(currencyType.getName());
        currencyRepository.save(currency);
        currencyRepository.flush();

        model.addAttribute("alert", Snack.create("Sukces", "Zapisano"));
        model.addAttribute("currencyType", new CurrencyType());
        model.addAttribute("currencyList", currencyRepository.findAll());
        return "admin/currency";
    }
}

