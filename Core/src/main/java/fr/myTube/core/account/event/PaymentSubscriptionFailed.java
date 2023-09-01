package fr.myTube.core.account.event;

import fr.myTube.core.account.entities.Account;

public class PaymentSubscriptionFailed extends PaymentSubscriptionEvent{
  public PaymentSubscriptionFailed(Account data) {
    super(data);
  }
}
