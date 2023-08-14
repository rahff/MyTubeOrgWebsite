package fr.myTube.website.core.account.command;

import fr.myTube.website.core.account.event.AccountSubscriptionFailed;
import fr.myTube.website.core.account.event.AccountSubscriptionValidated;
import fr.myTube.website.core.account.ports.driven.AccountGateway;
import fr.myTube.website.core.account.ports.driven.EmailGateway;
import fr.myTube.website.core.shared.EventEmitter;
import fr.myTube.website.core.account.ports.driver.CustomerPaymentWebHookNotification;

public class CustomerPaymentWebHook {
  private final EventEmitter eventBus;
  private final AccountGateway accountGateway;

  public CustomerPaymentWebHook(EventEmitter eventBus, AccountGateway accountGateway){
    this.eventBus = eventBus;
    this.accountGateway = accountGateway;
  }

  public void execute(CustomerPaymentWebHookNotification webHookNotification){
    var account = this.accountGateway.getAccount(webHookNotification.accountId()).orElseThrow(RuntimeException::new);
    if (webHookNotification.success()){
      account.markSubscriptionAsPaid();
      accountGateway.saveAccount(account);
      eventBus.emit(new AccountSubscriptionValidated(account));
    }else {
      eventBus.emit(new AccountSubscriptionFailed(account));
    }
  }
}
