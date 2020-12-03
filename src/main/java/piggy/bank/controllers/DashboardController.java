package piggy.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piggy.bank.repository.AccountHistoryRecordRepository;
import piggy.bank.repository.AccountRepository;

import java.util.Optional;


@Controller
public class DashboardController extends AppController {
    @GetMapping({"/"})
    public String show(Model model) {

        AccountRepository accountRepository = new AccountRepository();
        AccountHistoryRecordRepository accountHistoryRecordRepository = new AccountHistoryRecordRepository();

        int size = accountHistoryRecordRepository.size();
        model.addAttribute("historyList", accountHistoryRecordRepository.getAll().subList((size - 5), size));
        model.addAttribute("accountList", accountRepository.getAll());
        return "pages/dashboard";
    }
}
