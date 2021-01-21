package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piggy.bank.adapter.AccountAdapterCollection;
import piggy.bank.entity.User;
import piggy.bank.repository.AccountRepository;
import piggy.bank.repository.HistoryRecordRepository;
import piggy.bank.service.AccountService;


@Controller
public class DashboardController extends AppController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HistoryRecordRepository historyRecordRepository;

    @Autowired
    AccountService accountService;

    @GetMapping({"/dashboard"})
    public String show(Model model) {

        User user = getLoggedUser();
        AccountAdapterCollection accountAdapterList = accountService.getAdaptersList(user.getAccounts());

        model.addAttribute("user", user.getUserDetails().getFirstName());
        model.addAttribute("historyList", accountAdapterList.getAllHistory());
        model.addAttribute("accountList", accountAdapterList.getAccounts());
        return "pages/dashboard";
    }
}
