/* 
    Created on : Oct 22, 2022
    Author     : Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller;

import Services.HoKhauService;
import Services.NhanKhauService;
import bean.HoKhauBean;
import bean.NhanKhauBean;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import model.UserModel;

public class TrangChuController implements Initializable {

    private StageController sc = new StageController();
    private NhanKhauService nhanKhauService = new NhanKhauService();
    private HoKhauService hoKhauService = new HoKhauService();

    @FXML
    private Button hokhau_btn;
    @FXML
    private Button nhankhau_btn;
    @FXML
    private Pane nhankhau_pane;
    @FXML
    private Pane quanlysh_pane;
    @FXML
    private Pane quanlydiem_pane;
    @FXML
    private Pane hokhau_pane;
    @FXML
    private Pane thongke_pane;
    @FXML
    private Label toTruong;
    @FXML
    private Label nhanKhau;
    @FXML
    private Label hoKhau;

    @FXML
    private void switchToHoKhauScene(ActionEvent e) throws IOException {
        sc.switchToHoKhauScene(e);
    }

    @FXML
    private void switchToNhanKhauScene(ActionEvent e) throws IOException {
        sc.switchToNhanKhauScene(e);
    }

    @FXML
    private void switchToTrangChuScene(ActionEvent e) throws IOException {
        sc.switchToTrangChuScene(e);
    }

    @FXML
    void switchToThongKeScene(ActionEvent e) throws IOException {
        sc.switchToThongKeScene(e);
    }

    @FXML
    void switchToQLSinhHoatScene(ActionEvent e) throws IOException {
        sc.switchToQLSinhHoatScene(e);
    }

    @FXML
    void switchToTinhDiemScene(ActionEvent e) throws IOException {
        sc.switchToTinhDiemScene(e);
    }

    @FXML
    public void handleLogout(MouseEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Xác nhận!");
        alert.setContentText("Bạn chắc chắn muốn đăng xuất?");
        alert.initStyle(StageStyle.DECORATED);
        alert.setResizable(false);
        ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonYes, buttonNo);
        alert.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> res = alert.showAndWait();
        if (res.get() == buttonYes) {
            sc.switchToDangNhapScene(e);
            LoginController.user = new UserModel("", "");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phan_quyen();
        
        setData();

    }

    public void phan_quyen() {
        if (LoginController.user.getRole().equals("to_truong")) {
            quanlysh_pane.setVisible(false);
            quanlysh_pane.setManaged(false);

            quanlydiem_pane.setVisible(false);
            quanlydiem_pane.setManaged(false);
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

    public void setData() {
        List<NhanKhauBean> listNk = new ArrayList<>();
        listNk = nhanKhauService.getListNhanKhau();
        List<HoKhauBean> listHk = new ArrayList<>();
        listHk = hoKhauService.getListHoKhau();

        int numberOfNhanKhau = 0;
        int numberOfHoKhau = 0;

        for (NhanKhauBean n : listNk) {
            numberOfNhanKhau++;
        }
        for (HoKhauBean n : listHk) {
            numberOfHoKhau++;
        }
        
        nhanKhau.setText(String.valueOf(numberOfNhanKhau));
        System.out.println(nhanKhau.getText());
        hoKhau.setText(String.valueOf(numberOfHoKhau));

    }
}
