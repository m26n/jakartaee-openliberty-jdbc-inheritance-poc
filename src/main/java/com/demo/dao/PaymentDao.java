package com.demo.dao;

import com.demo.entity.CreditCardPayment;
import com.demo.entity.Payment;
import com.demo.entity.PaypalPayment;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@RequestScoped
public class PaymentDao {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    public void createCreditCardPayment(CreditCardPayment payment) {
        em.persist(payment);
    }

    public void createPaypalPayment(PaypalPayment payment) {
        em.persist(payment);
    }

    public PaypalPayment findPaypalPayment(int id) {
        return em.find(PaypalPayment.class, id);
    }

    public CreditCardPayment findCreditCardPayment(int id) {
        return em.find(CreditCardPayment.class, id);
    }

    public List<PaypalPayment> readAllPaypalPayments() {
        return em.createNamedQuery("PaypalPayment.findAll", PaypalPayment.class).getResultList();
    }

    public List<CreditCardPayment> readAllCreditCardPayments() {
        return em.createNamedQuery("CreditCardPayment.findAll", CreditCardPayment.class).getResultList();
    }
}