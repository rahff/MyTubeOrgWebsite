package fr.myTube.core.realm.events;

import fr.myTube.core.account.entities.Account;
import fr.myTube.core.realm.entities.Realm;
import fr.myTube.core.shared.Event;

public record VPCCreated(String vpcId, Account account) implements Event {

  public Realm getData() {
    return new Realm(vpcId, account);
  }
}
