package fr.myTube.website.core.account.ports.driven;

import fr.myTube.website.core.account.ports.driver.SubscriptionPlanRequest;
import fr.myTube.website.core.account.entities.CustomerSubscription;
public interface SubscriptionGateway {
  CustomerSubscription initSubscription(SubscriptionPlanRequest request);
}
