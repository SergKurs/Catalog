package com.home.webm3;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class DBUtilityDDLTest {
    //@Ignore("Аннотация не игнорирует тест.")
    //@Test
    void createTables() {
        System.out.println("Начало теста на создание таблицы");
        DBUtilityDDL.createTables();
        System.out.println("Конец теста на создание таблицы");
    }

    //Ignore("Аннотация не игнорирует тест.")
    //@Test
    void deleteTables() {
        System.out.println("Начало теста на удаление таблицы");
        DBUtilityDDL.deleteTables();
        System.out.println("Конец теста на удаление таблицы");
    }

    //@Ignore("Аннотация не игнорирует тест.")
    //@Test
    void putDemoDataInTables() {
        System.out.println("Начало теста на заполнение демо таблицы");
        DBUtilityDDL.putDemoDataInTables();
        System.out.println("Конец теста на заполнение демо таблицы");
    }
}