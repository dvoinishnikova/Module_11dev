package org.example.crud;

import org.example.entities.Client;
import org.example.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public class ClientCrudService {

    public void createClient(Client client) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("User creation failed");
        }
    }

    public Client getClientById(long id) {
        Client client;
        try (Session session = getSession()
        ) {
            client = session.get(Client.class, id);
        }
        return Optional.ofNullable(client)
                .orElseThrow( () -> new NoSuchElementException("Client with such ID doesn't exist"));
    }

    public void updateClientById(long id, String name) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                client.setName(name);
                session.persist(client);
                transaction.commit();
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Client with such ID doesn't exist");
        }
    }

    public void deleteClientById(long id) {
        Client client;
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.get(Client.class, id);
            if (client != null) {
                session.remove(client);
                transaction.commit();
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Client with such ID doesn't exist");
        }
    }

    public List<Client> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("from Client ", Client.class).list();
        }
    }

    private static Session getSession() {
        return HibernateUtil.getInstance()
                .getSessionFactory()
                .openSession();
    }

}