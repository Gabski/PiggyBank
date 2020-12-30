package piggy.bank.adapter;

import piggy.bank.entity.HistoryRecord;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapterCollection {

    private final List<AccountAdapter> accounts;

    public AccountAdapterCollection() {
        accounts = new ArrayList<>();
    }

    public List<AccountAdapter> getAccounts() {
        return accounts;
    }

    public void add(AccountAdapter accountAdapter) {
        accounts.add(accountAdapter);
    }

    public List<HistoryRecord> getAllHistory() {

        List<HistoryRecord> historyRecordList = new ArrayList<>();

        for (AccountAdapter account : accounts) {
            historyRecordList.addAll(account.getHistory());
        }

        return historyRecordList;
    }

    public float getBalance() {
        float sum = 0;
        for (AccountAdapter account : accounts) {
            sum += account.getBalance();
        }
        return sum;
    }
}
