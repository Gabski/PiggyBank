package piggy.bank.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegisterType extends UserEditType {

    @NotNull
    @NotEmpty
    private Currency currency;

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

        return userEditType;
    }


}