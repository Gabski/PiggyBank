package piggy.bank.service;

import piggy.bank.entity.AccountHistoryRecord;

import java.util.List;

public class HistoryService {



    static public float getSum(List<AccountHistoryRecord> records) {
        float sum = 0;

        for (AccountHistoryRecord record : records) {
            sum += record.getValue();
        }

        return sum;
    }

}
