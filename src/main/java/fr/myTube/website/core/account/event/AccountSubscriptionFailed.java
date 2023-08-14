package fr.myTube.website.core.account.event;

import fr.myTube.website.core.account.entities.Account;
import fr.myTube.website.core.shared.Event;

public record AccountSubscriptionFailed(Account data) implements Event<Account> {
  public Account getData() {
    return data;
  }
}
