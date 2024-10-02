package com.demo.dao;

import java.util.List;

import com.demo.entity.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class EventDao {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    public void createEvent(Event event) {
        em.persist(event);
    }

    public Event readEvent(int eventId) {
        return em.find(Event.class, eventId);
    }

    public void updateEvent(Event event) {
        em.merge(event);
    }

    public void deleteEvent(Event event) {
        em.remove(event);
    }

    public List<Event> readAllEvents() {
        return em.createNamedQuery("Event.findAll", Event.class).getResultList();
    }

    public List<Event> findEvent(String name, String location, String time) {
        return em.createNamedQuery("Event.findEvent", Event.class)
                .setParameter("name", name)
                .setParameter("location", location)
                .setParameter("time", time).getResultList();
    }
}