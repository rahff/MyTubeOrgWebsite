package fr.myTube.core.account.command;

import fr.myTube.core.account.ports.driven.AccountGateway;
import fr.myTube.core.account.ports.driven.SubscriptionGateway;
import fr.myTube.core.account.ports.driver.SubscriptionPlanRequest;

public class AccountSubscription {
  private final SubscriptionGateway subscriptionGateway;
  private final AccountGateway accountGateway;
  public AccountSubscription(SubscriptionGateway subscriptionGateway, AccountGateway accountGateway) {
    this.subscriptionGateway = subscriptionGateway;
    this.accountGateway = accountGateway;
  }

  public void execute(SubscriptionPlanRequest request){
    var account = accountGateway.getAccount(request.accountId()).orElseThrow(RuntimeException::new);
    var subscription = subscriptionGateway.initSubscription(request);
    account.addCustomerSubscription(subscription);
    accountGateway.saveAccount(account);
  }
}
