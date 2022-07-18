package com.home.webm3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class ImageDAOTest {
    static DataImage image;

    @BeforeAll
    static void beforeAll() {
        image = new DataImage();
        //int itemId = DBUtilityForDAO.getMaxItemIdInItemTable();
        image.setImageId(0);
        image.setImageName("imageName10");
        image.setSize(0);
        image.setContentType("contentType10");
        image.setPath("path10");
        image.setMd5("md510");
        image.setItemId(0);

        //System.out.println("  Изображение ':"+image.toString());

    }
    @Test
    void insert() {
        System.out.println("Начало теста на добавление изображения");
        int itemId = DBUtilityForDAO.getMaxItemIdInItemTable();
        System.out.println("  Изображение для теста':"+image.toString());
        ImageDAO.insert(image,itemId);
        System.out.println("Конец теста на добавление изображения");
    }

    @Test
    void selectAll() {
        System.out.println("Начало теста на получения списка изображений");
        int itemId = DBUtilityForDAO.getMaxItemIdInItemTable();
        System.out.println("  Индекс элемента каталога :"+itemId);
        List<DataImage> listImage = ImageDAO.selectAll(itemId);
        if (listImage!=null) {
            System.out.println("  Список полученых изображений:" + listImage.toString());
        }else{
            System.out.println("  Список полученых изображений пустой." );
        }

        System.out.println("Конец теста на получения списка изображений");
    }
    @Test
    void select() {
        System.out.println("Начало теста на получение изображения");
        int imageId = DBUtilityForDAO.getMaxImageIdInImageTable();
        System.out.println("  Индекс изображения:"+imageId);
        DataImage image = ImageDAO.select(imageId);
        if (image!=null) {
            System.out.println("  Полученное изображение:" + image.toString());
        }else{
            System.out.println("  Полученное изображение пустое." );
        }

        System.out.println("Конец теста на получение изображения");
    }


    @Test
    void delete() {
        System.out.println("Начало теста на удаление изображения");
        int imageId = DBUtilityForDAO.getMaxImageIdInImageTable();
        System.out.println("  Индекс удаляемого изображение :"+imageId);
        ImageDAO.delete(imageId);
        System.out.println("Конец теста на удаление изображения");

    }

    @AfterAll
    static void afterAll() {

    }
}