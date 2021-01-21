package piggy.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import piggy.bank.domain.AuthorityType;
import piggy.bank.entity.*;
import piggy.bank.repository.CurrencyRepository;
import piggy.bank.repository.RoleRepository;
import piggy.bank.repository.UserDetailsRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StaticPagesController extends AppController {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @GetMapping({"/"})
    public String home(Model model) {
        return "pages/home";
    }

    @GetMapping({"/contact"})
    public String contact(Model model) {
        return "pages/contact";
    }

    @GetMapping({"/register"})
    public String register(Model model) {

        User user = new User();
        Currency currency = currencyRepository.findBySlug("FSE");
        model.addAttribute("registerType", RegisterType.create(user, currency));
        return "pages/register";
    }

    @GetMapping({"/register/demo"})
    public String registerDemo(Model model) {

        RegisterType registerTypeDemo = new RegisterType();
        registerTypeDemo.setCurrency(currencyRepository.findBySlug("PLN"));
        registerTypeDemo.setEmail("adam.nowak@demo.pl");
        registerTypeDemo.setFirstName("Adam");
        registerTypeDemo.setLastName("Nowak");
        registerTypeDemo.setAddressNumber("12");
        registerTypeDemo.setCity("Siedlce");
        registerTypeDemo.setPostal("Siedlce");
        registerTypeDemo.setPostalCode("08-110");
        registerTypeDemo.setPesel("94022708336");
        registerTypeDemo.setPhoneNumber("022708336");
        registerTypeDemo.setStreet("Ogrodowa");
        registerTypeDemo.setPassword("demo");
        registerTypeDemo.setRepeatPassword("demo");
        registerTypeDemo.setUsername("demo");

        model.addAttribute("registerType", registerTypeDemo);
        return "pages/register";
    }


    @PostMapping({"/register/save"})
    public String myAccountPost(Model model, @ModelAttribute("registerType") @Valid RegisterType registerType, BindingResult result) {

        model.addAttribute("registerType", registerType);

        if (result.hasErrors()) {
            model.addAttribute("alert", Snack.create("Błąd", "Nie zapisano, sprawdź błędy"));
            return "pages/register";
        }

        if (!registerType.getPassword().equals(registerType.getRepeatPassword())) {
            model.addAttribute("alert", Snack.create("Błąd", "Hasła nie są identyczne"));
            return "pages/register";
        }

        var testUser = userRepository.findByUsernameOrEmail(registerType.getUsername());
        if (testUser != null) {
            model.addAttribute("alert", Snack.create("Błąd", "Użytkownik taki już istnieje zmień nazwę użytkownika"));
            return "pages/register";
        }

        testUser = userRepository.findByUsernameOrEmail(registerType.getEmail());
        if (testUser != null) {
            model.addAttribute("alert", Snack.create("Błąd", "Użytkownik taki już istnieje zmień email"));
            return "pages/register";
        }


        User user = new User();
        UserDetails userDetails = new UserDetails();

        var dc = new BCryptPasswordEncoder();
        var password = dc.encode(registerType.getPassword());

        userDetails.setFirstName(registerType.getFirstName());
        userDetails.setLastName(registerType.getLastName());
        userDetails.setPesel(registerType.getPesel());
        userDetails.setCity(registerType.getCity());
        user.setEmail(registerType.getEmail());
        userDetails.setStreet(registerType.getStreet());
        userDetails.setAddressNumber(registerType.getAddressNumber());
        userDetails.setPhoneNumber(registerType.getPhoneNumber());
        userDetails.setPostal(registerType.getPostal());
        userDetails.setPostalCode(registerType.getPostalCode());
        user.setPassword(password);
        user.setUsername(registerType.getUsername());
        user.setRole(roleRepository.findByName(AuthorityType.ROLE_USER.name()));

        user.setUserDetails(userDetails);
        user.addAccount(Account.create(registerType.getCurrency()));

        userDetailsRepository.save(userDetails);
        userRepository.save(user);
        userRepository.flush();

        model.addAttribute("alert", Snack.create("Sukces", "Zapisano"));
        model.addAttribute("registerType", new RegisterType());
        return "pages/register";
    }

    @ModelAttribute("typesOfCurrency")
    public List<Currency> loadCurrencyType() {
        return currencyRepository.findAll();
    }
}
