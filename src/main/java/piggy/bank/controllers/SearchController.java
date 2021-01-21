package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import piggy.bank.adapter.AccountAdapterCollection;
import piggy.bank.domain.AuthorityType;
import piggy.bank.entity.*;
import piggy.bank.repository.CurrencyRepository;
import piggy.bank.repository.HistoryRecordRepository;
import piggy.bank.service.AccountService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@Controller
public class SearchController extends AppController {

    @Autowired
    AccountService accountService;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    HistoryRecordRepository historyRecordRepository;

    @GetMapping({"/search"})
    public String show(Model model) {

        User user = getLoggedUser();
        AccountAdapterCollection accountAdapterList = accountService.getAdaptersList(user.getAccounts());

        var searchType = new SearchType();
        searchType.setValueFrom((float) 0.0);
        searchType.setValueTo((float) 10000.0);
        searchType.setFrom( new Date()) ;
        searchType.setTo(new Date());
        searchType.setAccount(accountAdapterList.getAccounts().get(0).getAccount());
        model.addAttribute("accountList", accountAdapterList.getAccounts());
        model.addAttribute("searchType", searchType);
        return "pages/search";
    }

    @PostMapping({"/search"})
    public String myAccountPost(Model model, @ModelAttribute("searchType") @Valid SearchType searchType, BindingResult result) {

        User user = getLoggedUser();
        AccountAdapterCollection accountAdapterList = accountService.getAdaptersList(user.getAccounts());

        model.addAttribute("accountList", accountAdapterList.getAccounts());
        model.addAttribute("searchType", searchType);

        if (result.hasErrors()) {
            model.addAttribute("alert", Snack.create("Błąd", "Nie wszyszukano, sprawdź błędy"));
            return "pages/search";
        }

        System.out.println(searchType.getAccount());

        var list = historyRecordRepository.searchByAccount(
                searchType.getAccount(),
                searchType.getFrom(),
                searchType.getTo(),
                searchType.getValueFrom(),
                searchType.getValueTo()
//             searchType.getDescription()
        );

        model.addAttribute("historyList", list);
        model.addAttribute("alert", Snack.create("Sukces", "Wyszukano"));
        return "pages/search";
    }

    @ModelAttribute("typesOfCurrency")
    public List<Currency> loadCurrencyType() {
        return currencyRepository.findAll();
    }
}
