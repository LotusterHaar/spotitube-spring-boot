package oose.dea.lotusterhaar.model;

public class Account {
    private String user;
    private String password;
    private String fullName;

    public Account() {
    }

    public Account(String user, String password) {
        this.user = user;
        this.password = password;
        /*      this.fullName = fullName;*/
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

/*    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }*/
}
