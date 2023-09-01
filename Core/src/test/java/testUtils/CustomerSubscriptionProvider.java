package testUtils;


import fr.myTube.core.account.entities.CustomerSubscription;

public class CustomerSubscriptionProvider {

  public static CustomerSubscription getUnPaidCustomerSubscription() {
    return new CustomerSubscription("https://fakepaymenturl", "789456", false);
  }
  public static CustomerSubscription getPaidCustomerSubscription() {
    return new CustomerSubscription("https://fakepaymenturl", "789456", true);
  }
}
