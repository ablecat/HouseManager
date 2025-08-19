package util;

import java.sql.*;

//包装数据库连接程序
public class DBConnectionManager {
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/MyDB?useUnicode=true&characterEncoding=utf8&&useSSL=true";
    private static String username = "root";
    private static String password = "123456";
    public static Connection getConnection(){
        try{
            Class.forName(driverName);
            return DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
