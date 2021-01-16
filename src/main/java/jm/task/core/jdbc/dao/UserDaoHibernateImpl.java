package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String CREATE_TABLE_SQL = "CREATE TABLE `my_db`.`users` ("
                + "`id` INT NOT NULL AUTO_INCREMENT,"
                + "`name` VARCHAR(45) NULL,"
                + "`lastName` VARCHAR(45) NULL,"
                + "`age` INT NULL,"
                + "PRIMARY KEY (`id`))"
                + "COMMENT = 'Java Mentor course';";
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query createQuery = session.createSQLQuery(CREATE_TABLE_SQL);
            int create = createQuery.executeUpdate();
            System.out.println(" delete   " + create);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE `my_db`.`users`;";
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query createQuery = session.createSQLQuery(sqlDrop);

            int drop = createQuery.executeUpdate();
            System.out.println(" drop   " + drop);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User u = new User(name, lastName, age);
        Transaction transaction = null;
        Session session = null;
        try {

            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(u);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = null;
        String deleteBiID = "DELETE FROM my_db.users WHERE (id = '" + id + "')";
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query deleteQuery = session.createSQLQuery(deleteBiID);
            int delete = deleteQuery.executeUpdate();
            System.out.println(" delete   " + delete);
            transaction.commit();
            //session.close();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        Session session = null;
        List<User> users = null;
        String hql = "FROM User";
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query us = session.createQuery(hql);
            users = us.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String deleteAll = "DELETE FROM my_db.users;";
        Transaction transaction = null;
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query deleteQuery = session.createSQLQuery(deleteAll);
            int delete = deleteQuery.executeUpdate();
            System.out.println(" delete   " + delete);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
