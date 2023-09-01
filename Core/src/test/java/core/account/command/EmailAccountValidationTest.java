package core.account.command;


import fr.myTube.core.account.command.AccountValidation;
import fr.myTube.core.account.entities.Account;
import fr.myTube.core.account.exceptions.WrongValidationCodeException;
import fr.myTube.core.account.ports.driven.AccountGateway;
import fr.myTube.core.account.ports.driver.AccountValidationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import testUtils.AccountProvider;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmailAccountValidationTest extends BaseAccountCommandTest {

  private AccountValidation accountValidation;

  @BeforeEach
  void setUp() {
    accountGateway = Mockito.mock(AccountGateway.class);
    accountValidation = new AccountValidation(accountGateway);
  }

  @Test
  void A_customer_validate_his_account_by_entering_the_code_send_by_email() {
    when(accountGateway.getAccount( "accountId")).thenReturn(Optional.of(AccountProvider.getNewAccount()));
    var request = new AccountValidationRequest("accountId", "123456");
    accountValidation.execute(request);
    verify(accountGateway).saveAccount(ArgumentMatchers.eq(AccountProvider.getValidatedAccount()));
  }

  @Test
  void A_customer_enter_wrong_code() {
    when(accountGateway.getAccount( "accountId")).thenReturn(Optional.of(AccountProvider.getNewAccount()));
    var request = new AccountValidationRequest("accountId", "wrong_code");
    assertThrows(WrongValidationCodeException.class, () ->  accountValidation.execute(request));
    verify(accountGateway, never()).saveAccount(any(Account.class));
  }
}
