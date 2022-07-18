package com.home.webm3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {

    @Test
    void insert() {
    }

    @Test
    void selectAll() {
        System.out.println("Начало теста на получение списка всех элемента");
        //int itemId = DBUtilityForDAO.getMaxItemIdInItemTable();
        //System.out.println("  Индекс элемента:"+itemId);
        List<DataCatalogItem> listitem = ItemDAO.selectAll();
        if (listitem!=null) {
            System.out.println("  Полученный списрк элементов:" + listitem.toString());
        }else{
            System.out.println("  Список элемент отстутствует." );
        }

        System.out.println("Конец теста на получение списка всех элемента");
    }

    @Test
    void select() {
        System.out.println("Начало теста на получение элемента");
        int itemId = DBUtilityForDAO.getMaxItemIdInItemTable();
        System.out.println("  Индекс элемента:"+itemId);
        DataCatalogItem item = ItemDAO.select(itemId);
        if (item!=null) {
            System.out.println("  Полученный элемент:" + item.toString());
        }else{
            System.out.println("  Полученный элемент пуст." );
        }

        System.out.println("Конец теста на получение элемента");

    }

    @Test
    void delete() {
        System.out.println("Начало теста на удаление элемента'");
        int itemId = DBUtilityForDAO.getMaxItemIdInItemTable();
        System.out.println("  Индекс удаляемого элемента :"+itemId);
        ItemDAO.delete(itemId);
        System.out.println("Конец теста на удаление элемента");
    }

    @Test
    void update() {
    }
}