package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piggy.bank.adapter.AccountAdapter;
import piggy.bank.entity.User;
import piggy.bank.repository.AccountHistoryRecordRepository;
import piggy.bank.repository.AccountRepository;
import piggy.bank.repository.HistoryRecordRepository;
import piggy.bank.service.AccountService;

import java.util.List;


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

        AccountHistoryRecordRepository accountHistoryRecordRepository = new AccountHistoryRecordRepository();

        int size = accountHistoryRecordRepository.size();

        User user = getLoggedUser();

        List<AccountAdapter> accountAdapterList = accountService.getAdaptersList(user.getAccounts());

        model.addAttribute("user", user.getFirstName());
        model.addAttribute("historyList", accountAdapterList.get(0).getHistory());
        model.addAttribute("accountList", accountAdapterList);
        return "pages/dashboard";
    }
}
