package com.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "credit_card_payment")
@NamedQuery(name = "CreditCardPayment.findAll", query = "SELECT e FROM CreditCardPayment e")
public class CreditCardPayment extends Payment {
    private String cardNumber;

    public CreditCardPayment() {
    }

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CreditCardPayment(Double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    public CreditCardPayment(Double amount, Long id, String cardNumber) {
        super(amount, id);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
