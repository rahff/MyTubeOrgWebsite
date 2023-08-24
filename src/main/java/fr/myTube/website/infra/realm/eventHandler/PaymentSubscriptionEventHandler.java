package fr.myTube.website.infra.realm.eventHandler;


import fr.myTube.website.core.account.event.PaymentSubscriptionEvent;
import fr.myTube.website.core.realm.command.CreateVPC;
import fr.myTube.website.core.realm.ports.driver.CreateVPCRequest;
import fr.myTube.website.infra.shared.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class PaymentSubscriptionEventHandler implements EventHandler<PaymentSubscriptionEvent> {
  private final CreateVPC createVPC;
  public PaymentSubscriptionEventHandler(CreateVPC createVPC) {
    this.createVPC = createVPC;
  }
  public void handle(PaymentSubscriptionEvent event) {
    createVPC.execute(new CreateVPCRequest(event.getData()));
  }
}
