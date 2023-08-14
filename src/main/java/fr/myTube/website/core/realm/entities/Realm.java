package fr.myTube.website.core.realm.entities;

import fr.myTube.website.core.account.entities.Account;

public class Realm {

  private final String vpcId;
  private final Account account;

  public Realm(String vpcId, Account account) {
    this.vpcId = vpcId;
    this.account = account;
  }

  public String getVpcId() {
    return vpcId;
  }
}
