package piggy.bank.repository;

import piggy.bank.entity.Account;
import piggy.bank.entity.AccountHistoryRecord;

import java.util.ArrayList;

public class AccountRepository extends AppRepository<Account> {
    public AccountRepository() {
        long id = 1;
        this.collection = new ArrayList<>();
        this.collection.add(new Account(id++, "Konto główne", "PL11 1233 2344 1233 1233 4223 4322"));
        this.collection.add(new Account(id++, "Konto oszczędnościowe", "PL11 6533 2344 1233 5633 4223 1232"));
        this.collection.add(new Account(id++, "Konto oszczędnościowe", "PL11 6533 2344 1233 5633 4223 1232"));
    }

    @Override
    public Account getById(Long id) {
        for (Account account : collection) {
            if (account.getId().equals(id))
                return account;
        }
        return null;
    }
}
