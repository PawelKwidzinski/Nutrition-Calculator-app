package pl.kwidzinski.caloriecalculator.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegistration {

    @NotEmpty
    @Size(min = 4)
    private String username;

    @NotEmpty
    @Size(min = 6, max = 100)
    private String password;

    private String passwordConfirm;

    public boolean arePasswordEqual() {
        return password.equals(passwordConfirm);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(final String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
