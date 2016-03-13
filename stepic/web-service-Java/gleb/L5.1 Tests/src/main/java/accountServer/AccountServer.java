package accountServer;

public class AccountServer implements AccountServerI {
    private int users;
    private int usersLimit;

    public void addNewUser() {
        users++;
    }

    public void removeUser() {
        users--;
    }

    public int getUsersLimit() {
        return usersLimit;
    }

    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    public AccountServer(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    public int getUsersCount() {
        return users;
    }
}
