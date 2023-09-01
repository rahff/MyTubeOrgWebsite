package fr.myTube.core.account.event;

import fr.myTube.core.account.entities.Account;

public class PaymentSubscriptionValidated extends PaymentSubscriptionEvent {
  public PaymentSubscriptionValidated(Account data) {
    super(data);
  }
}
