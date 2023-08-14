package fr.myTube.website.core.realm.ports.driven;

import fr.myTube.website.core.realm.entities.Realm;

public interface RealmGateway {
  void save(Realm realm);
}
