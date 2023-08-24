package fr.myTube.website.core.account.event;

import fr.myTube.website.core.account.entities.Account;
import fr.myTube.website.core.shared.Event;

public class PaymentSubscriptionFailed extends PaymentSubscriptionEvent{
  public PaymentSubscriptionFailed(Account data) {
    super(data);
  }
}
