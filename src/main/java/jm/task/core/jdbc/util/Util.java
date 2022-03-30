package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {     //утилита
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/jdbc_kata?serverTimezone=Europe/Moscow";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root12345";

    public static Connection getConnection() {   //получить соединение
        Connection connection = null;
        try {
            Class.forName(DRIVER);   //динамическая загрузка класса драйвера в память, после чего происходит его автоматическая регистрация
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);   //соединение с БД по параметрам
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
