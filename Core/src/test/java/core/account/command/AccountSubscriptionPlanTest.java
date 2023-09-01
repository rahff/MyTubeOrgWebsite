package core.account.command;


import fr.myTube.core.account.command.AccountSubscription;
import fr.myTube.core.account.ports.driven.AccountGateway;
import fr.myTube.core.account.ports.driven.SubscriptionGateway;
import fr.myTube.core.account.ports.driver.SubscriptionPlanRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import testUtils.AccountProvider;
import testUtils.CustomerSubscriptionProvider;

import java.util.Optional;

import static org.mockito.Mockito.*;



public class AccountSubscriptionPlanTest extends BaseAccountCommandTest {
  private AccountSubscription accountSubscription;
  private SubscriptionGateway subscriptionGateway;

  @BeforeEach
  void setUp() {
    accountGateway = Mockito.mock(AccountGateway.class);
    subscriptionGateway = Mockito.mock(SubscriptionGateway.class);
    accountSubscription = new AccountSubscription(subscriptionGateway, accountGateway);
  }

  @Test
  void A_customer_choose_a_subscription_plan() {
    when(accountGateway.getAccount("123")).thenReturn(Optional.of(AccountProvider.getValidatedAccount()));
    var request = new SubscriptionPlanRequest("123");
    accountSubscription.execute(request);
    when(subscriptionGateway.initSubscription(request)).thenReturn(CustomerSubscriptionProvider.getUnPaidCustomerSubscription());
    verify(subscriptionGateway).initSubscription(request);
    verify(accountGateway).saveAccount(ArgumentMatchers.eq(AccountProvider.getValidatedAccountWithUnPaidSubscription()));
  }
}
