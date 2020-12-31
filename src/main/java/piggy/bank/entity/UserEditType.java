package piggy.bank.entity;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserEditType implements Serializable {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "^[0-9]{11}$")
    private String pesel;
    @NotNull
    @NotEmpty
    private String street;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String postal;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9]{2}[-][0-9]{3}$")
    private String postalCode;

    @NotNull
    @NotEmpty
    private String addressNumber;

    public static UserEditType create(User user) {
        var userEditType = new UserEditType();
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

        return userEditType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }
}