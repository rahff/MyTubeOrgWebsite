package fr.myTube.website.core.account.event;

import fr.myTube.website.core.account.entities.Account;
import fr.myTube.website.core.shared.Event;

public abstract class PaymentSubscriptionEvent implements Event {

  protected Account data;

  public PaymentSubscriptionEvent(Account data) {
    this.data = data;
  }

  public Account getData(){
    return this.data;
  }
}
