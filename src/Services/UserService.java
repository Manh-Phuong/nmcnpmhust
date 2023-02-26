/* 
    Created on : Oct 22, 2022
    Author     : Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */

package Services;

import controller.LoginController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.UserModel;

public class UserService {

    public static boolean login(String userName, String passwod) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        String account = "";
        String role = "";
        String password = "";
        connection = MysqlConnection.getMysqlConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM users WHERE userName = '" + userName + "'");
        if (rs == null) {
            return false;
        }
        while (rs.next()) {
            account = rs.getString("userName");
            role = rs.getString("role");
            password = rs.getString("passwd");

        }
        if (password.equals(passwod)) {
            LoginController.user = new UserModel(role, account);
            return true;
        }
        connection.close();
        return false;
    }
}
