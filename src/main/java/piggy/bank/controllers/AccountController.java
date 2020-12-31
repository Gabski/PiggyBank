package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import piggy.bank.entity.*;
import piggy.bank.repository.UserRepository;
import piggy.bank.service.AccountService;

import javax.validation.Valid;

@Controller
public class AccountController extends AppController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService accountService;

    @GetMapping({"/myaccount"})
    public String myAccountGet(Model model) {

        User user = getLoggedUser();
        model.addAttribute("userType", UserEditType.create(user));
        return "pages/myaccount";
    }


    @PostMapping({"/myaccount"})
    public String myAccountPost(Model model, @ModelAttribute("userType") @Valid UserEditType userEditType, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("alert", Snack.create("Błąd", "Nie zapisano, sprawdź błędy"));
            model.addAttribute("userType", userEditType);
            return "pages/myaccount";
        }

        User user = getLoggedUser();

        user.setFirstName(userEditType.getFirstName());
        user.setLastName(userEditType.getLastName());
        user.setPesel(userEditType.getPesel());
        user.setCity(userEditType.getCity());
        user.setEmail(userEditType.getEmail());
        user.setStreet(userEditType.getStreet());
        user.setAddressNumber(userEditType.getAddressNumber());
        user.setPhoneNumber(userEditType.getPhoneNumber());
        user.setPostal(userEditType.getPostal());
        user.setPostalCode(userEditType.getPostalCode());

        userRepository.save(user);
        userRepository.flush();

        model.addAttribute("alert", Snack.create("Sukces", "Zapisano"));
        model.addAttribute("userType", userEditType);
        return "pages/myaccount";
    }

    @GetMapping(value = "/account/{account}")
    public String showAccount(Model model, @PathVariable Account account) {

        model.addAttribute("account", accountService.getAdapter(account));
        return "pages/account";
    }


}
