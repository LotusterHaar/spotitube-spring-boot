package oose.dea.lotusterhaar.persistence;

import oose.dea.lotusterhaar.domain.Account;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM account");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("full_name");
                accounts.add(new Account(user, password, fullName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public void persistAccount(Account account) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO account(user,password, full_name) VALUES (?,?,?)");
        ) {
            statement.setString(1, account.getUser());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getFullName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account findAccountByUsername(String username) {
        Account account = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE user = ?");
        ) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("full_name");
                account = new Account(user, password, fullName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }


}