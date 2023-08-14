package fr.myTube.website.core.account.ports.driver;

public record AccountValidationRequest(String accountId, String code) {
}
