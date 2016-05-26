package main.data.model;

/**
 * Created by gleb on 06.05.16.
 */
public class Client {
    private final Long personalAccount;
    private static Long count = 0L;

    public Long getPersonalAccount() {
        return personalAccount;
    }

    public Client() {
        this.personalAccount = count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return personalAccount.equals(client.personalAccount);

    }

    @Override
    public int hashCode() {
        return personalAccount.hashCode();
    }

    @Override
    public String toString() {
        return "Client{" +
                "personalAccount=" + personalAccount +
                '}';
    }
}
