package com.home.webm3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtilityDDL {
    public static void createTables() {
        Connection con = null;
        Statement stmt = null;
        //ResultSet rs;
        String query2 = "CREATE TABLE image_table (" +
                " imageId   INT   NOT NULL AUTO_INCREMENT," +
                " imageName VARCHAR (255)     ," +
                " size  INT              ," +
                " contentType  CHAR (50) ," +
                " path  VARCHAR (1024) ," +
                " md5  VARCHAR (1024) ," +
                " itemId  INT      ," +
                " PRIMARY KEY (imageId)," +
                " FOREIGN KEY (itemId) REFERENCES item_table(itemId)" +
                " );";
        String query1 = "CREATE TABLE item_table (" +
                " itemId   INT  NOT NULL AUTO_INCREMENT," +
                " attr0 VARCHAR (255)     ," +
                " attr1 VARCHAR (255)     ," +
                " attr2 VARCHAR (255)     ," +
                " attr3 VARCHAR (255)     ," +
                " attr4 VARCHAR (255)     ," +
                " attrInt0 INT     ," +
                " attrInt1 INT     ," +
                " attrInt2 INT     ," +
                " attrInt3 INT     ," +
                " attrInt4 INT     ," +
                " PRIMARY KEY (itemId)" +
                " );";

        //образец
        //String query = "INSERT INTO test.books (id, name, author) \n" +
        //        " VALUES (3, 'Head First Java', 'Kathy Sieara');";


        try {
            con = DBconnect.getConnection();
            stmt = con.createStatement();
            //rs = stmt.executeQuery(query1);
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);
        } catch (SQLException | NullPointerException ex) {
            //
        } finally {
                try {
                    if(con != null) {
                        con.close();
                    };
                } catch (SQLException | NullPointerException ex) { ex.printStackTrace(); }
                try { stmt.close(); } catch (SQLException | NullPointerException ex) { ex.printStackTrace(); }
                //try { rs.close(); }catch (SQLException ex) { ex.printStackTrace(); }
        }
    }


    public static void deleteTables() {
        Connection con = null;
        Statement stmt = null;
        //ResultSet rs;
        String query1 = "DROP TABLE image_table;";
        String query2 = "DROP TABLE item_table;";

        //образец
        //String query = "INSERT INTO test.books (id, name, author) \n" +
        //        " VALUES (3, 'Head First Java', 'Kathy Sieara');";


        try {
            con = DBconnect.getConnection();
            stmt = con.createStatement();//осторожно, возможен NPE
            //ResultSet rs = stmt.executeQuery(query1);
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);


        } catch (SQLException | NullPointerException ex) {
            //
        } finally {
                try {
                    con.close();
                } catch (SQLException | NullPointerException ex) {
                    ex.printStackTrace();
                }
                try {
                    stmt.close();
                } catch (SQLException | NullPointerException ex) {
                    ex.printStackTrace();
                }
                //try { rs.close(); }catch (SQLException ex) { ex.printStackTrace(); }

        }
    }

    public static void putDemoDataInTables(){
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;

        //ДЕМО первый элемент каталога
        String query1item1 = "INSERT INTO item_table (attr0,attr1,attr2,attr3,attr4,"+
                "attrInt0,attrInt1,attrInt2,attrInt3,attrInt4) " +
                "VALUES ('ПервоеЗачениеКн1','ВтороеЗачениеКн1', 'ТретьеЗначКн1', 'ЧетвЗначКн1', 'ПяЗначКн1',1,2,3,4,5)";
        String query1maxId = "SELECT MAX(itemId) as maxItemId FROM item_table";
        String query1image1begin = "INSERT INTO image_table (imageName,size,contentType,path,md5,itemId) " +
                " VALUES ('Picture1','0', 'image/jpg', 'picture1.jpg', '0', ";
        String query1image1end = " );";
        String query1image2begin = "INSERT INTO image_table (imageName,size,contentType,path,md5,itemId) " +
                " VALUES ('Picture2','0', 'image/jpg', 'picture2.jpg', '0', ";
        String query1image2end = " );";

        //ДЕМО второй элемент каталога
        String query2item1 = "INSERT INTO item_table (attr0,attr1,attr2,attr3,attr4," +
                "attrInt0,attrInt1,attrInt2,attrInt3,attrInt4) " +
                "VALUES ('ПервоеЗачениеКн2','ВтороеЗачениеКн2', 'ТретьеЗначКн2', 'ЧетвЗначКн2', 'ПяЗначКн2',11,12,13,14,15)";
        String query2maxId = "SELECT MAX(itemId) as maxItemId FROM item_table";
        String query2image1begin = "INSERT INTO image_table (imageName,size,contentType,path,md5,itemId) " +
                " VALUES ('Picture3','0', 'image/jpg', 'picture3.jpg', '0', ";
        String query2image1end = " );";
        String query2image2begin = "INSERT INTO image_table (imageName,size,contentType,path,md5,itemId) " +
                " VALUES ('Picture4','0', 'image/jpg', 'picture4.jpg', '0', ";
        String query2image2end = " );";

                    try {
                        con = DBconnect.getConnection();
                        if (con!=null) {

                            stmt = con.createStatement();

                            //ДЕМО первый элемент каталога
                            stmt.executeUpdate(query1item1);
                            rs = stmt.executeQuery(query1maxId);
                            if (rs.next()) {
                                int maxItemId = rs.getInt("maxItemId");
                                stmt.executeUpdate(query1image1begin + maxItemId + query1image1end);
                                stmt.executeUpdate(query1image2begin + maxItemId + query1image2end);
                            }

                            //ДЕМО второй элемент каталога
                            stmt.executeUpdate(query2item1);
                            rs = stmt.executeQuery(query2maxId);
                            if (rs.next()) {
                                int maxItemId = rs.getInt("maxItemId");
                                stmt.executeUpdate(query2image1begin + maxItemId + query2image1end);
                                stmt.executeUpdate(query2image2begin + maxItemId + query2image2end);
                            }
                        }


                    } catch (Exception ex) {
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
    }



    //справочник
    //метод executeUpdate() вместо executeQuery().
    // Этот метод используется для запросов INSERT, UPDATE и DELETE,
    // а также для SQL DDL выражений, таких как CREATE, ALTER или DROP.
}
