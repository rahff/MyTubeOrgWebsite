package core.account.command;


import fr.myTube.core.account.command.PaymentSubscriptionWebHook;
import fr.myTube.core.account.entities.Account;
import fr.myTube.core.account.event.PaymentSubscriptionEvent;
import fr.myTube.core.account.event.PaymentSubscriptionFailed;
import fr.myTube.core.account.event.PaymentSubscriptionValidated;
import fr.myTube.core.account.ports.driven.AccountGateway;
import fr.myTube.core.account.ports.driver.CustomerPaymentWebHookNotification;
import fr.myTube.core.shared.EventEmitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import testUtils.AccountProvider;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentSubscriptionWebHookTest extends BaseAccountCommandTest {

  private PaymentSubscriptionWebHook paymentWebHook;
  private EventEmitter<PaymentSubscriptionEvent> eventBus;

  @BeforeEach
  void setUp(){
    accountGateway = Mockito.mock(AccountGateway.class);
    eventBus = Mockito.mock(EventEmitter.class);
    paymentWebHook = new PaymentSubscriptionWebHook(eventBus, accountGateway);
    when(accountGateway.getAccount("accountId")).thenReturn(Optional.of(AccountProvider.getValidatedAccountWithUnPaidSubscription()));
  }
  @Test
  void A_third_party_payment_gateway_successfully_receive_customer_subscription_payment(){
    var notification = new CustomerPaymentWebHookNotification(true, "accountId");
    paymentWebHook.execute(notification);
    verify(eventBus).emit(any(PaymentSubscriptionValidated.class));
    verify(accountGateway).saveAccount(ArgumentMatchers.eq(AccountProvider.getValidatedAccountWithPaidSubscription()));
  }

  @Test
  void A_third_party_payment_gateway_fail_subscription_payment(){
    var notification = new CustomerPaymentWebHookNotification(false, "accountId");
    paymentWebHook.execute(notification);
    verify(eventBus, never()).emit(any(PaymentSubscriptionValidated.class));
    verify(eventBus).emit(any(PaymentSubscriptionFailed.class));
    verify(accountGateway, never()).saveAccount(any(Account.class));
  }
}
