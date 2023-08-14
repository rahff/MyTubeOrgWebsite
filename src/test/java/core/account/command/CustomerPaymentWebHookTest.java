package core.account.command;

import fr.myTube.website.core.account.command.CustomerPaymentWebHook;
import fr.myTube.website.core.account.entities.Account;
import fr.myTube.website.core.account.event.AccountSubscriptionFailed;
import fr.myTube.website.core.account.event.AccountSubscriptionValidated;
import fr.myTube.website.core.account.ports.driven.AccountGateway;
import fr.myTube.website.core.shared.EventEmitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import fr.myTube.website.core.account.ports.driver.CustomerPaymentWebHookNotification;
import testUtils.AccountProvider;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerPaymentWebHookTest extends BaseAccountCommandTest {

  private CustomerPaymentWebHook paymentWebHook;
  private EventEmitter eventBus;

  @BeforeEach
  void setUp(){
    accountGateway = Mockito.mock(AccountGateway.class);
    eventBus = Mockito.mock(EventEmitter.class);
    paymentWebHook = new CustomerPaymentWebHook(eventBus, accountGateway);
    when(accountGateway.getAccount("accountId")).thenReturn(Optional.of(AccountProvider.getValidatedAccountWithUnPaidSubscription()));
  }
  @Test
  void A_third_party_payment_gateway_successfully_receive_customer_subscription_payment(){
    var notification = new CustomerPaymentWebHookNotification(true, "accountId");
    paymentWebHook.execute(notification);
    verify(eventBus).emit(any(AccountSubscriptionValidated.class));
    verify(accountGateway).saveAccount(eq(AccountProvider.getValidatedAccountWithPaidSubscription()));
  }

  @Test
  void A_third_party_payment_gateway_fail_subscription_payment(){
    var notification = new CustomerPaymentWebHookNotification(false, "accountId");
    paymentWebHook.execute(notification);
    verify(eventBus, never()).emit(any(AccountSubscriptionValidated.class));
    verify(eventBus).emit(any(AccountSubscriptionFailed.class));
    verify(accountGateway, never()).saveAccount(any(Account.class));
  }
}
