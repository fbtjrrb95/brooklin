package me.screw.brooklin.request;



public class RegisterRequest {
    private String accountId;
    private String accountPassword;

    // TODO: 이거 왜 NoArgsConstructor가 필요한거지?
    // 이거랑 databind error가 나는걸까?
    public RegisterRequest() {
        super();
    }
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
