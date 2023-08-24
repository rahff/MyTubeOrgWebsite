package fr.myTube.website.core.realm.events;

import fr.myTube.website.core.account.entities.Account;
import fr.myTube.website.core.realm.entities.Realm;
import fr.myTube.website.core.shared.Event;

public record VPCCreated(String vpcId, Account account) implements Event {

  public Realm getData() {
    return new Realm(vpcId, account);
  }
}
