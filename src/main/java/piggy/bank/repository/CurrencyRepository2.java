package piggy.bank.repository;

import piggy.bank.entity.Currency2;

import java.util.ArrayList;

public class CurrencyRepository2 extends AppRepository<Currency2> {
    public CurrencyRepository2() {
        long id = 1;
        this.collection = new ArrayList<>();
        this.collection.add(new Currency2(id++, "Złoty polski", "PLN"));
    }

    @Override
    public Currency2 getById(Long id) {
        for (Currency2 currency : collection) {
            if (currency.getId().equals(id))
                return currency;
        }
        return null;
    }
}
