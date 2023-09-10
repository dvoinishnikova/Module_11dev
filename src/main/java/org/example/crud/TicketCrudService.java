package org.example.crud;

import org.example.entities.*;
import org.example.utils.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TicketCrudService {

    public void createTicket(Client client, Planet fromPlanet, Planet toPlanet) {
        Ticket ticket = new Ticket();
        ticket.setClientId(client);
        ticket.setFromPlanetId(fromPlanet);
        ticket.setToPlanetId(toPlanet);
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(ticket);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ticket creation failed");
        }
    }

    public Ticket getTicketById(long id) {
        Ticket ticket;
        try (Session session = getSession()
        ) {
            ticket = session.get(Ticket.class, id);
        }
        return Optional.ofNullable(ticket)
                .orElseThrow(() -> new NoSuchElementException("Ticket with such ID doesn't exist"));
    }

    public void updateTicketById(long id, Client clientId, Planet fromPlanetId, Planet toPlanetId) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                ticket.setClientId(clientId);
                ticket.setFromPlanetId(fromPlanetId);
                ticket.setToPlanetId(toPlanetId);
                session.persist(ticket);
                transaction.commit();
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Ticket with such ID doesn't exist");
        }
    }

    public void deleteTicketById(long id) {
        Ticket ticket;
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.remove(ticket);
                transaction.commit();
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Ticket with such ID doesn't exist");
        }
    }

    public List<Ticket> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("from Ticket ", Ticket.class).list();
        }
    }

    private static Session getSession() {
        return HibernateUtil.getInstance()
                .getSessionFactory()
                .openSession();
    }

}