package com.demo.dao;

import com.demo.entity.Order;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@RequestScoped
public class OrderRepository {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    public void createOrder(Order order) {
        em.persist(order);
    }

    public Order findOrder(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> readAllOrders() {
        return em.createNamedQuery("Order.findAll", Order.class).getResultList();
    }
}