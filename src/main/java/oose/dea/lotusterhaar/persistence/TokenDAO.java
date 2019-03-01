package oose.dea.lotusterhaar.persistence;

public class TokenDAO {


    private ConnectionFactory connectionFactory;

    public TokenDAO() {
        connectionFactory = new ConnectionFactory();

    }

    public boolean tokenExpired(String token) {
        boolean tokenExpired = false;

        return !tokenExpired;
    }


}
