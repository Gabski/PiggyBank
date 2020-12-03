package piggy.bank.repository;

import piggy.bank.entity.Application;

import java.util.ArrayList;

public class ApplicationRepository extends AppRepository<Application> {
    public ApplicationRepository() {

        CurrencyRepository currencyRepository = new CurrencyRepository();
        long id = 1;
        this.collection = new ArrayList<>();
        this.collection.add(new Application(id++, "Wniosek o kredyt", (double) 1222233, currencyRepository.getById((long) 1)));
        this.collection.add(new Application(id++, "Wniosek o kredyt inwestycyjny", (double) 1222233, currencyRepository.getById((long) 1)));
    }

    @Override
    public Application getById(Long id) {
        for (Application application : collection) {
            if (application.getId().equals(id)){
                return application;
            }
        }
        return null;
    }
}
