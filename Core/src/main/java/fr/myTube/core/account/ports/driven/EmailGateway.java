package fr.myTube.core.account.ports.driven;

import fr.myTube.core.account.entities.Account;

public interface EmailGateway {
  void sendActivationKey(Account account);
  void sendFailureNotification(Account account);
  void sendValidateEmail(Account account);
}
