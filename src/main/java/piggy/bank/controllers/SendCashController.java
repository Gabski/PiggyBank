package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import piggy.bank.adapter.AccountAdapter;
import piggy.bank.entity.HistoryRecord;
import piggy.bank.entity.SendCashType;
import piggy.bank.entity.User;
import piggy.bank.repository.HistoryRecordRepository;
import piggy.bank.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SendCashController extends AppController {

    @Autowired
    AccountService accountService;

    @Autowired
    HistoryRecordRepository historyRecordRepository;

    @GetMapping({"/przelew"})
    public String home(Model model) {

        model.addAttribute("form", new SendCashType());
        return "pages/cash";
    }

    @PostMapping("/send")
    public String saveBook(@ModelAttribute("form") @Valid SendCashType sendCashType, BindingResult result) {


        if (result.hasErrors()) {
            return "pages/cash";
        }

        var account = loadMyAccounts().get(0).getAccount();
        historyRecordRepository.save(HistoryRecord.create(account, account, sendCashType.getValue(), sendCashType.getTitle()));
        return "pages/cash";
    }

    @ModelAttribute("myAccounts")
    public List<AccountAdapter> loadMyAccounts() {
        User user = getLoggedUser();
        return accountService.getAdaptersList(user.getAccounts()).getAccounts();
    }
}

