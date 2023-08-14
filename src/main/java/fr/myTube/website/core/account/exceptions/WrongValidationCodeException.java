package fr.myTube.website.core.account.exceptions;

public class WrongValidationCodeException extends RuntimeException{
  public WrongValidationCodeException() {
    super("wrong code");
  }
}
