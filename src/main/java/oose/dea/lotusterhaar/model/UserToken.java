package oose.dea.lotusterhaar.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable=false, unique=true)
    private String token;

    @Column(nullable=false)
    private String accountUser;

    @Column(nullable=false)
    private LocalDateTime expiryDate;


    public UserToken(String accountUser){
        super();
        this.accountUser = accountUser;
    }

    public UserToken(String token, String accountUser, LocalDateTime expiryDate) {
        super();
        this.token = token;
        this.accountUser = accountUser;
        this.expiryDate = expiryDate;
    }

    public String getToken() {
        return token;
    }

    public String getAccountUser() {
        return accountUser;
    }


    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }
}
