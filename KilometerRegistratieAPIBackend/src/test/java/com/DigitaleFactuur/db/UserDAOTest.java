package com.DigitaleFactuur.db;

import com.google.common.base.Optional;
import com.DigitaleFactuur.models.User;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDAOTest {

    private static final SessionFactory SESSION_FACTORY
            = HibernateUtil.getSessionFactory();
    private static Liquibase liquibase = null;
    private Session session;
    private Transaction tx;
    private UserDAO dao;

    @BeforeClass
    public static void setUpClass() throws LiquibaseException, SQLException {

        SessionFactoryImpl sessionFactoryImpl
                = (SessionFactoryImpl) SESSION_FACTORY;
        DriverManagerConnectionProviderImpl provider
                = (DriverManagerConnectionProviderImpl) sessionFactoryImpl
                .getConnectionProvider();
        Connection connection = provider.getConnection();
        Database database = DatabaseFactory
                .getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));

        liquibase
                = new Liquibase(
                        "migrations.xml",
                        new ClassLoaderResourceAccessor(),
                        database);
    }

    @AfterClass
    public static void tearDownClass() {
        SESSION_FACTORY.close();
    }

    @Before
    public void setUp() throws LiquibaseException {
        liquibase.update("DEV");
        session = SESSION_FACTORY.openSession();
        dao = new UserDAO(SESSION_FACTORY);
        tx = null;
    }

    @After
    public void tearDown() throws DatabaseException, LockException {
        liquibase.dropAll();
    }

    @Test
    public void findAll() {
        List<User> users = null;
        try {
            ManagedSessionContext.bind(session);
            tx = session.beginTransaction();

            //database bewerkerkingen
            users = dao.findAll();

            tx.commit();
        } catch (Exception e) {
            //bij problemen: rollback
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            ManagedSessionContext.unbind(SESSION_FACTORY);
            session.close();
        }

        Assert.assertNotNull(users);
        Assert.assertFalse(users.isEmpty());
        Assert.assertEquals(2, users.size());
    }

    @Test
    public void findByUsernamePassword() {
        String expectedUsername = "user1";
        String expectedPassword = "pwd1";

        Optional<User> user;

        //first
        try {
            ManagedSessionContext.bind(session);
            tx = session.beginTransaction();

            //database bewerkerkingen
            session
                    .createSQLQuery(
                            "INSERT INTO users "
                                    //bij het invoeren van null bij een primarykey genereert de database er automatisch 1
                                    + "values(null, :username, :password)"
                    )
                    .setString("username", expectedUsername)
                    .setString("password", expectedPassword)
                    .executeUpdate();

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            ManagedSessionContext.unbind(SESSION_FACTORY);
            session.close();
        }

        //reopen session
        session = SESSION_FACTORY.openSession();
        tx = null;

        //second
        try {
            ManagedSessionContext.bind(session);
            tx = session.beginTransaction();

            user = dao.findByUsername(expectedUsername//
                    // , expectedPassword
                    );
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            ManagedSessionContext.unbind(SESSION_FACTORY);
            session.close();
        }

        Assert.assertNotNull(user);
        Assert.assertTrue(user.isPresent());
        Assert.assertEquals(expectedUsername,
                user.get().getName());
    }
}