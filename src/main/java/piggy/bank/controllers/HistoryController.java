package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import piggy.bank.adapter.AccountAdapterCollection;
import piggy.bank.entity.User;
import piggy.bank.service.AccountService;


@Controller
public class HistoryController extends AppController {

    @Autowired
    AccountService accountService;

    @GetMapping({"/history"})
    public String show(Model model) {


        User user = getLoggedUser();
        AccountAdapterCollection accountAdapterList = accountService.getAdaptersList(user.getAccounts());

        model.addAttribute("historyList", accountAdapterList.getAllHistory());
        model.addAttribute("sum",accountAdapterList.getBalance());
        return "pages/history";
    }
}
