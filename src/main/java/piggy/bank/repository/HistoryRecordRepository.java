package piggy.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import piggy.bank.entity.Account;
import piggy.bank.entity.HistoryRecord;
import piggy.bank.entity.Role;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, Long> {
    @Query("SELECT r FROM HistoryRecord r WHERE r.to=:account OR r.from=:account ORDER BY r.id desc")
    List<HistoryRecord> findByAccount(Account account);

    @Query("SELECT r FROM HistoryRecord r WHERE (r.to=:account OR r.from=:account) AND (r.value BETWEEN :valueFrom AND :valueTo) AND (r.createAt BETWEEN :from AND :to) ORDER BY r.id desc")
    List<HistoryRecord> searchByAccount(
             Account account,
             @Param("from") Date from,
             @Param("to") Date to,
             @Param("valueFrom") Float valueFrom,
             @Param("valueTo") Float valueTo
//             @Param("description") String description
            );
}