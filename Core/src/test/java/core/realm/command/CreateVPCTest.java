package core.realm.command;

import fr.myTube.core.realm.command.CreateVPC;
import fr.myTube.core.realm.events.VPCCreated;
import fr.myTube.core.realm.ports.driven.CloudFacade;
import fr.myTube.core.realm.ports.driver.CreateVPCRequest;
import fr.myTube.core.shared.EventEmitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class CreateVPCTest extends BaseRealmCommandTest {

  private CreateVPC createVPC;
  private CloudFacade cloudFacade;
  private EventEmitter<VPCCreated> eventEmitter;

  @BeforeEach
  void setup(){
    eventEmitter = Mockito.mock(EventEmitter.class);
    cloudFacade = Mockito.mock(CloudFacade.class);
    createVPC = new CreateVPC(cloudFacade, eventEmitter);
  }

  @Test
  void create_vpc_from_account_subscription_event(){
    var request = new CreateVPCRequest(accountProvider.get());
    createVPC.execute(request);
    verify(cloudFacade).createVPC("accountId");
    verify(eventEmitter).emit(any(VPCCreated.class));
  }
}
