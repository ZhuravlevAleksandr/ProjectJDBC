package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();       //экземпляр класса UserDaoJDBCImpl

    public void createUsersTable() {    //создать таблицу пользователей
        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {     //удалить таблицу пользователей
        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {   //сохранить пользователя
        userDaoJDBC.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {   //удалить пользователя по идентификатору
        userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {     //получить всех пользователей
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {    //очистить таблицу пользователей
        userDaoJDBC.cleanUsersTable();
    }
}
