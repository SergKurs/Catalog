package com.home.webm3;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public static void insert(DataCatalogItem item){
        //
        Connection con = DBconnect.getConnection();
        PreparedStatement pstmt = null;
        int resint ;
        String sqlQuery = "insert into item_table values(null, ?,?,?,?,?, ?,?,?,?,?)";
        try {
            if (con!=null) {
                pstmt = con.prepareStatement(sqlQuery);
                pstmt.setString(1, item.getAttr0());
                pstmt.setString(2, item.getAttr1());
                pstmt.setString(3, item.getAttr2());
                pstmt.setString(4, item.getAttr3());
                pstmt.setString(5, item.getAttr4());
                pstmt.setInt(6, item.getAttrInt0());
                pstmt.setInt(7, item.getAttrInt1());
                pstmt.setInt(8, item.getAttrInt2());
                pstmt.setInt(9, item.getAttrInt3());
                pstmt.setInt(10, item.getAttrInt4());

                resint = pstmt.executeUpdate();
                System.out.println("Количесто вставленных строк элементов:" + resint);
            }else {
                System.out.println("Коннект не создан.");
            }
        } catch (SQLException | NullPointerException ex){
            ex.printStackTrace();
        } finally {
            DBconnect.close(con,pstmt,null);
        }


    }
    public static List<DataCatalogItem> selectAll(){
        //
        List<DataCatalogItem> listItem = new ArrayList<>();
        DataCatalogItem item;
        Connection con = DBconnect.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resSet = null ;

        String sqlQuery = "select * from item_table";
        try {
            if (con!=null) {
                pstmt = con.prepareStatement(sqlQuery);
                resSet = pstmt.executeQuery();
                while (resSet.next()) {
                    item = new DataCatalogItem();
                    item.setItemId(resSet.getInt("itemId"));
                    item.setAttr0(resSet.getString("attr0"));
                    item.setAttr1(resSet.getString("attr1"));
                    item.setAttr2(resSet.getString("attr2"));
                    item.setAttr3(resSet.getString("attr3"));
                    item.setAttr4(resSet.getString("attr4"));
                    item.setAttrInt0(resSet.getInt("attrInt0"));
                    item.setAttrInt1(resSet.getInt("attrInt1"));
                    item.setAttrInt2(resSet.getInt("attrInt2"));
                    item.setAttrInt3(resSet.getInt("attrInt3"));
                    item.setAttrInt4(resSet.getInt("attrInt4"));
                    item.setListImages(ImageDAO.selectAll(resSet.getInt("itemId")));

                    System.out.println("Номер элемента':"+resSet.getInt("itemId"));

                    listItem.add(item);
                }
                return listItem;
            } else {
                System.out.println("Коннект не создан.");
            }
        } catch (SQLException | NullPointerException ex){
            ex.printStackTrace();
        } finally {
            DBconnect.close(con,pstmt,null);
        }
        return null;
    }
    public static DataCatalogItem select(int itemId){
        //
        DataCatalogItem item = new DataCatalogItem();
        Connection con = DBconnect.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resSet = null ;
        String sqlQuery = "select * from item_table where itemId = ?";
        try {
            if (con!=null) {
                pstmt = con.prepareStatement(sqlQuery);
                pstmt.setInt(1, itemId);

                resSet = pstmt.executeQuery();
                if (resSet.next()) {
                    item.setItemId(resSet.getInt("itemId"));
                    item.setAttr0(resSet.getString("attr0"));
                    item.setAttr1(resSet.getString("attr1"));
                    item.setAttr2(resSet.getString("attr2"));
                    item.setAttr3(resSet.getString("attr3"));
                    item.setAttr4(resSet.getString("attr4"));
                    item.setAttrInt0(resSet.getInt("attrInt0"));
                    item.setAttrInt1(resSet.getInt("attrInt1"));
                    item.setAttrInt2(resSet.getInt("attrInt2"));
                    item.setAttrInt3(resSet.getInt("attrInt3"));
                    item.setAttrInt4(resSet.getInt("attrInt4"));

                    System.out.println("Номер элемента':"+itemId);

                    item.setListImages(ImageDAO.selectAll(itemId));

                    return item;
                }
            }else {
                System.out.println("Коннект не создан.");
            }
        } catch (SQLException | NullPointerException ex){
            ex.printStackTrace();
        } finally {
            DBconnect.close(con,pstmt,null);
        }
        return null;
    }
    public static void delete(int itemId){
        //
        List<DataImage> listImage;
        Connection con = DBconnect.getConnection();
        PreparedStatement pstmt = null;
        int resint ;
        String sqlQueryImage = "delete from image_table where imageId = ?";
        String sqlQueryItem = "delete from item_table where itemId = ?";
        try {
            if (con!=null) {
                //удаляем изображения указанного элемента каталога
                listImage = ImageDAO.selectAll(itemId);
                if (listImage!=null) {
                    for (DataImage image : listImage) {
                        pstmt = con.prepareStatement(sqlQueryImage);
                        pstmt.setInt(1, image.getImageId());
                        resint = pstmt.executeUpdate();
                        try {
                            File file = new File(image.getPath());
                            if (file.delete()) {
                                System.out.println(" Удаление файла изображения прошло успешно");
                            }
                        } catch (Exception e){};
                        System.out.println("    Количесто удаленных строк изображений:" + resint);
                    }
                }
                //удаляем элемент каталога
                pstmt = con.prepareStatement(sqlQueryItem);
                pstmt.setInt(1, itemId);
                resint = pstmt.executeUpdate();
                System.out.println("    Количесто удаленных строк эоементов:" + resint);
            } else {
                System.out.println("Коннект не создан.");
            }

        } catch (SQLException | NullPointerException ex){
            ex.printStackTrace();
        } finally {
            DBconnect.close(con,pstmt,null);
        }

    }
    public static void update(int itemId,DataCatalogItem item){
        //не реализовано. Заменяется на удаление и добавление

    }
}
