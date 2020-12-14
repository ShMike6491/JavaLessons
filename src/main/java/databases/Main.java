package databases;

import java.sql.*;
import java.util.Random;

//execute()
//executeQuery() - SELECT
//executeUpdate() - UPDATE, DELETE, INSERT

public class Main {

    // поле для подключения в DB и работы с ней
    private static Connection connection;
    // объект для обращения к DB
    private static Statement stmt;
    // переменная для создания запросов
    private static ResultSet rs;
    private static Random rand = new Random();

    public static void main(String[] args) {
        try {
            connection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            clearTable();
            createThousand();
            //System.out.println(selectItem(""));
            //updateItem("item10", "99.99");
            //System.out.println(selectItem("item10"));
            showItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            disconnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void showItems() throws SQLException {
        rs = stmt.executeQuery("SELECT name, price FROM goods ORDER BY price DESC;");

        while (rs.next()) {
            System.out.printf("Name: %7s price: %s%n", rs.getString("name"), rs.getString("price"));
        }
    }

    /**
     * метод изменения данных в DB
     */
    private static void updateItem(String item, String price) throws SQLException {
        stmt.executeUpdate
                (String.format("UPDATE goods SET price = '%s' WHERE name = '%s'", price, item));
    }

    /**
     * добавление 1000 элементов в DB
     */
    private static void createThousand() throws SQLException {
        long startTime = System.currentTimeMillis();

        connection.setAutoCommit(false);
        for (int i = 0; i < 1000; i++) {
            stmt.addBatch(String.format
                    ("INSERT INTO goods (id, name, price) VALUES (%d, 'item%d', '%d')", i, i, i * rand.nextInt(100)));
        }
        stmt.executeBatch();
        connection.setAutoCommit(true);

        System.out.println(System.currentTimeMillis() - startTime);
    }

    /**
     * метод удаляет все данные с таблицы
     */
    private static void clearTable() throws SQLException {
        stmt.executeUpdate("DELETE FROM goods");
    }

    /**
     * метод для получения данных с DB
     */
    private static String selectItem(String name) throws SQLException {
        rs = stmt.executeQuery
                (String.format("SELECT price FROM goods WHERE name = '%s'", name));
        String price = rs.getString("price");
        if(price != null) {
            return price;
        }
        return "Such Item Does Not Exist";
    }

    /**
     *  закрытие подключения
     */
    private static void disconnect() throws SQLException {
        connection.close();
    }

    /**
     * выполнить подключение
     */
    private static void connection() throws ClassNotFoundException, SQLException {
        // подключиться к JDBC после импорта библиотеки
        Class.forName("org.sqlite.JDBC");
        // указываем на местоположение DB
        connection = DriverManager.getConnection
                ("jdbc:sqlite:src/main/resources/mainDB.db");
        stmt = connection.createStatement();
    }
}
