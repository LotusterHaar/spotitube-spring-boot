//tokengenerator: java.util.UUID;
//system.out.println(UID,randomUID
package oose.dea.lotusterhaar;

import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.persistence.AccountDAO;


public class App {
    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        Account newAccount = new Account("piet", "pietpass");
        accountDAO.persistAccount(newAccount);
        Account newAccount2 = new Account("uwe", "uwepass");
        accountDAO.persistAccount(newAccount2);
        Account newAccount3 = new Account("jan", "janpass");
        accountDAO.persistAccount(newAccount3);

        for (Account account : accountDAO.getAllAccounts()) {
            System.out.println(account);
        }

    }
}