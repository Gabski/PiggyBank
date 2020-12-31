package piggy.bank.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import piggy.bank.entity.User;
import piggy.bank.repository.UserRepository;

abstract public class AppController {

    @Autowired
    UserRepository userRepository;

    @ModelAttribute("currentUser")
    protected User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return userRepository.findByUsernameOrEmail(name);
    }
}
