package fr.myTube.core.infra.account.eventEmitters;


import fr.myTube.core.core.account.event.PaymentSubscriptionEvent;
import fr.myTube.core.core.shared.EventEmitter;
import fr.myTube.core.infra.realm.eventHandler.PaymentSubscriptionEventHandler;
import org.springframework.stereotype.Component;



@Component
public class PaymentSubscriptionEventEmitter extends EventEmitter<PaymentSubscriptionEvent> {
  public PaymentSubscriptionEventEmitter(PaymentSubscriptionEventHandler eventHandler) {
    super(eventHandler);
  }
}
