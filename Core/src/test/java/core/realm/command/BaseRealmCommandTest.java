package core.realm.command;


import fr.myTube.core.account.entities.Account;
import testUtils.AccountProvider;

import java.util.function.Supplier;


public class BaseRealmCommandTest {
  protected Supplier<Account> accountProvider = AccountProvider::getNewAccount;
}
