package oose.dea.lotusterhaar.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserToken {

    @Column(nullable=false, unique=true)
    private String token;

    @Column(nullable=false)
    private String user;

    public UserToken() {
        super();
    }

    public UserToken(String token, String user) {
        super();
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
