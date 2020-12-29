package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piggy.bank.entity.User;
import piggy.bank.repository.AccountHistoryRecordRepository;
import piggy.bank.repository.AccountRepository;
import piggy.bank.repository.UserRepository;


@Controller
public class DashboardController extends AppController {

    @Autowired
    AccountRepository accountRepository;


    @GetMapping({"/dashboard"})
    public String show(Model model) {

        AccountHistoryRecordRepository accountHistoryRecordRepository = new AccountHistoryRecordRepository();

        int size = accountHistoryRecordRepository.size();

        User user = getLoggedUser();

        model.addAttribute("username", user.getFirstName());
        model.addAttribute("historyList", accountHistoryRecordRepository.getAll().subList((size - 5), size));
        model.addAttribute("accountList", accountRepository.getAllByUser(user));
        return "pages/dashboard";
    }
}
