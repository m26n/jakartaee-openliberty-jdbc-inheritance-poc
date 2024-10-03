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
@Table(name = "paypal_payments")
@NamedQuery(name = "PaypalPayment.findAll", query = "SELECT e FROM PaypalPayment e")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PaypalPayment extends Payment {
    @Column(name = "paypal_id")
    private String paypalId;

    public PaypalPayment(Double amount, String paypalId) {
        super(amount);
        this.paypalId = paypalId;
    }
}


