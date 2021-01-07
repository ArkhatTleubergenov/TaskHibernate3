package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.transform.sax.SAXSource;
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
        // auto close session object
        // (Session session = Util.getSessionFactory().openSession()) {
        try{ Session session = Util.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();

            Query createQuery = session.createSQLQuery(CREATE_TABLE_SQL);
            //deleteQuery.setParameter("user_id", 10);
            int create = createQuery.executeUpdate();
            System.out.println(" delete   " + create);
            // commit transction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE `my_db`.`users`;";
        Transaction transaction = null;
        // auto close session object
        // (Session session = Util.getSessionFactory().openSession()) {
        try{ Session session = Util.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();

            Query createQuery = session.createSQLQuery(sqlDrop);

            int drop = createQuery.executeUpdate();
            System.out.println(" drop   " + drop);
            // commit transction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User u = new User(name, lastName, age);
        Transaction transaction = null;

        // auto close session object
        // (Session session = Util.getSessionFactory().openSession()) {
        try{ Session session = Util.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(u);

            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        String deleteBiID = "DELETE FROM my_db.users WHERE (id = '" + id + "')";
        // auto close session object
        // (Session session = Util.getSessionFactory().openSession()) {
        try{ Session session = Util.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();

            Query deleteQuery = session.createSQLQuery(deleteBiID);
            //deleteQuery.setParameter("user_id", 10);
            int delete = deleteQuery.executeUpdate();
            System.out.println(" delete   " + delete);
            // commit transction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        //User u = new User(name, lastName, age);
        Transaction transaction = null;

        List<User> list = new ArrayList<>();
        try{ Session session = Util.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("SELECT * FROM my_db.users");
            /*List<User> empList = query.list();
            for(Object emp : empList){
                //System.out.println("List of Employees::"+emp.getId()+","+emp.getName());
            }*/
            List<Object> empList = (List<Object>)query.list();
            Iterator itr = empList.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                int id = Integer.parseInt(String.valueOf(obj[0]));
                //Byte age = Byte.parseByte(String.valueOf(obj[1]));
                Byte age = Byte.parseByte(String.valueOf(obj[3]));
                String name = String.valueOf(obj[1]);
                String lastName = String.valueOf(obj[2]);
                //System.out.print(" id " + id);System.out.print(" age " + age);
                //System.out.print(" name " + name);System.out.print(" lastName " + lastName);
                User u = new User();
                u.setId((long) id);
                u.setName(name);
                u.setLastName(lastName);
                u.setAge(age);
                list.add(u);
            }
            // commit transction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String deleteAll = "DELETE FROM my_db.users;";
        Transaction transaction = null;
        // auto close session object
        // (Session session = Util.getSessionFactory().openSession()) {
        try{ Session session = Util.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();

            Query deleteQuery = session.createSQLQuery(deleteAll);
            int delete = deleteQuery.executeUpdate();
            System.out.println(" delete   " + delete);
            // commit transction
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
