package testUtils;


import fr.myTube.core.account.ports.driver.CreateAccountRequest;

public class RequestProviders {

  public static CreateAccountRequest getCreateAccountRequest() {
    return new CreateAccountRequest("name", "firstname", "enterpriseName", "email@gmail.com", "channelName", "Basic");
  }
  public static CreateAccountRequest getInvalidCreateAccountRequest() {
    return new CreateAccountRequest("name", "firstname",  "enterpriseName","emailalreadyexist@gmail.com", "channelName", "Basic");
  }
}
