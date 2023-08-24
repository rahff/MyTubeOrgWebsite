package fr.myTube.website.core.account.command;

import fr.myTube.website.core.account.event.PaymentSubscriptionEvent;
import fr.myTube.website.core.account.event.PaymentSubscriptionFailed;
import fr.myTube.website.core.account.event.PaymentSubscriptionValidated;
import fr.myTube.website.core.account.ports.driven.AccountGateway;
import fr.myTube.website.core.shared.EventEmitter;
import fr.myTube.website.core.account.ports.driver.CustomerPaymentWebHookNotification;

public class PaymentSubscriptionWebHook {
  private final EventEmitter<PaymentSubscriptionEvent> eventBus;
  private final AccountGateway accountGateway;

  public PaymentSubscriptionWebHook(EventEmitter<PaymentSubscriptionEvent> eventBus, AccountGateway accountGateway){
    this.eventBus = eventBus;
    this.accountGateway = accountGateway;
  }

  public void execute(CustomerPaymentWebHookNotification webHookNotification){
    var account = this.accountGateway.getAccount(webHookNotification.accountId()).orElseThrow(RuntimeException::new);
    if (webHookNotification.success()){
      account.markSubscriptionAsPaid();
      accountGateway.saveAccount(account);
      eventBus.emit(new PaymentSubscriptionValidated(account));
    }else {
      eventBus.emit(new PaymentSubscriptionFailed(account));
    }
  }
}
