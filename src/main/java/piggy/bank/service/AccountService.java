package piggy.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piggy.bank.repository.AccountRepository;
import piggy.bank.repository.CurrencyRepository;


@Service("accountService")
public class AccountService implements AccountServiceInterface {

    @Autowired
    private AccountRepository accountRepository;
}