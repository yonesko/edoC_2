package accountServer;

public class AccountServerController implements AccountServerControllerMBean {
    private final AccountServerI accountServer;

    public AccountServerController(AccountServerI accountServer) {
        this.accountServer = accountServer;
    }

    public int getUsers() {
        return accountServer.getUsersCount();
    }

    public int getUsersLimit() {
        return accountServer.getUsersCount();
    }

    public void setUsersLimit(int usersLimit) {
        accountServer.setUsersLimit(usersLimit);
    }
}
