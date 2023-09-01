package fr.myTube.core.account.ports.driven;

import fr.myTube.core.account.entities.Account;

import java.util.Optional;


public interface AccountGateway {
  void saveAccount(Account accountInfo);
  Account saveAndReturnAccount(Account account);

  Optional<Account> getAccount(String id);
  Optional<Account> getAccountByEmail(String email);
}
