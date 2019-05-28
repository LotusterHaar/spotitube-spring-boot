package oose.dea.lotusterhaar.dao;

import oose.dea.lotusterhaar.model.Account;
import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account,Integer> {

    Account findByUser(String user);

}