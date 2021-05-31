package me.screw.brooklin.domain;

public class Account {
    private String accountId;
    private String accountPassword;

    public Account(String accountId, String accountPassword) {
        this.accountId = accountId;
        this.accountPassword = accountPassword;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}
