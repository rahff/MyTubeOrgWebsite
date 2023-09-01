package fr.myTube.core.account.exceptions;

public class EmailAlreadyExistException extends RuntimeException {
  public EmailAlreadyExistException(String email){
    super("email "+email+" already exist");
  }
}
