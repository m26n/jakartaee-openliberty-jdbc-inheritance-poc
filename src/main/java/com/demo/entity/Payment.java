package com.demo.entity;

import java.util.Objects;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Order order;

    public Payment() {
    }

    public Payment(Double amount) {
        this.amount = amount;
    }

    public Payment(Double amount, Long id) {
        this.amount = amount;
        this.id = id;
    }

    public Payment(Long id, Double amount, Order order) {
        this.id = id;
        this.amount = amount;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orderId) {
        this.order = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(amount, payment.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
