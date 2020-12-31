package piggy.bank.features;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import piggy.bank.domain.AuthorityType;
import piggy.bank.entity.*;
import piggy.bank.repository.*;
import piggy.bank.service.RoleServiceInterface;

@Configuration
public class StartData {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HistoryRecordRepository historyRecordRepository;

    @Bean
    InitializingBean init(RoleServiceInterface roleService) {
        return () -> {
            insertCurrency();
            insertRole();
            insertUsers(roleService);
            // insertAccounts();
        };
    }

    private void insertCurrency() {
        if (currencyRepository.findAll().isEmpty()) {
            currencyRepository.save(Currency.create("Złoty polski", "PLN"));
            currencyRepository.save(Currency.create("Dolar Amerykański", "USD"));
            currencyRepository.save(Currency.create("Euro", "EUR"));
            currencyRepository.save(Currency.create("Funt brytyjski", "GBP"));
            currencyRepository.save(Currency.create("Frank szwajcarski", "CHF"));
            currencyRepository.save(Currency.create("Ruble", "RUB"));
            currencyRepository.save(Currency.create("Dolar australijski", "AUD"));
            currencyRepository.save(Currency.create("Frank San Eskobarski", "FSE"));
            currencyRepository.flush();
        }
    }

    private void insertRole() {
        if (roleRepository.findAll().isEmpty()) {

            Role roleUser = Role.create(AuthorityType.ROLE_USER.name());
            Role roleAdmin = Role.create(AuthorityType.ROLE_ADMIN.name());

            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            roleRepository.flush();
        }
    }

    private void insertUsers(RoleServiceInterface roleService) {
        if (userRepository.findAll().isEmpty()) {

            Role roleUser = roleService.getUserRole();
            Role roleAdmin = roleService.getAdminRole();

            Currency currency = currencyRepository.findBySlug("PLN");
            Currency currency2 = currencyRepository.findBySlug("USD");

            Account account = Account.create(currency);
            Account account2 = Account.create(currency);
            Account account3 = Account.create(currency);
            Account account4 = Account.create(currency2);


            User user = new User();
            user.setPassword(bCryptPasswordEncoder().encode("user"));
            user.setFirstName("Gabriel");
            user.setLastName("Koziestański");
            user.setEmail("gabriel@wp.pl");
            user.setPhoneNumber("123412344");
            user.setCity("Siedlce");
            user.setPostal("Siedlce");
            user.setPesel("94022708335");
            user.setStreet("Polna");
            user.setAddressNumber("12");
            user.setPostalCode("01-122");
            user.setUsername("user");
            user.setRole(roleUser);

            user.addAccount(account);
            user.addAccount(account2);
            user.addAccount(account4);


            User admin = new User();
            admin.setPassword(bCryptPasswordEncoder().encode("admin"));
            admin.setFirstName("Gabriel");
            admin.setLastName("Koziestański");
            admin.setEmail("admin@wp.pl");
            admin.setUsername("admin");
            admin.setRole(roleAdmin);

            admin.addAccount(account3);

            userRepository.save(user);
            userRepository.save(admin);
            userRepository.flush();

            Account bank = Account.create(currency, "PL00000000000000");
            Account bank2 = Account.create(currency2, "PL00000000000001");
            accountRepository.save(bank);
            accountRepository.save(bank2);

            historyRecordRepository.save(HistoryRecord.create(bank2, account4, (float) 55.10, "Na waciki"));
            historyRecordRepository.save(HistoryRecord.create(bank, account, (float) 2605.10, "Wypłata październik"));
            historyRecordRepository.save(HistoryRecord.create(bank, account2, (float) 2400.21, "Wypłata listopad"));
            historyRecordRepository.save(HistoryRecord.create(bank, account, (float) 2100.30, "Wypłata grudzień"));
            historyRecordRepository.save(HistoryRecord.create(account, bank, (float) 605.10, "Opłata za prąd"));
            historyRecordRepository.flush();
        }

    }

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
