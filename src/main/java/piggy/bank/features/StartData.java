package piggy.bank.features;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import piggy.bank.domain.AuthorityType;
import piggy.bank.entity.Currency;
import piggy.bank.entity.Role;
import piggy.bank.entity.User;
import piggy.bank.repository.CurrencyRepository;
import piggy.bank.repository.RoleRepository;
import piggy.bank.repository.UserRepository;

@Configuration
public class StartData {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean
    InitializingBean init() {
        return () -> {
            insertCurrency();
            insertRole();
            insertUsers();
        };
    }

    private void insertCurrency() {
        if (currencyRepository.findAll().isEmpty()) {
            currencyRepository.save(Currency.initCurrency("Złoty polski", "PLN"));
            currencyRepository.save(Currency.initCurrency("Dolar Amerykański", "USD"));
            currencyRepository.save(Currency.initCurrency("Frank San Eskobarski", "FCK"));
            currencyRepository.flush();
        }
    }

    private void insertRole() {
        if (roleRepository.findAll().isEmpty()) {

            Role roleAdmin = new Role();
            roleAdmin.setRoleName(AuthorityType.ROLE_ADMIN.name());

            Role roleUser = new Role();
            roleUser.setRoleName(AuthorityType.ROLE_USER.name());

            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            roleRepository.flush();
        }
    }

    private void insertUsers() {
        if (userRepository.findAll().isEmpty()) {

            Role roleUser = roleRepository.findByName(AuthorityType.ROLE_USER.name());

            User user = new User();
            user.setPassword(bCryptPasswordEncoder().encode("user"));
            user.setFirstName("Gabriel");
            user.setLastName("Koziestański");
            user.setEmail("gabriel@wp.pl");
            user.setUsername("user");
            user.setRole(roleUser);

            userRepository.save(user);
            userRepository.flush();
        }
    }

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
