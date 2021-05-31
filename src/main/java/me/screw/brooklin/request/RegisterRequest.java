package me.screw.brooklin.request;

public class RegisterRequest {
    private String accountId;
    private String accountPassword;

    public RegisterRequest(String accountId, String accountPassword) {
        this.accountId = accountId;
        this.accountPassword = accountPassword;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountPassword() {
        return accountPassword;
    }
}
