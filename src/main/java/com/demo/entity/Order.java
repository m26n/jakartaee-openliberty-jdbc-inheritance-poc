package com.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_name")
    private String orderName;

    @OneToMany(mappedBy = "order")
    private List<Payment> payment;

    public Order() {
    }

    public Order(String orderName) {
        this.orderName = orderName;
    }

    public Order(List<Payment> payment) {
        this.payment = payment;
    }

    public Order(Long id, List<Payment> payment) {
        this.id = id;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }
}
