package piggy.bank.adapter;

import piggy.bank.entity.Account;
import piggy.bank.entity.HistoryRecord;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter {

    private final Account account;

    private List<HistoryRecord> history;

    public AccountAdapter(Account account, List<HistoryRecord> history) {
        this.account = account;
        this.history = history;
    }

    public Account getAccount() {
        return account;
    }

    public List<HistoryRecord> getHistory() {
        return history;
    }

    public float getBalance() {

        float sum = 0;

        for (HistoryRecord row : history) {
            if (row.getFrom().equals(account))
                sum -= row.getValue();
            else
                sum += row.getValue();
        }

        return sum;
    }
}
