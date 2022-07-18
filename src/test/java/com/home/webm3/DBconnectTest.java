package com.home.webm3;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBconnectTest {

    @BeforeEach
    void setUp() {
        //System.out.println("Я сообщение перед тестом коннектас БД!\nСейчас будет тест\nДержитесь");
    }

    @Test
    void getDataSource() {
    }

    @Test
    void getConnection() {
        System.out.println("Прием, прием. Я тест коннекта с БД!");
        //проверяем возможность соединения с БД
        Connection connection = new DBconnect().getConnection();
        Assert.assertNotNull("Связь с БД сущ.",connection);
        //Assert.assertNull("Связь с БД не сущ.",connection);
        if (connection == null){System.out.println("Связь с БД not");} else {System.out.println("Связь с БД ok");};

        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }



    }

    @Test
    void close() {
    }

    @AfterAll
    static void afterAll() {
        System.out.println("конец тестам");

    }
}