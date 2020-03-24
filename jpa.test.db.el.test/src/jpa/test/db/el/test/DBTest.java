package jpa.test.db.el.test;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jpa.test.db.el.Citizen;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBTest extends JPATestBase {

    private static void rollback(EntityTransaction t) {
        if (t != null && t.isActive()) {
            try {
                t.rollback();
            } catch (PersistenceException pe) {
                // Nothing more can be done.
            }
        }
    }

    private static void close(EntityManager em) {
        if (em != null && em.isOpen()) {
            try {
                em.close();
            } catch (IllegalStateException e) {
                // Nothing more can be done.
            }
        }
    }

    @Test
    public void _001_save_citizen() {
        EntityManagerFactory emf = null;
        try {
            emf = emfTracker.waitForService(500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
            assertTrue(false);
        }
        
        {
            EntityManager em = null;
            EntityTransaction t = null;
            try {
                em = emf.createEntityManager();
                t = em.getTransaction();
                Citizen c = Citizen.valueOf(UUID.randomUUID(), "Ivan", "Ivanov");
                t.begin();
                em.persist(c);
                t.commit();
            } catch(Exception e) {
                e.printStackTrace();
                rollback(t);
                assertTrue(false);
            } finally {
                close(em);
            }
        }

    }

}
