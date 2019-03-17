package oose.dea.lotusterhaar.persistence;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDAO {

    @Inject
    ConnectionFactory connectionFactory;

    public TokenDAO() {
    }

    public boolean tokenExpired(String token) {
        boolean tokenExpired = false;

        return !tokenExpired;
    }

    public String findUsernameByToken(String token) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT token.account_user FROM spotitube.token WHERE token.token = ?")
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            String result = "";
            while (resultSet.next()) {
                result = resultSet.getString("account_user");
            }
            return (result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
