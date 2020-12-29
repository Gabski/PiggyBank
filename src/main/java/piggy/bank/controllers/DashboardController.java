package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piggy.bank.entity.User;
import piggy.bank.repository.AccountHistoryRecordRepository;
import piggy.bank.repository.AccountRepository;
import piggy.bank.repository.HistoryRecordRepository;


@Controller
public class DashboardController extends AppController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HistoryRecordRepository historyRecordRepository;

    @GetMapping({"/dashboard"})
    public String show(Model model) {

        AccountHistoryRecordRepository accountHistoryRecordRepository = new AccountHistoryRecordRepository();

        int size = accountHistoryRecordRepository.size();

        User user = getLoggedUser();

        model.addAttribute("user", user.getFirstName());
        model.addAttribute("historyList", historyRecordRepository.findByAccount(user.getAccounts().iterator().next()));
        model.addAttribute("accountList", user.getAccounts());
        return "pages/dashboard";
    }
}
