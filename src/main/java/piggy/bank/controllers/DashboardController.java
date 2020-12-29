package piggy.bank.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piggy.bank.repository.AccountHistoryRecordRepository;
import piggy.bank.repository.AccountRepository2;
import piggy.bank.repository.UserRepository;
import piggy.bank.service.UserService;


@Controller
public class DashboardController extends AppController {
    @GetMapping({"/dashboard"})
    public String show(Model model, UserService userService) {

        AccountRepository2 accountRepository = new AccountRepository2();
        AccountHistoryRecordRepository accountHistoryRecordRepository = new AccountHistoryRecordRepository();

        int size = accountHistoryRecordRepository.size();


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("username", name);

        model.addAttribute("historyList", accountHistoryRecordRepository.getAll().subList((size - 5), size));
        model.addAttribute("accountList", accountRepository.getAll());
        return "pages/dashboard";
    }
}
