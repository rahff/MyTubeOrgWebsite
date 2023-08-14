package fr.myTube.website.core.account.ports.driven;

public interface KeyProvider {

  String getValidationCode();
  String generateId();
}
