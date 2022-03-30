package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();    //соединение с БД

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {     //создать таблицу пользователей
        try (Statement statement = connection.createStatement()) {   //экземпляр Statement для выполнения SQL – запросов
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS jdbc_kata.users" +  //создание таблицы
                    "(id mediumint not null auto_increment," +  //первый столбец id, генерация уникального идентификатора для новых строк
                    " name VARCHAR(50), " +    //второй столбец name, строковый тип
                    "lastname VARCHAR(50), " +   //третий столбец lastname, строковый тип
                    "age tinyint, " +   //четвертый столбец age, числовой тип
                    "PRIMARY KEY (id))"); //указание, что поле id является первичным ключом
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {     //удалить таблицу пользователей
        try (Statement statement = connection.createStatement()) {     //экземпляр Statement для выполнения SQL – запросов
            statement.executeUpdate("Drop table if exists jdbc_kata.users"); //удаление таблицы
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {       //сохранить пользователя
        String sql = "INSERT INTO jdbc_kata.users(name, lastname, age) VALUES(?,?,?)";    //команда создания строки
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {   //создание строки, подготовка к вставке параметров
            preparedStatement.setString(1, name);    //вставка параметра name
            preparedStatement.setString(2, lastName);   //вставка параметра lastName
            preparedStatement.setByte(3, age);     //вставка параметра age
            preparedStatement.executeUpdate();    //обновление БД
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {     //удалить пользователя по идентификатору
        try (Statement statement = connection.createStatement()) {    //экземпляр Statement для выполнения SQL – запросов
            String sql = "DELETE FROM jdbc_kata.users where id";  //команда на удаление пользователя по id
            statement.executeUpdate(sql);    //удаление пользователя по id
            System.out.println("User удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {      //получить всех пользователей
        List<User> allUser = new ArrayList<>();    //список
        String sql = "SELECT id, name, lastName, age FROM jdbc_kata.users";    //команда извлечения пользователей из БД
        try (Statement statement = connection.createStatement()) {    //экземпляр Statement для выполнения SQL – запросов
            ResultSet resultSet = statement.executeQuery(sql);    //извлечение пользователей из БД
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));       //возврат id
                user.setName(resultSet.getString("name"));     //возврат name
                user.setLastName(resultSet.getString("lastName"));     //возврат lastName
                user.setAge(resultSet.getByte("age"));     //возврат age
                allUser.add(user);    //добавляем в коллекцию
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUser;    //возвращаем коллекцию
    }

    public void cleanUsersTable() {      //очистить таблицу пользователей
        String sql = "DELETE FROM jdbc_kata.users";   //команда очистки таблицы
        try (Statement statement = connection.createStatement()) {   //экземпляр Statement для выполнения SQL – запросов
            statement.executeUpdate(sql);    //очистка таблицы
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось очистить");
        }
    }
}

