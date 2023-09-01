package fr.myTube.core.account.ports.driven;

public interface KeyProvider {

  String getValidationCode();
  String generateId();
}
