package fr.myTube.website.core.account.ports.driven;

import fr.myTube.website.core.account.entities.Account;

public interface EmailGateway {
  void sendActivationKey(Account account);
  void sendFailureNotification(Account account);
  void sendValidateEmail(Account account);
}
