package fr.myTube.core.account.ports.driven;

import fr.myTube.core.account.ports.driver.SubscriptionPlanRequest;
import fr.myTube.core.account.entities.CustomerSubscription;
public interface SubscriptionGateway {
  CustomerSubscription initSubscription(SubscriptionPlanRequest request);
}
