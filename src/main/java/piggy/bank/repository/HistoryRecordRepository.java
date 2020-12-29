package piggy.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import piggy.bank.entity.Account;
import piggy.bank.entity.HistoryRecord;
import piggy.bank.entity.Role;

import java.util.List;
import java.util.Set;

public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, Long> {
    @Query("SELECT r FROM HistoryRecord r WHERE r.to=:account OR r.from=:account")
    List<HistoryRecord> findByAccount(Account account);
}