package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import piggy.bank.adapter.AccountAdapter;
import piggy.bank.entity.Account;
import piggy.bank.entity.HistoryRecord;
import piggy.bank.entity.TransferType;
import piggy.bank.entity.User;
import piggy.bank.repository.HistoryRecordRepository;
import piggy.bank.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TransferController extends AppController {

    @Autowired
    AccountService accountService;

    @Autowired
    HistoryRecordRepository historyRecordRepository;

    @GetMapping({"/transfer/{account}"})
    public String home(Model model, @PathVariable Account account) {

        var transferType = new TransferType();
        transferType.setFrom(account.getBankNumber());
        transferType.setTitle("Standardowy przelew pieniędzy");

        model.addAttribute("account", accountService.getAdapter(account));
        model.addAttribute("form", transferType);
        return "pages/cash";
    }


    @PostMapping("/send")
    public String saveBook(@ModelAttribute("form") @Valid TransferType sendCashType, BindingResult result) {


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

