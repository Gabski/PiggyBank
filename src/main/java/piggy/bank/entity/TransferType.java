package piggy.bank.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

public class TransferType implements Serializable {

    private String to;

    private Account from;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createAt;

    @Min(1)
    @Max(100000000)
    @NotNull
    @NumberFormat(pattern = "#.00")
    private Float value;

    @NotNull
    @NotEmpty
    private String title;

    @Pattern(regexp = "^PL[0-9]{14}$")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
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