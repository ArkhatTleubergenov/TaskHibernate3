package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl udhiHibernateDao = new UserDaoHibernateImpl();

        udhiHibernateDao.createUsersTable();// сделано

        udhiHibernateDao.saveUser("Marie","Curie", (byte) 33);// сделано
        udhiHibernateDao.saveUser("Pierre","Curie", (byte) 45);// сделано
        udhiHibernateDao.saveUser("Isaav","Newton", (byte) 46);// сделано
        udhiHibernateDao.saveUser("Erwin","Schr?dinger", (byte) 23);// сделано

        udhiHibernateDao.removeUserById(5); // сделано

        List<User> list = udhiHibernateDao .getAllUsers(); //сделано
        System.out.println(Arrays.deepToString(list.toArray()));// печать всех users

        udhiHibernateDao.cleanUsersTable(); // сделано

        udhiHibernateDao.dropUsersTable(); // сделано
    }
}