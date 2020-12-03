package piggy.bank.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

public class Application extends Entity implements Serializable {

    @Min(1000)
    @Max(1000000)
    @NotNull
    @NumberFormat(pattern = "#.00")
    private Double value;

    @Min(6)
    @Max(124)
    @NotNull
    private int lengthOnMonths;

    @NotEmpty
    @Size(min=5, max=120)
    private String name;

    @NotEmpty
    @Size(min=5, max=120)
    private String target;

    @Email
    @NotEmpty
    private String email;

    private Currency currency;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate cratedAt;

    public Application() {
        this.currency = new Currency(1l, "Złoty polski", "PLN");
    }

    public Application(long id, String name, Double value, Currency currency) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.currency = currency;
        this.cratedAt = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDate getCratedAt() {
        return cratedAt;
    }

    public void setCratedAt(LocalDate cratedAt) {
        this.cratedAt = cratedAt;
    }

    public int getLengthOnMonths() {
        return lengthOnMonths;
    }

    public void setLengthOnMonths(int lengthOnMonths) {
        this.lengthOnMonths = lengthOnMonths;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
