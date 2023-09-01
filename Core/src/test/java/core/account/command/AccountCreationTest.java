package core.account.command;


import fr.myTube.core.account.command.CreateAccount;
import fr.myTube.core.account.entities.Account;
import fr.myTube.core.account.exceptions.EmailAlreadyExistException;
import fr.myTube.core.account.ports.driven.AccountGateway;
import fr.myTube.core.account.ports.driven.EmailGateway;
import fr.myTube.core.account.ports.driven.KeyProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import testUtils.AccountProvider;
import testUtils.RequestProviders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



public class AccountCreationTest extends BaseAccountCommandTest {

  private CreateAccount createAccountCommand;

  @BeforeEach
  void setUp() {
    KeyProvider keyProvider = Mockito.mock(KeyProvider.class);
    emailGateway = Mockito.mock(EmailGateway.class);
    accountGateway = Mockito.mock(AccountGateway.class);
    createAccountCommand = new CreateAccount(accountGateway, emailGateway, keyProvider);
    when(keyProvider.getValidationCode()).thenReturn("123456");
    when(keyProvider.generateId()).thenReturn("accountId");
  }

  @Test
  void A_customer_registration_should_be_persisted() {
    when(accountGateway.getAccountByEmail("email@gmail.com")).thenReturn(Optional.empty());
    var createAccountRequest = RequestProviders.getCreateAccountRequest();
    createAccountCommand.execute(createAccountRequest);
    verify(accountGateway).saveAccount(ArgumentMatchers.eq(AccountProvider.getNewAccount()));
  }

  @Test
  void A_customer_registration_may_fail() {
    when(accountGateway.getAccountByEmail("emailalreadyexist@gmail.com")).thenReturn(Optional.of(AccountProvider.getNewAccount()));
    var createAccountRequest = RequestProviders.getInvalidCreateAccountRequest();
    assertThrows(EmailAlreadyExistException.class, () -> createAccountCommand.execute(createAccountRequest));
    verify(emailGateway, never()).sendValidateEmail(any(Account.class));
  }

  @Test
  void A_email_is_send_to_customer_for_email_validating() {
    when(accountGateway.getAccountByEmail("email@gmail.com")).thenReturn(Optional.empty());
    when(accountGateway.saveAndReturnAccount(any(Account.class))).thenReturn(AccountProvider.getNewAccount());
    var createAccountRequest = RequestProviders.getInvalidCreateAccountRequest();
    createAccountCommand.execute(createAccountRequest);
    verify(emailGateway).sendValidateEmail(any(Account.class));
  }

}
