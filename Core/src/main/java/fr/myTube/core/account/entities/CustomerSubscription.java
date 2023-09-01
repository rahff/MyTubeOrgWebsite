package fr.myTube.core.account.entities;

import java.util.Objects;

public record CustomerSubscription(String paymentUrl, String identifier, boolean paid) {

  public boolean equals(Object o) {
    if (!(o instanceof CustomerSubscription other)) return false;
    return Objects.equals(paymentUrl, other.paymentUrl) && Objects.equals(identifier, other.identifier);
  }

  public int hashCode() {
    return Objects.hash(paymentUrl, identifier);
  }
}
