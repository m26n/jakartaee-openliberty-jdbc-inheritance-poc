package com.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "paypal_payment")
@NamedQuery(name = "PaypalPayment.findAll", query = "SELECT e FROM PaypalPayment e")
public class PaypalPayment extends Payment {
    @Column(name = "paypal_id")
    private String paypalId;

    public PaypalPayment() {
    }

    public PaypalPayment(String paypalId) {
        this.paypalId = paypalId;
    }

    public PaypalPayment(Double amount, String paypalId) {
        super(amount);
        this.paypalId = paypalId;
    }

    public PaypalPayment(Double amount, Long id, String paypalId) {
        super(amount, id);
        this.paypalId = paypalId;
    }

    public String getPaypalId() {
        return paypalId;
    }

    public void setPaypalId(String cardNumber) {
        this.paypalId = cardNumber;
    }
}
