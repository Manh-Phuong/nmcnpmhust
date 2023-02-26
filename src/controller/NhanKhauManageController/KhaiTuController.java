/* 
    Created on : Oct 22, 2022
    Author     : Nguyen Manh Phuong - Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller.NhanKhauManageController;

import model.*;
import Services.NhanKhauService;
import controller.LoginController;
import controller.StageController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Admin
 */
public class KhaiTuController implements Initializable {

    private StageController sc = new StageController();
    private NhanKhauService nhanKhauService = new NhanKhauService();

    @FXML
    private Pane nhankhau_pane;
    @FXML
    private Pane quanlysh_pane;
    @FXML
    private Pane hokhau_pane;
    @FXML
    private Pane thongke_pane;

    @FXML
    private TextField soCMTnguoiKhai_TextField;

    @FXML
    private TextField soCMTnguoiChet_TextField;

    @FXML
    private TextField soGiayKhaiTu_TextField;

    @FXML
    private DatePicker ngayKhai_DatePicker;

    @FXML
    private DatePicker ngayChet_DatePicker;

    @FXML
    private TextArea lyDo_TextArea;

    @FXML
    private Button checkNguoiKhai_Button;

    @FXML
    private Button checkNguoiChet_Button;

    @FXML
    private Button xacNhan_Button;

    @FXML
    private Pane check_pane1;

    @FXML
    private Pane check_pane2;

    @FXML
    public void switchToHoKhauScene(ActionEvent e) throws IOException {
        sc.switchToHoKhauScene(e);
    }

    @FXML
    public void switchToNhanKhauScene(ActionEvent e) throws IOException {
        sc.switchToNhanKhauScene(e);
    }

    @FXML
    public void switchToThongKeScene(ActionEvent e) throws IOException {
        sc.switchToThongKeScene(e);
    }

    @FXML
    public void switchToTrangChuScene(ActionEvent e) throws IOException {
        sc.switchToTrangChuScene(e);
    }

    @FXML
    public void switchToQLSinhHoatScene(ActionEvent e) throws IOException {
        sc.switchToQLSinhHoatScene(e);
    }

    @FXML
    public void switchToThemNhanKhauScene(ActionEvent e) throws IOException {
        sc.switchToThemNhanKhauScene(e);
    }

    @FXML
    public void switchToDangKyTamVangScene(ActionEvent e) throws IOException {
        sc.switchToDangKyTamVangScene(e);
    }

    @FXML
    public void switchToDangKyTamTruScene(ActionEvent e) throws IOException {
        sc.switchToDangKyTamTruScene(e);
    }

    @FXML
    public void switchToKhaiTuScene(ActionEvent e) throws IOException {
        sc.switchToKhaiTuScene(e);
    }

    public void phan_quyen() {
        if (LoginController.user.getRole().equals("to_truong")) {
            quanlysh_pane.setVisible(false);
            quanlysh_pane.setManaged(false);
        }
        if (LoginController.user.getRole().equals("can_bo")) {
            hokhau_pane.setVisible(false);
            hokhau_pane.setManaged(false);

            nhankhau_pane.setVisible(false);
            nhankhau_pane.setManaged(false);

            thongke_pane.setVisible(false);
            thongke_pane.setManaged(false);
        }
    }

    public boolean checkThongTinKhaiTu() {
        if (soGiayKhaiTu_TextField.getText().trim().isEmpty()
                || soCMTnguoiChet_TextField.getText().trim().isEmpty()
                || lyDo_TextArea.getText().trim().isEmpty()
                || soCMTnguoiKhai_TextField.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public void checkCMT_khaitu(ActionEvent e) {
        if (soCMTnguoiKhai_TextField.getText().trim() == "" || soCMTnguoiKhai_TextField.getText().trim().length() != 12) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đúng định dạng CMT/CCCD");
            alert.show();
        } else {
            int res;
            res = nhanKhauService.checkCMT(soCMTnguoiKhai_TextField.getText().trim());
            if (res != -1) {
                check_pane1.setVisible(true);
                if (check_pane2.isVisible() == true) {
                    soGiayKhaiTu_TextField.setDisable(false);
                    ngayChet_DatePicker.setDisable(false);
                    ngayKhai_DatePicker.setDisable(false);
                    lyDo_TextArea.setDisable(false);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Số CMT/CCCD không tồn tại");
                alert.show();
            }
        }
    }

    public void checkCMT_nguoichet(ActionEvent e) {
        if (soCMTnguoiChet_TextField.getText().trim() == "" || soCMTnguoiChet_TextField.getText().trim().length() != 12) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đúng định dạng CMT/CCCD");
            alert.show();
        } else {
            int res;
            res = nhanKhauService.checkCMT(soCMTnguoiChet_TextField.getText().trim());
            if (res != -1) {
                check_pane2.setVisible(true);
                if (check_pane1.isVisible() == true) {
                    soGiayKhaiTu_TextField.setDisable(false);
                    ngayChet_DatePicker.setDisable(false);
                    ngayKhai_DatePicker.setDisable(false);
                    lyDo_TextArea.setDisable(false);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Số CMT/CCCD không tồn tại");
                alert.show();
            }
        }
    }

    public void resetInput() {
        soCMTnguoiKhai_TextField.setText("");
        soCMTnguoiChet_TextField.setText("");
        soGiayKhaiTu_TextField.setText("");
        ngayChet_DatePicker.setValue(null);
        ngayKhai_DatePicker.setValue(null);
        lyDo_TextArea.setText("");

        check_pane1.setVisible(false);
        check_pane2.setVisible(false);

        soGiayKhaiTu_TextField.setDisable(true);
        ngayChet_DatePicker.setDisable(true);
        ngayKhai_DatePicker.setDisable(true);
        lyDo_TextArea.setDisable(true);
    }

    @FXML
    public void xacNhanKhaiTu() throws SQLException {
        try {
            if (checkThongTinKhaiTu()) {
                boolean res = nhanKhauService.addNew(new KhaiTuModel(this.soGiayKhaiTu_TextField.getText().trim(),
                        nhanKhauService.checkCMT(this.soCMTnguoiKhai_TextField.getText().trim()), nhanKhauService.checkCMT(this.soCMTnguoiChet_TextField.getText().trim()),
                        java.sql.Date.valueOf(this.ngayKhai_DatePicker.getValue()), java.sql.Date.valueOf(this.ngayChet_DatePicker.getValue()),
                        this.lyDo_TextArea.getText().trim()));

                if (res) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Khai tử thành công!");
                    alert.show();
                    resetInput();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
                alert.show();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void handleExit() {
        sc.handleExit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phan_quyen();

        check_pane1.setVisible(false);
        check_pane2.setVisible(false);

        soGiayKhaiTu_TextField.setDisable(true);
        ngayChet_DatePicker.setDisable(true);
        ngayKhai_DatePicker.setDisable(true);
        lyDo_TextArea.setDisable(true);
    }

    @FXML
    public void checkFieldPress1(KeyEvent e) {
        check_pane1.setVisible(false);

        soGiayKhaiTu_TextField.setDisable(true);
        ngayChet_DatePicker.setDisable(true);
        ngayKhai_DatePicker.setDisable(true);
        lyDo_TextArea.setDisable(true);
    }

    @FXML
    public void checkFieldPress2(KeyEvent e) {
        check_pane2.setVisible(false);

        soGiayKhaiTu_TextField.setDisable(true);
        ngayChet_DatePicker.setDisable(true);
        ngayKhai_DatePicker.setDisable(true);
        lyDo_TextArea.setDisable(true);
    }

    @FXML
    public void cancelAction(ActionEvent e) throws IOException {
        if (alertcancel() == 1) {
            sc.switchToNhanKhauScene(e);
        }
    }

    public int alertcancel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Cảnh báo");
        alert.setContentText("Bạn có chắc chắn muốn thoát không?");

        ButtonType buttonTypeOne = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            return 1;
        } else {
            return 0;
        }
    }
}
