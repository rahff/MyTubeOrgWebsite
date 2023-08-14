package fr.myTube.website.core.realm.ports.driver;

import fr.myTube.website.core.account.entities.Account;

public record CreateRealmRequest(String vpcId, Account account) {
}
