package jpa.test.db.el.test;

import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

public abstract class JPATestBase {

    protected static ServiceTracker<EntityManagerFactory, EntityManagerFactory> emfTracker;

    private static BundleContext context;

    @BeforeClass
    public static void setUp() {
        context = FrameworkUtil.getBundle(JPATestBase.class)
                               .getBundleContext();
        emfTracker = new ServiceTracker<>(context, EntityManagerFactory.class, new TrackerCustomizer());
        emfTracker.open();
    }

    @AfterClass
    public static void shutDown() {
        if (emfTracker != null) {
            emfTracker.close();
            emfTracker = null;
        }
        context = null;
    }

}
