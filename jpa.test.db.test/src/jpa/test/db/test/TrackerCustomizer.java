package jpa.test.db.test;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.ServiceReference;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class TrackerCustomizer implements ServiceTrackerCustomizer<EntityManagerFactory, EntityManagerFactory> {

    /**
     * Like in persistence.xml
     */
    private static final String TEST_DB_UNIT_NAME = "jpa.test.db";

    @Override
    public EntityManagerFactory addingService(ServiceReference<EntityManagerFactory> reference) {
        String unitName = (String) reference.getProperty(EntityManagerFactoryBuilder.JPA_UNIT_NAME);
        return TEST_DB_UNIT_NAME.equals(unitName) ? reference.getBundle().getBundleContext().getService(reference) : null;
    }

    @Override
    public void modifiedService(ServiceReference<EntityManagerFactory> reference, EntityManagerFactory service) {
    }

    @Override
    public void removedService(ServiceReference<EntityManagerFactory> reference, EntityManagerFactory service) {
        String unitName = (String) reference.getProperty(EntityManagerFactoryBuilder.JPA_UNIT_NAME);
        if (TEST_DB_UNIT_NAME.equals(unitName)) {
            reference.getBundle()
                     .getBundleContext()
                     .ungetService(reference);
        }
    }

}
