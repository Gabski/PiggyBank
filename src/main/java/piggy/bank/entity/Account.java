package piggy.bank.entity;

public class Account extends Entity{

    private String name;

    private String number;

    public Account(Long id, String name, String number) {
        this.name = name;
        this.id = id;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}