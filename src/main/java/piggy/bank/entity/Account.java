package piggy.bank.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_at")
    private LocalDate createAt;

    @Column(name = "bank_number")
    private String bankNumber;

    public static Account create(Currency currency) {
        Account account = new Account();
        account.setCurrency(currency);
        account.setBankNumber(Account.randomBankNumber());
        return account;
    }

    public static String randomBankNumber() {
        String start = "PL";
        Random value = new Random();

        int r1 = value.nextInt(10);
        int r2 = value.nextInt(10);
        start += Integer.toString(r1) + Integer.toString(r2);
        int n = 0;
        for (int i = 0; i < 12; i++) {
            n = value.nextInt(10);
            start += Integer.toString(n);
        }
        return start;
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

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }
}