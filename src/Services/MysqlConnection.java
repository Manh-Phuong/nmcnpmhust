package Services;

import java.sql.*;
/**
 *
 * @author Hai
 */

/* 
    Created on : Oct 22, 2022
    Author     : Nguyen Trung Hieu - Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
    Kết nối với cơ sở dữ liệu
 */

public class MysqlConnection {
    public static Connection getMysqlConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "quan_ly_nhan_khau";
        String userName = "root";
        String password = "";
        return getMysqlConnection(hostName, dbName, userName, password);
    }
    
    public static Connection getMysqlConnection(String hostName, String dbName, String userName, String password) 
        throws SQLException, ClassNotFoundException{
        //Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName ;
        Connection conn = DriverManager.getConnection(connectionUrl, userName, password);
        return conn;
    }
}
