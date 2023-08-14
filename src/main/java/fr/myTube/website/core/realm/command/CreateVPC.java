package fr.myTube.website.core.realm.command;

import fr.myTube.website.core.shared.EventEmitter;
import fr.myTube.website.core.realm.events.VPCCreated;
import fr.myTube.website.core.realm.ports.driven.CloudFacade;
import fr.myTube.website.core.realm.ports.driver.CreateVPCRequest;

public class CreateVPC {
  private final CloudFacade cloudFacade;
  private final EventEmitter eventEmitter;

  public CreateVPC(CloudFacade cloudFacade, EventEmitter eventEmitter) {
    this.cloudFacade = cloudFacade;
    this.eventEmitter = eventEmitter;
  }

  public void execute(CreateVPCRequest request){
    // Future ?
    var vpcId = cloudFacade.createVPC(request.account().getId());
    eventEmitter.emit(new VPCCreated(vpcId, request.account()));
  }
}