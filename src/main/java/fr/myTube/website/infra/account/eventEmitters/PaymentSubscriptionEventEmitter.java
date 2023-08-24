package fr.myTube.website.infra.account.eventEmitters;


import fr.myTube.website.core.account.event.PaymentSubscriptionEvent;
import fr.myTube.website.core.shared.EventEmitter;
import fr.myTube.website.infra.realm.eventHandler.PaymentSubscriptionEventHandler;
import org.springframework.stereotype.Component;



@Component
public class PaymentSubscriptionEventEmitter extends EventEmitter<PaymentSubscriptionEvent> {
  public PaymentSubscriptionEventEmitter(PaymentSubscriptionEventHandler eventHandler) {
    super(eventHandler);
  }
}
