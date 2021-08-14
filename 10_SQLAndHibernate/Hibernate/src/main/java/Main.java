import com.mysql.cj.xdevapi.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = LogManager.getLogger("LOGGER");

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()) {
            Session session = sessionFactory.openSession();
            List <LinkedPurchaseList> linked = FillTables.fillLincedPurchaseList(session);

            Transaction transaction = session.beginTransaction();
            linked.forEach(session::save);
            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }
    }
}
