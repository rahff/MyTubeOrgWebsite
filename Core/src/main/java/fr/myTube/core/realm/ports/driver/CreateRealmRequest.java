package fr.myTube.core.realm.ports.driver;

import fr.myTube.core.account.entities.Account;

public record CreateRealmRequest(String vpcId, Account account) {
}
