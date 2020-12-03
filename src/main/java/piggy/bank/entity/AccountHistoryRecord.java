package piggy.bank.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

public class AccountHistoryRecord extends Entity {


    private String name;

    @NumberFormat(pattern = "#.00")
    private Float value;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public AccountHistoryRecord(Long id, String name, Float value) {
        this.name = name;
        this.id = id;
        this.value = value;
        this.date = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
