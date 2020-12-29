package piggy.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piggy.bank.entity.User;
import piggy.bank.repository.AccountRepository;


@Service("accountService")
public class AccountService implements AccountServiceInterface {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Override
    public Object getAllByUser(User user) {
        return accountRepository.getAllByUser(user);
    }

}