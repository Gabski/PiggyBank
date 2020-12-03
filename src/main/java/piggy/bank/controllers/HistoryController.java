package piggy.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import piggy.bank.repository.AccountHistoryRecordRepository;
import piggy.bank.service.HistoryService;


@Controller
public class HistoryController extends AppController {

    @GetMapping({"/history"})
    public String show(Model model) {
        AccountHistoryRecordRepository accountHistoryRecordRepository = new AccountHistoryRecordRepository();

        model.addAttribute("historyList", accountHistoryRecordRepository.getAll());
        model.addAttribute("sum", HistoryService.getSum(accountHistoryRecordRepository.getAll()));
        return "pages/history";
    }
}
