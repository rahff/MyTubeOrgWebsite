package fr.myTube.website.core.realm.command;

import fr.myTube.website.core.account.ports.driven.EmailGateway;
import fr.myTube.website.core.realm.entities.Realm;
import fr.myTube.website.core.realm.ports.driven.RealmGateway;
import fr.myTube.website.core.realm.ports.driver.CreateRealmRequest;

public class CreateRealm {

  private final RealmGateway realmGateway;
  private final EmailGateway emailGateway;

  public CreateRealm(RealmGateway realmGateway, EmailGateway emailGateway) {
    this.realmGateway = realmGateway;
    this.emailGateway = emailGateway;
  }

  public void execute(CreateRealmRequest request) {
    realmGateway.save(new Realm(request.vpcId(), request.account()));
    emailGateway.sendActivationKey(request.account()); // should add key here
  }
}
