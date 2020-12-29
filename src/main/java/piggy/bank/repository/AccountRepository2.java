package piggy.bank.repository;

import piggy.bank.entity.Account2;

import java.util.ArrayList;

public class AccountRepository2 extends AppRepository<Account2> {
    public AccountRepository2() {
        long id = 1;
        this.collection = new ArrayList<>();
        this.collection.add(new Account2(id++, "Konto główne", "PL11 1233 2344 1233 1233 4223 4322"));
        this.collection.add(new Account2(id++, "Konto oszczędnościowe", "PL11 6533 2344 1233 5633 4223 1232"));
        this.collection.add(new Account2(id++, "Konto oszczędnościowe", "PL11 6533 2344 1233 5633 4223 1232"));
    }

    @Override
    public Account2 getById(Long id) {
        for (Account2 account : collection) {
            if (account.getId().equals(id))
                return account;
        }
        return null;
    }
}
