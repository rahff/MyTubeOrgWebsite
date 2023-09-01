package fr.myTube.core.account.ports.driver;

public record CustomerPaymentWebHookNotification(boolean success, String accountId) {
}
