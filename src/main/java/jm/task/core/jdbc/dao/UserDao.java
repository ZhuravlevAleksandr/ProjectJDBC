package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable(); //создать таблицу пользователей

    void dropUsersTable(); //удалить таблицу пользователей

    void saveUser(String name, String lastName, byte age); //сохранить пользователя

    void removeUserById(long id); //удалить пользователя по идентификатору

    List<User> getAllUsers(); //получить всех пользователей

    void cleanUsersTable(); //очистить таблицу пользователей
}
