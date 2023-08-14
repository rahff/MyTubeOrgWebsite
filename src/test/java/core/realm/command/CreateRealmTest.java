package core.realm.command;

import fr.myTube.website.core.account.entities.Account;
import fr.myTube.website.core.account.ports.driven.EmailGateway;
import fr.myTube.website.core.realm.command.CreateRealm;
import fr.myTube.website.core.realm.entities.Realm;
import fr.myTube.website.core.realm.ports.driven.RealmGateway;
import fr.myTube.website.core.realm.ports.driver.CreateRealmRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testUtils.AccountProvider;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class CreateRealmTest extends BaseRealmCommandTest {

  private CreateRealm createRealm;
  private RealmGateway realmGateway;
  private EmailGateway emailGateway;


  @BeforeEach
  void setup(){
    accountProvider = AccountProvider::getNewAccount;
    emailGateway = Mockito.mock(EmailGateway.class);
    realmGateway = Mockito.mock(RealmGateway.class);
    createRealm = new CreateRealm(realmGateway, emailGateway);
  }

  @Test
  void create_realm_on_vpc_created_event(){
    var request = new CreateRealmRequest("vpcId", accountProvider.get());
    createRealm.execute(request);
    verify(realmGateway).save(any(Realm.class));
  }

  @Test
  void A_email_is_send_to_tell_customer_his_app_is_ready(){
    var request = new CreateRealmRequest("vpcId", accountProvider.get());
    createRealm.execute(request);
    verify(emailGateway).sendActivationKey(any(Account.class));
  }
}
