package piggy.bank.repository;

import piggy.bank.entity.Account;
import piggy.bank.entity.AccountHistoryRecord;

import java.util.ArrayList;

public class AccountHistoryRecordRepository extends AppRepository<AccountHistoryRecord> {

    public AccountHistoryRecordRepository() {
        long id = 1;
        this.collection = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            collection.add(new AccountHistoryRecord(id++, "testowe" + i, (float) 123.12 +  i));
        }
    }

    @Override
    public AccountHistoryRecord getById(Long id) {
        for (AccountHistoryRecord accountHistoryRecord : collection) {
            if (accountHistoryRecord.getId().equals(id))
                return accountHistoryRecord;
        }
        return null;
    }
}
