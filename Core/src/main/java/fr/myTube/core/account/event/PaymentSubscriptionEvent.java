package fr.myTube.core.account.event;

import fr.myTube.core.account.entities.Account;
import fr.myTube.core.shared.Event;

public abstract class PaymentSubscriptionEvent implements Event {

  protected Account data;

  public PaymentSubscriptionEvent(Account data) {
    this.data = data;
  }

  public Account getData(){
    return this.data;
  }
}
