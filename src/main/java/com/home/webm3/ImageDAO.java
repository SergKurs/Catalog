package com.home.webm3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ImageDAO {

    public static void insert (DataImage image,DataCatalogItem item) {
        insert(image,item.getItemId());
    }

    public static void insert (DataImage image,int itemId) {
        //
        Connection con = DBconnect.getConnection();
        PreparedStatement pstmt = null;
        int resint ;
        String sqlQuery = "insert into image_table values(null,?,?,?,?,?,?)";
        try {
            if (con!=null) {
                pstmt = con.prepareStatement(sqlQuery);
                pstmt.setString(1, image.getImageName());
                pstmt.setInt(2, image.getSize());
                pstmt.setString(3, image.getContentType());
                pstmt.setString(4, image.getPath());
                pstmt.setString(5, image.getMd5());
                pstmt.setInt(6, itemId);

                resint = pstmt.executeUpdate();
                System.out.println("    Количесто вставленных строк:" + resint);
            }else {
                System.out.println("Коннект не создан.");
            }
        } catch (SQLException | NullPointerException ex){
            ex.printStackTrace();
        } finally {
            DBconnect.close(con,pstmt,null);
        }

    }
    public static List<DataImage> selectAll(DataCatalogItem item) {
        return selectAll(item.getItemId()) ;
    }
    public static List<DataImage> selectAll(int itemId) {
        //
        List<DataImage> listImage = new ArrayList<>();
        DataImage image = null;
        Connection con = DBconnect.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resSet = null ;
        String sqlQuery = "select * from image_table where itemId = ?";
        System.out.println("Номер элемента каталога':"+itemId);
        try {
            if (con!=null) {
                pstmt = con.prepareStatement(sqlQuery);
                pstmt.setInt(1, itemId);

                resSet = pstmt.executeQuery();
                while (resSet.next()) {
                    image = new DataImage();
                    image.setImageId(resSet.getInt("imageId"));
                    image.setImageName(resSet.getString("imageName"));
                    image.setSize(resSet.getInt("size"));
                    image.setContentType(resSet.getString("contentType"));
                    image.setPath(resSet.getString("path"));
                    image.setMd5(resSet.getString("md5"));
                    image.setItemId(resSet.getInt("itemId"));

                    System.out.println("  Изображение':"+image.toString());

                    listImage.add(image);
                }
                // System.out.println("Номер элемента каталога':"+itemId);
                return listImage;
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

    public static DataImage select(int imageId) {
        //
        DataImage image = new DataImage();
        Connection con = DBconnect.getConnection();
        PreparedStatement pstmt = null;
        ResultSet resSet = null ;
        String sqlQuery = "select * from image_table where imageId = ?";
        try {
            if (con!=null) {
                pstmt = con.prepareStatement(sqlQuery);
                pstmt.setInt(1, imageId);

                resSet = pstmt.executeQuery();
                if (resSet.next()) {
                    image.setImageId(resSet.getInt("imageId"));
                    image.setImageName(resSet.getString("imageName"));
                    image.setSize(resSet.getInt("size"));
                    image.setContentType(resSet.getString("contentType"));
                    image.setPath(resSet.getString("path"));
                    image.setMd5(resSet.getString("md5"));
                    image.setItemId(resSet.getInt("itemId"));

                    System.out.println("Номер изображения:"+imageId);

                    return image;
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
    public static void delete(int imageId){
        //
        Connection con = DBconnect.getConnection();
        PreparedStatement pstmt = null;
        int resint ;
        String sqlQuery = "delete from image_table where imageId = ?";
        try {
            if (con!=null) {
                pstmt = con.prepareStatement(sqlQuery);
                pstmt.setInt(1, imageId);

                resint = pstmt.executeUpdate();
                System.out.println("Количесто удаленных строк:" + resint);
            }else {
                System.out.println("Коннект не создан.");
            };

        } catch (SQLException | NullPointerException ex){
            ex.printStackTrace();
        } finally {
            DBconnect.close(con,pstmt,null);
        }
    }
}
