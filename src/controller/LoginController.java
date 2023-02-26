/* 
    Created on : Oct 22, 2022
    Author     : Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller;

import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.UserModel;

/**
 *
 * @author Admin
 */
public class LoginController implements Initializable {

    public static UserModel user;
    private StageController sc = new StageController();
    @FXML
    private Button Login;
    @FXML
    private Label message;
    @FXML
    private TextField account_input;
    @FXML
    private TextField password_input;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void Login_(ActionEvent e) throws IOException {
        try {
            if (account_input.getText().length() == 0 || password_input.getText().length() == 0) {
                message.setText("Vui lòng nhập đầy đủ thông tin!");
            } else if (UserService.login(account_input.getText().toLowerCase(), password_input.getText()) == false) {
                message.setText("Tài khoản hoặc mật khẩu không chính xác!");
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("/view/TrangChu.fxml"));
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                Scene scene = new Scene(root, 864, 486);
                scene.getStylesheets().add("/CSS/globalStyle.css");
                stage.setScene(scene);

                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
                stage.show();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            message.setText("Đã xảy ra lỗi! Vui lòng thử lại sau!");
        }
    }
    
    @FXML
    public void Login(KeyEvent e) throws IOException {
        try {
            if (account_input.getText().length() == 0 || password_input.getText().length() == 0) {
                message.setText("Vui lòng nhập đầy đủ thông tin!");
            } else if (UserService.login(account_input.getText().toLowerCase(), password_input.getText()) == false) {
                message.setText("Tài khoản hoặc mật khẩu không chính xác!");
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("/view/TrangChu.fxml"));
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                Scene scene = new Scene(root, 864, 486);
                scene.getStylesheets().add("/CSS/globalStyle.css");
                stage.setScene(scene);

                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
                stage.show();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            message.setText("Đã xảy ra lỗi! Vui lòng thử lại sau!");
        }
    }
    
    

   
    @FXML
    public void handleExit() {
        sc.handleExit();
    }

    @FXML
    public void onKeyPressEnter(KeyEvent e) throws IOException {
        if (e.getCode().equals(KeyCode.ENTER)) {
           Login(e);
        }
    }

}
