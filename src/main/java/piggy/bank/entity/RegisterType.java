package piggy.bank.entity;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterType extends UserEditType {


    private Currency currency;

    @NotNull
    @NotEmpty
    @Size(min=3, max=12, message = "Nie prawidłowa ilość znaków")
    private String password;

    @NotNull
    @NotEmpty
    @Size(min=3, max=50, message = "Nie prawidłowa ilość znaków")
    private String username;

    @NotNull
    @NotEmpty
    @Size(min=3, max=12, message = "Nie prawidłowa ilość znaków")
    private String repeatPassword;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public static RegisterType create(User user, Currency currency) {
        var userEditType = new RegisterType();
        userEditType.setFirstName(user.getUserDetails().getFirstName());
        userEditType.setLastName(user.getUserDetails().getLastName());
        userEditType.setPesel(user.getUserDetails().getPesel());
        userEditType.setCity(user.getUserDetails().getCity());
        userEditType.setEmail(user.getEmail());
        userEditType.setStreet(user.getUserDetails().getStreet());
        userEditType.setAddressNumber(user.getUserDetails().getAddressNumber());
        userEditType.setPhoneNumber(user.getUserDetails().getPhoneNumber());
        userEditType.setPostal(user.getUserDetails().getPostal());
        userEditType.setPostalCode(user.getUserDetails().getPostalCode());
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