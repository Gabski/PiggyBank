package piggy.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piggy.bank.adapter.AccountAdapter;
import piggy.bank.adapter.AccountAdapterCollection;
import piggy.bank.entity.Account;
import piggy.bank.entity.User;
import piggy.bank.repository.AccountRepository;
import piggy.bank.repository.HistoryRecordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service("accountService")
public class AccountService implements AccountServiceInterface {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    HistoryRecordRepository historyRecordRepository;

    public AccountAdapter getAdapter(Account account) {
        return new AccountAdapter(account, historyRecordRepository.findByAccount(account));
    }

    public AccountAdapterCollection getAdaptersList(Set<Account> accounts) {

        AccountAdapterCollection collection = new AccountAdapterCollection();

        var iterator = accounts.iterator();

        while (iterator.hasNext()) {
            Account account = iterator.next();
            collection.add(new AccountAdapter(account, historyRecordRepository.findByAccount(account)));
        }

        return collection;
    }
}