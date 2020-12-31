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
public class AdminController extends AppController {


    @GetMapping({"/admin/test"})
    public String home(Model model) {


        return "pages/cash";
    }
}

