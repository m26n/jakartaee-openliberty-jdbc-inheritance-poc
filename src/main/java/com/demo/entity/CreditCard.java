package com.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "credit_card_payments")
@NamedQuery(name = "CreditCardPayment.findAll", query = "SELECT e FROM CreditCard e")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CreditCard extends Payment {
    @Column(name = "card_number")
    private String cardNumber;

    public CreditCard(Double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }
}
