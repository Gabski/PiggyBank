package piggy.bank.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

@Entity
@Table(name = "history")
public class HistoryRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to_id")
    private Account to;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_form_id")
    private Account from;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_at")
    private LocalDate createAt;

    @NumberFormat(pattern = "#.00")
    @Column(name = "value")
    private Float value;

    @NotNull
    @NotEmpty
    @Column(name = "title", length = 165)
    private String title;

    public static HistoryRecord create(Account from, Account to, Float value, String title) {
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setFrom(from);
        historyRecord.setTo(to);
        historyRecord.setValue(value);
        historyRecord.setTitle(title);

        return historyRecord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}