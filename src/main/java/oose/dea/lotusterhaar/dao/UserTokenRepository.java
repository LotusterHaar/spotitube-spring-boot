package oose.dea.lotusterhaar.dao;

import oose.dea.lotusterhaar.model.UserToken;
import org.springframework.data.repository.CrudRepository;


public interface UserTokenRepository extends CrudRepository<UserToken, Integer> {

    UserToken findByToken(String token);

    void deleteByAccountUser(String accountUser);

    UserToken findByAccountUserAndToken(String accountUser, String token);
}
