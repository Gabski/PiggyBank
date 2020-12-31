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
import piggy.bank.entity.*;
import piggy.bank.repository.AccountRepository;
import piggy.bank.repository.HistoryRecordRepository;
import piggy.bank.service.AccountService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TransferController extends AppController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HistoryRecordRepository historyRecordRepository;

    @GetMapping({"/transfer/{account}"})
    public String home(Model model, @PathVariable Account account) {

        var transferType = new TransferType();
        transferType.setFrom(account);
        transferType.setTitle("Standardowy przelew pieniędzy");

        model.addAttribute("account", accountService.getAdapter(account));
        model.addAttribute("form", transferType);
        return "pages/cash";
    }


    @PostMapping("/transfer/send")
    public String saveBook(Model model, @ModelAttribute("form") @Valid TransferType transferType, BindingResult result) {

        model.addAttribute("account", accountService.getAdapter(transferType.getFrom()));

        if (result.hasErrors()) {
            model.addAttribute("form", transferType);
            return "pages/cash";
        }

        var accountTo = accountRepository.findByName(transferType.getTo());

        if (accountTo == null) {
            accountTo = Account.create(transferType.getFrom().getCurrency(), transferType.getTo());
            accountRepository.saveAndFlush(accountTo);
        }

        historyRecordRepository.saveAndFlush(HistoryRecord.create(
                transferType.getFrom(),
                accountTo,
                transferType.getValue(),
                transferType.getTitle())
        );


        model.addAttribute("alert", Snack.create("Wysłano", "Wysłano przelew"));
        model.addAttribute("form", new TransferType());
        return "pages/cash";
    }

    @ModelAttribute("myAccounts")
    public List<AccountAdapter> loadMyAccounts() {
        User user = getLoggedUser();
        return accountService.getAdaptersList(user.getAccounts()).getAccounts();
    }
}

