/* 
    Created on : Oct 22, 2022
    Author     : Nguyen Manh Phuong - Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller.NhanKhauManageController;

import Services.NhanKhauService;
import model.*;
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
public class DangKyTamVangController implements Initializable {

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
    private TextField soCMT_TextField;

    @FXML
    private TextField maGiayTamVang_TextField;

    @FXML
    private TextField noiTamTru_TextField;

    @FXML
    private DatePicker tuNgay_DatePicker;

    @FXML
    private DatePicker denNgay_DatePicker;

    @FXML
    private TextArea lyDo_TextArea;

    @FXML
    private Button check_Button;

    @FXML
    private Button dangKyTamVang_Button;

    @FXML
    private Pane checkPane;

    private DangKyTamVangController controller = null;

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

    @FXML
    public boolean checkCMT() {
        if (soCMT_TextField.getText().trim() == "" || soCMT_TextField.getText().trim().length() < 12 || soCMT_TextField.getText().trim().length() > 12) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đúng định dạng CMT/CCCD");
            alert.show();
            return false;
        } else {
            int res;

            res = nhanKhauService.checkCMT(soCMT_TextField.getText().trim());
            if (res != -1) {
                checkPane.setVisible(true);

                maGiayTamVang_TextField.setDisable(false);
                noiTamTru_TextField.setDisable(false);
                tuNgay_DatePicker.setDisable(false);
                denNgay_DatePicker.setDisable(false);
                lyDo_TextArea.setDisable(false);
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Số CMT/CCCD không tồn tại");
                alert.show();
                return false;
            }
        }
    }

    public boolean checkThongTinTamVang() {
        if (maGiayTamVang_TextField.getText().trim().isEmpty()
                || noiTamTru_TextField.getText().trim().isEmpty()
                || lyDo_TextArea.getText().trim().isEmpty()
                || soCMT_TextField.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    @FXML
    public void dangKyTamVang() throws SQLException {
        try {
            if (checkThongTinTamVang()) {
                TamVangModel tamVangModel = new TamVangModel();
                tamVangModel.setMaGiayTamVang(this.maGiayTamVang_TextField.getText().trim());
                //checkCMT(soCMT_TextField.getText().trim())admin   1
                tamVangModel.setID(39);
                tamVangModel.setNoiTamTru(this.noiTamTru_TextField.getText().trim());
                tamVangModel.setTuNgay(java.sql.Date.valueOf(this.tuNgay_DatePicker.getValue()));
                tamVangModel.setDenNgay(java.sql.Date.valueOf(this.denNgay_DatePicker.getValue()));
                tamVangModel.setLyDo(this.lyDo_TextArea.getText().trim());

                boolean res = nhanKhauService.addNew(new TamVangModel(39, maGiayTamVang_TextField.getText().trim(), noiTamTru_TextField.getText().trim(), java.sql.Date.valueOf(this.tuNgay_DatePicker.getValue()), java.sql.Date.valueOf(this.denNgay_DatePicker.getValue()), lyDo_TextArea.getText().trim()));
                if (res && checkCMT()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Thành công!");
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

        maGiayTamVang_TextField.setDisable(true);
        noiTamTru_TextField.setDisable(true);
        tuNgay_DatePicker.setDisable(true);
        denNgay_DatePicker.setDisable(true);
        lyDo_TextArea.setDisable(true);
        sc.handleExit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phan_quyen();

        checkPane.setVisible(false);
        maGiayTamVang_TextField.setDisable(true);
        noiTamTru_TextField.setDisable(true);
        tuNgay_DatePicker.setDisable(true);
        denNgay_DatePicker.setDisable(true);
        lyDo_TextArea.setDisable(true);
    }

    public void resetInput() {
        soCMT_TextField.setText("");
        maGiayTamVang_TextField.setText("");
        noiTamTru_TextField.setText("");
        tuNgay_DatePicker.setValue(null);
        denNgay_DatePicker.setValue(null);
        lyDo_TextArea.setText("");

        checkPane.setVisible(false);
        maGiayTamVang_TextField.setDisable(true);
        noiTamTru_TextField.setDisable(true);
        tuNgay_DatePicker.setDisable(true);
        denNgay_DatePicker.setDisable(true);
        lyDo_TextArea.setDisable(true);
    }

    @FXML
    public void checkFieldPress(KeyEvent e) {
        checkPane.setVisible(false);

        maGiayTamVang_TextField.setDisable(true);
        noiTamTru_TextField.setDisable(true);
        tuNgay_DatePicker.setDisable(true);
        denNgay_DatePicker.setDisable(true);
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
