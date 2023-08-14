package fr.myTube.website.core.account.command;

import fr.myTube.website.core.account.exceptions.WrongValidationCodeException;
import fr.myTube.website.core.account.ports.driven.AccountGateway;
import fr.myTube.website.core.account.ports.driver.AccountValidationRequest;

public class AccountValidation {

  private final AccountGateway accountGateway;

  public AccountValidation(AccountGateway accountGateway) {
    this.accountGateway = accountGateway;
  }

  public void execute(AccountValidationRequest request) {
    var account = accountGateway.getAccount(request.accountId()).orElseThrow(RuntimeException::new);
    checkValidationCode(account.getEmailCodeValidation(), request.code());
    account.validateEmail();
    accountGateway.saveAccount(account);
  }

  private void checkValidationCode(String validationCode, String submittedCode) {
    var codeMatching = validationCode.equals(submittedCode);
    if(!codeMatching) throw new WrongValidationCodeException();
  }
}
