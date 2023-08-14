package fr.myTube.website.core.account.exceptions;

public class EmailAlreadyExistException extends RuntimeException {
  public EmailAlreadyExistException(String email){
    super("email "+email+" already exist");
  }
}
