package models;

public class DBCred {
    private String name;
    private String pass;
    private String user;

    public DBCred() {
        name = "";
        pass = "";
        user = "";
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getUser() {
        return user;
    }

    public DBCred(String name, String pass, String user) {

        this.name = name;
        this.pass = pass;
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DBCred{");
        sb.append("name='").append(name).append('\'');
        sb.append(", pass='").append(pass).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
