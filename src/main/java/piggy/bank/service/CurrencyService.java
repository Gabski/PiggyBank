package piggy.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piggy.bank.repository.CurrencyRepository;


@Service("currencyService")
public class CurrencyService implements CurrencyServiceInterface {

    @Autowired
    private CurrencyRepository currencyRepository;
}