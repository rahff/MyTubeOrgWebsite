package fr.myTube.core.account.ports.driver;

public record AccountValidationRequest(String accountId, String code) {
}
