package piggy.bank.entity;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterType extends UserEditType {


    private Currency currency;

    @NotNull
    @NotEmpty
    @Size(min=3, max=12)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min=5, max=50)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min=3, max=12)
    private String repeatPassword;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public static RegisterType create(User user, Currency currency) {
        var userEditType = new RegisterType();
        userEditType.setFirstName(user.getFirstName());
        userEditType.setLastName(user.getLastName());
        userEditType.setPesel(user.getPesel());
        userEditType.setCity(user.getCity());
        userEditType.setEmail(user.getEmail());
        userEditType.setStreet(user.getStreet());
        userEditType.setAddressNumber(user.getAddressNumber());
        userEditType.setPhoneNumber(user.getPhoneNumber());
        userEditType.setPostal(user.getPostal());
        userEditType.setPostalCode(user.getPostalCode());
        userEditType.setCurrency(currency);
        userEditType.setUsername(user.getUsername());

        return userEditType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}