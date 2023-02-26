/* 
    Created on : Oct 22, 2022
    Author     : Bui Anh Tuan - Nguyen Manh Phuong - Nguyen Trung Hieu
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */

package controller;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.UserModel;


public class StageController {

    public static Stage stage;
    public static Stage stage_popup;
    public static Scene scene;
    public static Parent root;

    public static FXMLLoader x;

    @FXML
    public void switchToQuanLySHScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/QuanLySuKien.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToHoKhauScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/HoKhau.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToNhanKhauScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/NhanKhau.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToTrangChuScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/TrangChu.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    @FXML
    public void switchToThongKeScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/ThongKe.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    @FXML
    public void switchToQLSinhHoatScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/QuanLySuKien.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    @FXML
    public void switchToThemNhanKhauScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ThemNhanKhau.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToThemHoKhauScene(MouseEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ThemHoKhau.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    // lam phan nhan khau
    @FXML
    public void switchToDangKyTamVangScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DangKyTamVang.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToDangKyTamTruScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DangKyTamTru.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToKhaiTuScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/KhaiTu.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToChiTietNhanKhauScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ChiTietNhanKhau.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToChuyenDi(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HoKhauChuyenDi.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    @FXML
    public void switchToTachHoKhau(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TachHoKhau.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    @FXML
    public void switchToTinhDiemScene(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DiemDanh.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    @FXML
    public void switchToThongTinHoKhauScene(MouseEvent e) throws IOException {
        FXMLLoader fl = new FXMLLoader(getClass().getResource("/view/ThongTinHoKhau.fxml"));
        Parent root1 = (Parent) fl.load();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root1));
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setResizable(false);
        stage1.show();
    }

    @FXML
    public void switchToChonThanhVienScene(ActionEvent e) throws IOException {
        FXMLLoader fl = new FXMLLoader(getClass().getResource("/view/ChonThanhVien.fxml"));
        Parent root1 = (Parent) fl.load();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root1));
        stage1.show();
    }

    @FXML
    public void switchToTimChuHoScene(ActionEvent e) throws IOException {
        FXMLLoader fl = new FXMLLoader(getClass().getResource("/view/TimChuHo.fxml"));
        Parent root1 = (Parent) fl.load();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root1));
        stage1.show();
    }

    @FXML
    public void switchToDetailScene() throws IOException {
        FXMLLoader fl = new FXMLLoader(getClass().getResource("/view/ChiTietHoKhau.fxml"));
        root = (Parent) fl.load();
        stage_popup = new Stage();
        stage_popup.setScene(new Scene(root));
        stage_popup.show();
    }

        @FXML
    public void switchToThemThanhVien() throws IOException {
        FXMLLoader fl = new FXMLLoader(getClass().getResource("/view/ThemThanhVien.fxml"));
        root = (Parent) fl.load();
        stage_popup = new Stage();
        stage_popup.setScene(new Scene(root));
        stage_popup.show();
    }
    @FXML
    public void switchToThemHoKhauScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/ThemHoKhau.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    @FXML
    public void switchToDangNhapScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/DangNhap.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    @FXML
    public void switchToDangNhapScene(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/DangNhap.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add("/CSS/globalStyle.css");
        stage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.show();
    }

    @FXML
    public void switchToChiTietSuKienScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/ChiTietSuKien.fxml"));

//        FXMLLoader fl = new FXMLLoader(getClass().getResource("/view/ChiTietSuKien.fxml"));
//        root = (Parent) fl.load();
//      stage_popup = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Comfirm?");
        alert.setHeaderText("Xác nhận!");
        alert.setContentText("Thoát chương trình?");
        alert.initStyle(StageStyle.UNDECORATED);
        ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonYes, buttonNo);
        alert.initModality(Modality.APPLICATION_MODAL);

//        DialogPane dialog = alert.getDialogPane();
//
//        dialog.getStylesheets().add(getClass().getResource("/CSS/globalStyle.css").toString());
//        dialog.getStyleClass().add("dialog");
        Optional<ButtonType> res = alert.showAndWait();
        if (res.get() == buttonYes) {
            System.exit(0);
        }

    }

    @FXML
    public void handleExit(ActionEvent e) throws IOException {
//   

        FXMLLoader fl = new FXMLLoader(getClass().getResource("/view/ChiTietSuKien.fxml"));
//        root = (Parent) fl.load();
//        stage_popup = new Stage();
//        stage_popup.setScene(new Scene(root));
//        stage_popup.initModality(Modality.APPLICATION_MODAL);
//        stage_popup.setResizable(false);
        stage_popup.close();
    }

    @FXML
    public void handleLogout(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Xác nhận!");
        alert.setContentText("Bạn chắc chắn muốn đăng xuất?");
        alert.initStyle(StageStyle.DECORATED);
        alert.setResizable(false);
        ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonYes, buttonNo);
        alert.initModality(Modality.APPLICATION_MODAL);

        DialogPane dialog = alert.getDialogPane();
//
//        dialog.getStylesheets().add(getClass().getResource("/CSS/globalStyle.css").toString());
//        dialog.getStyleClass().add("dialog");
//        dialog.setEffect(new DropShadow(2d, 0d, +2d, Color.BLACK));

        Optional<ButtonType> res = alert.showAndWait();
        if (res.get() == buttonYes) {
            switchToDangNhapScene(e);
            LoginController.user = new UserModel("", "");
        }
    }

}
