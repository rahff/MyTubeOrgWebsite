package fr.myTube.core.account.ports.driver;

public record CreateAccountRequest(String name,
                                   String firstname,
                                   String companyName,
                                   String email,
                                   String channelName,
                                   String plan) {}
