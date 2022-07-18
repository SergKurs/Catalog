package com.home.webm3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtilityForDAO {
    public static int getMaxItemIdInItemTable(){
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            int maxItemId;

            //ДЕМО максимальный индекс элемента каталога
            String query1maxId = "SELECT MAX(itemId) as maxItemId FROM item_table";

            try {
                con = DBconnect.getConnection();
                if (con!=null) {

                    stmt = con.createStatement();

                    //ДЕМО первый элемент каталога
                    rs = stmt.executeQuery(query1maxId);
                    if (rs.next()) {
                        maxItemId = rs.getInt("maxItemId");
                        return maxItemId;
                    }

                }//end if


            } catch (SQLException | NullPointerException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    stmt.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    rs.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return -1;
        }
    public static int getMaxImageIdInImageTable() {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            int maxImageId;

            //ДЕМО максимальный индекс изображения
            String query1maxId = "SELECT MAX(imageId) as maxImageId FROM image_table";

            try {
                con = DBconnect.getConnection();
                if (con != null) {

                    stmt = con.createStatement();

                    //ДЕМО первый элемент каталога
                    rs = stmt.executeQuery(query1maxId);
                    if (rs.next()) {
                        maxImageId = rs.getInt("maxImageId");
                        return maxImageId;
                    }

                }//end if


            } catch (SQLException | NullPointerException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    stmt.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    rs.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return -1;
        }
}

