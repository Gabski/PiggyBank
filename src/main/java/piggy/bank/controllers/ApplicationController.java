package piggy.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import piggy.bank.entity.Application;
import piggy.bank.entity.Currency2;
import piggy.bank.repository.ApplicationRepository;
import piggy.bank.repository.CurrencyRepository2;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class ApplicationController extends AppController {
    @GetMapping({"/application"})
    public String showApplicationList(Model model) {
        ApplicationRepository applicationRepository = new ApplicationRepository();

        model.addAttribute("applicationList", applicationRepository.getAll());
        return "pages/application";
    }

    @GetMapping("/application/show")
    public String showSingleApplication(Model model, Optional<Long> id) {
        ApplicationRepository applicationRepository = new ApplicationRepository();


        model.addAttribute("document", applicationRepository.getById(id.get()));
        return "application/show";
    }


    @ModelAttribute("typesOfCurrency")
    public List<Currency2> loadCurrencyType() {
        CurrencyRepository2 currencyRepository = new CurrencyRepository2();
        return currencyRepository.getAll();
    }

    @GetMapping(value = "/application/document", params = {"id"})
    public String newApplication(Model model, Optional<Long> id) {
        if (id.isPresent()) {
            ApplicationRepository applicationRepository = new ApplicationRepository();
            var application = applicationRepository.getById(id.get());
            model.addAttribute("document", application);
        } else {
            var application = new Application();
            application.setName("Wniosek o udzielenie kredytu");
            model.addAttribute("document", application);
        }
        return "application/form";
    }


    @PostMapping("/application/send")
    public String saveBook(@ModelAttribute("document") @Valid Application application, BindingResult result) {
        ApplicationRepository applicationRepository = new ApplicationRepository();

        if (result.hasErrors()) {
            return "application/form";
        }

        if (application.getId() == null) {
            applicationRepository.insert(application);
        } else {
            applicationRepository.update(application);
        }
        return "application/show";
    }

}
