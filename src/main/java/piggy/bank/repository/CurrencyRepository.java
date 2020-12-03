package piggy.bank.repository;

import piggy.bank.entity.Currency;

import java.util.ArrayList;

public class CurrencyRepository extends AppRepository<Currency> {
    public CurrencyRepository() {
        long id = 1;
        this.collection = new ArrayList<>();
        this.collection.add(new Currency(id++, "Złoty polski", "PLN"));
    }

    @Override
    public Currency getById(Long id) {
        for (Currency currency : collection) {
            if (currency.getId().equals(id))
                return currency;
        }
        return null;
    }
}
