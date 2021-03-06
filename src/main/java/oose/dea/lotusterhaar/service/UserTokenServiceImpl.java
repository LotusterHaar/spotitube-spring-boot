package oose.dea.lotusterhaar.service;

import oose.dea.lotusterhaar.dao.UserTokenRepository;
import oose.dea.lotusterhaar.model.UserToken;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    private UserTokenRepository userTokenRepository;

    public UserTokenServiceImpl(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    public UserToken createNewUserToken(String user) {
        userTokenRepository.deleteByAccountUser(user);
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(10);
        UserToken userToken = new UserToken(token, user, expiryDate);
        userTokenRepository.save(userToken);
        return new UserToken(token, user, expiryDate);
    }

    private UserToken getUserToken(UserToken userToken) {
        return userTokenRepository.findByAccountUserAndToken(userToken.getAccountUser(), userToken.getToken());
    }

    public boolean tokenExpired(UserToken userToken) {
        UserToken newUserToken = getUserToken(userToken);
        LocalDateTime expiryDate = newUserToken.getExpiryDate();
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (expiryDate.isAfter(currentDateTime)) {
            return false;
        } else {
            userTokenRepository.delete(userToken);
            return true;
        }
    }
}
