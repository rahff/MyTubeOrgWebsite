package testUtils;

import fr.myTube.website.core.account.entities.Account;
import fr.myTube.website.core.account.entities.CustomerSubscription;

public class AccountProvider {

  public static Account getNewAccount() {
    return new Account("accountId", "123456", "email@gmail.com", false, null);
  }
  public static Account getValidatedAccount() {
    return new Account("accountId", "123456", "email@gmail.com", true, null);
  }
  public static Account getValidatedAccountWithUnPaidSubscription() {
    return new Account("accountId", "123456", "email@gmail.com", true, CustomerSubscriptionProvider.getUnPaidCustomerSubscription());
  }
  public static Account getValidatedAccountWithPaidSubscription() {

    return new Account("accountId", "123456", "email@gmail.com", true, CustomerSubscriptionProvider.getPaidCustomerSubscription());
  }
}
