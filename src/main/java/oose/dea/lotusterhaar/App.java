//tokengenerator: java.util.UUID;
//system.out.println(UID,randomUID
package oose.dea.lotusterhaar;

import oose.dea.lotusterhaar.domain.Account;
import oose.dea.lotusterhaar.domain.Track;
import oose.dea.lotusterhaar.domain.TracksOverview;
import oose.dea.lotusterhaar.persistence.AccountDAO;
import oose.dea.lotusterhaar.persistence.PlaylistDAO;


public class App {
    public static void main(String[] args) {

        AccountDAO accountDAO = new AccountDAO();
/*

        Account newAccount = new Account("anne", "annepass", "Anne de Jong");
        accountDAO.persistAccount(newAccount);
*/
        for (Account account : accountDAO.getAllAccounts()) {
            System.out.println(account);
        }

        PlaylistDAO playlistDAO = new PlaylistDAO();

        TracksOverview tracksOverview = playlistDAO.getSongsTracksInPlaylist(1);
        for (Track track : tracksOverview.getTracks()) {
            System.out.println(track);
        }

    }
}