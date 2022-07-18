package com.home.webm3;

//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnect {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/catalogdb?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    //private static String URL = "jdbc:mysql://127.0.0.1:3306/catalogdb?characterEncoding=utf8&useSSL=true";
    //private static String USERNAME = "root";
    //private static String PASSWORD = "root";
    private static volatile DataSource dataSource = null;

    //используем в качестые источника данных БД MySQL
    public static DataSource getDataSource(){

        //вариант использования хранения важных данных во внешнем файле свойств
        //Properties props = new Properties();
        //try(InputStream in = Files.newInputStream(Paths.get("application.properties"))){
        //    props.load(in);
        //}
        //URL = props.getProperty("url");
        //USERNAME = props.getProperty("username");
        //PASSWORD = props.getProperty("password");

        if (dataSource == null){
            synchronized (DBconnect.class){
                if (dataSource == null){
                    dataSource = new MysqlDataSource();
                    MysqlDataSource tmpDataSource = (MysqlDataSource) dataSource;
                    tmpDataSource.setURL(URL);
                    tmpDataSource.setUser(USERNAME);
                    tmpDataSource.setPassword(PASSWORD);

                }
            }
        }
        return dataSource;
    }

    //
    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException ex){
            //все плохо
            ex.printStackTrace();
        }
        return null;
    }

    //* закрываем соединение с БД
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
