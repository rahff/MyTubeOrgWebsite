package fr.myTube.core.realm.ports.driven;

import fr.myTube.core.realm.entities.Realm;

public interface RealmGateway {
  void save(Realm realm);
}
