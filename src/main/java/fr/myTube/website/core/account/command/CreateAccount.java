package fr.myTube.website.core.account.command;

import fr.myTube.website.core.account.entities.Account;
import fr.myTube.website.core.account.exceptions.EmailAlreadyExistException;
import fr.myTube.website.core.account.ports.driven.AccountGateway;
import fr.myTube.website.core.account.ports.driven.EmailGateway;
import fr.myTube.website.core.account.ports.driven.KeyProvider;
import fr.myTube.website.core.account.ports.driver.CreateAccountRequest;

public class CreateAccount {

  private final AccountGateway accountGateway;
  private final EmailGateway emailGateway;

  private final KeyProvider keyProvider;

  public CreateAccount(AccountGateway accountGateway, EmailGateway emailGateway, KeyProvider keyProvider) {
    this.accountGateway = accountGateway;
    this.emailGateway = emailGateway;
    this.keyProvider = keyProvider;
  }

  public void execute(CreateAccountRequest request) {
    this.checkIfEmailAlreadyExist(request.email());
    var account = new Account(keyProvider.generateId(), keyProvider.getValidationCode(), request.email(), false, null);
    this.accountGateway.saveAccount(account);
    this.emailGateway.sendValidateEmail(account);
  }
  private void checkIfEmailAlreadyExist(String email) {
    accountGateway.getAccountByEmail(email).ifPresent(account -> {
      throw new EmailAlreadyExistException(account.getEmail());
    });
  }
}
