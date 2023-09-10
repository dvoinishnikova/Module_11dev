package org.example.crud;

import lombok.extern.slf4j.Slf4j;
import org.example.utils.HibernateUtil;
import org.example.entities.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public class PlanetCrudService {

    public void createPlanet(Planet planet) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Planet creation failed");
        }
    }

    public Planet getPlanetById(String id) {
        Planet planet;
        try (Session session = getSession()
        ) {
            planet = session.get(Planet.class, id);
        }
        return Optional.ofNullable(planet)
                .orElseThrow(() -> new NoSuchElementException("Planet with such ID doesn't exist"));
    }

    public void updatePlanetById(String id, String name) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                planet.setName(name);
                session.persist(planet);
                transaction.commit();
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Planet with such ID doesn't exist");
        }
    }

    public void deletePlanetById(String id) {
        Planet planet;
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            planet = session.get(Planet.class, id);
            if (planet != null) {
                session.remove(planet);
                transaction.commit();
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Planet with such ID doesn't exist");
        }
    }

    public List<Planet> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("from Planet ", Planet.class).list();
        }
    }

    private static Session getSession() {
        return HibernateUtil.getInstance()
                .getSessionFactory()
                .openSession();
    }

}