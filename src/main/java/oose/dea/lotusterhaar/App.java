//tokengenerator: java.util.UUID;
//system.out.println(UID,randomUID
package oose.dea.lotusterhaar;

import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.persistence.AccountDAO;


public class App {
    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();

        Account newAccount = new Account("anne", "annepass", "Anne de Jong");
        accountDAO.persistAccount(newAccount);

        for (Account account : accountDAO.getAllAccounts()) {
            System.out.println(account);
        }

    }
}