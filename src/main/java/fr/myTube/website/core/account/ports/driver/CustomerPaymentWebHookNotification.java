package fr.myTube.website.core.account.ports.driver;

public record CustomerPaymentWebHookNotification(boolean success, String accountId) {
}
