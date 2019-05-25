package oose.dea.lotusterhaar.dao;

import oose.dea.lotusterhaar.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TokenDAO {

    @Autowired
    ConnectionFactory connectionFactory;

    public TokenDAO() {
    }

    public UserToken getUserToken(String inputToken) {
        UserToken userToken = new UserToken();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM spotitube.token WHERE token = ?")
        ) {
            statement.setString(1, inputToken);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("account_user");
                String token = resultSet.getString("token");
                userToken.setUser(name);
                userToken.setToken(token);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userToken;
    }

    public UserToken createNewUserToken(String user) {

        UserToken userToken = new UserToken();
        userToken.setUser(user);

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO spotitube.token (token, account_user, expiry_date) VALUES (?,?,?) ")
        ) {
            deleteUserToken(userToken);
            UUID uuid = UUID.randomUUID();
            String token = uuid.toString();
            String expiryDate = LocalDateTime.now().plusHours(10).toString();
            statement.setString(1, token);
            statement.setString(2, user);
            statement.setString(3, expiryDate);
            statement.execute();
            userToken = new UserToken(token, user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userToken;
    }


    public boolean tokenExpired(UserToken userToken) {
        String expiryDateString = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT expiry_date FROM spotitube.token where token = ? AND account_user = ?")
        ) {
            statement.setString(1, userToken.getToken());
            statement.setString(2, userToken.getUser());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                expiryDateString = resultSet.getString("expiry_date");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime expiryDate = LocalDateTime.parse(expiryDateString, formatter);
            LocalDateTime currentDateTime = LocalDateTime.now();
            if (expiryDate.isAfter(currentDateTime)) {
                return false;
            } else {
                deleteUserToken(userToken);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserToken(UserToken userToken) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE from spotitube.token WHERE account_user = ?");
        ) {
            statement.setString(1, userToken.getUser());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
