package Aleksey.Plekhanov.dbService;

import Aleksey.Plekhanov.base.DBService;
import Aleksey.Plekhanov.base.dataSets.AddressDataSet;
import Aleksey.Plekhanov.base.dataSets.PhoneDataSet;
import Aleksey.Plekhanov.base.dataSets.UserDataSet;
import Aleksey.Plekhanov.cache.CacheEngine;
import Aleksey.Plekhanov.cache.CacheEngineImpl;
import Aleksey.Plekhanov.cache.MyCacheElement;
import Aleksey.Plekhanov.dbService.dao.UserDataSetDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.function.Function;

public class DBServiceImpl implements DBService {

    private final SessionFactory sessionFactory;
    private final CacheEngine<Long, UserDataSet> cache = new CacheEngineImpl<>(10,1000, 1000,true);

    DBServiceImpl() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/test");
        configuration.setProperty("hibernate.connection.username", "sa");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        configuration.setProperty("hibernate.jdbc.time_zone", "UTC");

        sessionFactory = createSessionFactory(configuration);
    }

    public DBServiceImpl(Configuration configuration) {
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public String getLocalStatus() {
        return runInSession(session -> {
            return session.getTransaction().getStatus().name();
        });
    }

    public int getSizeCache() {
        return cache.getSize();
    }

    public int getMiss() {
        return cache.getMissCount();
    }

    public int getHit() {
        return cache.getHitCount();
    }

    public void save(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            dao.save(dataSet);

            cache.put(new MyCacheElement<>(dataSet.getId(), dataSet));
        }
    }

    public UserDataSet read(long id) {
        MyCacheElement<Long, UserDataSet> cached = cache.get(id);
        if (cached != null) {
            return cached.getValue();
        }
        UserDataSet user = runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.read(id);
        });
        if (user != null) {
            cache.put(new MyCacheElement<>(id, user));
        }
        return user;
    }

    public UserDataSet readByName(String name) {
        MyCacheElement<Long, UserDataSet> cached = cache.getByName(name);
        if (cached != null) {
            return cached.getValue();
        }
        UserDataSet user = runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.readByName(name);
        });
        if (user != null) {
            cache.put(new MyCacheElement<>(user.getId(), user));
        }
        return user;
    }

    public List<UserDataSet> readAll() {
        List<UserDataSet> allUsers = runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.readAll();
        });
        if (allUsers != null) {
            allUsers.forEach(user -> new MyCacheElement<>(user.getId(), user));
        }
        return allUsers;
    }

    public void shutdown() {
        cache.dispose();
        sessionFactory.close();
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }
}
