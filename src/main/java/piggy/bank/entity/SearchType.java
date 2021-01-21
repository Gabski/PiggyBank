package piggy.bank.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class SearchType implements Serializable {

    private Float valueFrom;
    private Float valueTo;

    private String description;

    public Float getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(Float valueFrom) {
        this.valueFrom = valueFrom;
    }

    public Float getValueTo() {
        return valueTo;
    }

    public void setValueTo(Float valueTo) {
        this.valueTo = valueTo;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date from;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date to;

    private Account account;


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}