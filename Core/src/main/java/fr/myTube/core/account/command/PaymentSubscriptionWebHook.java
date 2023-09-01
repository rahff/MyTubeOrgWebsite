package fr.myTube.core.account.command;

import fr.myTube.core.account.event.PaymentSubscriptionEvent;
import fr.myTube.core.account.event.PaymentSubscriptionFailed;
import fr.myTube.core.account.event.PaymentSubscriptionValidated;
import fr.myTube.core.account.ports.driven.AccountGateway;
import fr.myTube.core.shared.EventEmitter;
import fr.myTube.core.account.ports.driver.CustomerPaymentWebHookNotification;

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
