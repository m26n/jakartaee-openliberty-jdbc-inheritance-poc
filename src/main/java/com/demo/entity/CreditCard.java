package com.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "credit_card_payments")
@NamedQuery(name = "CreditCardPayment.findAll", query = "SELECT e FROM CreditCard e")
public class CreditCard extends Payment {
    private String cardNumber;

    public CreditCard() {
    }

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CreditCard(Double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    public CreditCard(Double amount, Long id, String cardNumber) {
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
