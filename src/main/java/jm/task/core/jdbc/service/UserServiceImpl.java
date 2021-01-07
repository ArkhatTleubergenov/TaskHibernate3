package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoHibernateImpl UDHI = new UserDaoHibernateImpl();
    public void createUsersTable() {
        UDHI.createUsersTable();
    }

    public void dropUsersTable() {
        UDHI.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UDHI.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        UDHI.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> ar = UDHI.getAllUsers();
        return ar;
    }

    public void cleanUsersTable() {
        UDHI.cleanUsersTable();
    }
}
