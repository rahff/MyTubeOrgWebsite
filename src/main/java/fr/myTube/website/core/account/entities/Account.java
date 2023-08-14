package fr.myTube.website.core.account.entities;

import java.util.Objects;


public class Account {
  private final String id;
  private final String emailCodeValidation;
  private final String email;
  private boolean emailValidated;
  private CustomerSubscription subscription;

  public Account(String id, String emailCodeValidation, String email, boolean emailValidated, CustomerSubscription subscription) {
    this.id = id;
    this.emailCodeValidation = emailCodeValidation;
    this.email = email;
    this.emailValidated = emailValidated;
    this.subscription = subscription;
  }

  public String getId() {
    return id;
  }

  public String getEmailCodeValidation() {
    return emailCodeValidation;
  }

  public String getEmail() {
    return email;
  }

  public void validateEmail() {
    this.emailValidated = true;
  }

  public void addCustomerSubscription(CustomerSubscription subscription) {
    this.subscription = subscription;
  }

  public CustomerSubscription getSubscription() {
    return subscription;
  }

  public boolean equals(Object obj) {
    if(!(obj instanceof Account other)) return false;
    return id.equals(other.id)
      && other.getEmail().equals(email)
      && other.emailValidated == emailValidated
      && other.emailCodeValidation.equals(emailCodeValidation);
  }

  public int hashCode() {
    return Objects.hash(id, email, emailValidated, emailCodeValidation);
  }

  public void markSubscriptionAsPaid() {
    this.subscription = new CustomerSubscription(subscription.paymentUrl(), subscription.identifier(), true);
  }
}
