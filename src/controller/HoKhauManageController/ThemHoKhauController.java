/* 
    Created on : Oct 22, 2022
    Author     : Nguyen Trung Hieu
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller.HoKhauManageController;

import Services.HoKhauService;
import bean.HoKhauBean;
import bean.MemOfFamily;
import bean.NhanKhauBean;
import controller.LoginController;
import controller.StageController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.ThanhVienCuaHoModel;

/**
 *
 * @author Admin
 */
public class ThemHoKhauController extends AnimationTimer implements Initializable {

    private StageController sc = new StageController();
    public static List<MemOfFamily> listThanhVien = new ArrayList();
    public static Map<String, String> map = new HashMap<String, String>();
    private NhanKhauBean nhanKhauBean;

    @FXML
    private Pane nhankhau_pane;
    @FXML
    private Pane quanlysh_pane;
    @FXML
    private Pane hokhau_pane;
    @FXML
    private Pane thongke_pane;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Button trangchu_btn;
    @FXML
    private Button hokhau_btn;
    @FXML
    private Button nhankhau_btn;
    @FXML
    private Button thongke_btn;
    @FXML
    private Button quanlysh_btn;
    @FXML
    private TextField maHoKhau;
    @FXML
    private TextField maKhuVuc;
    @FXML
    private TextField diaChi;
    @FXML
    private TextField tenChuHo;
    @FXML
    private TextField ngaySinh;
    @FXML
    private TextField soCCCD;
    @FXML
    private TableView<MemOfFamily> nhanKhauMoiTable;
    @FXML
    private TableColumn<MemOfFamily, String> hoTenMoi;
    @FXML
    private TableColumn<MemOfFamily, String> ngaySinhMoi;
    @FXML
    private TableColumn<MemOfFamily, String> quanHeChuHo;

    private ObservableList<MemOfFamily> nhanKhauMoiList;

    @FXML
    private void switchToHoKhauScene(ActionEvent e) throws IOException {
        sc.switchToHoKhauScene(e);
    }

    @FXML
    private void switchToNhanKhauScene(ActionEvent e) throws IOException {
        sc.switchToNhanKhauScene(e);
    }

    @FXML
    void switchToThongKeScene(ActionEvent e) throws IOException {
        sc.switchToThongKeScene(e);
    }

    @FXML
    private void switchToTrangChuScene(ActionEvent e) throws IOException {
        sc.switchToTrangChuScene(e);
    }

    @FXML
    void switchToQLSinhHoatScene(ActionEvent e) throws IOException {
        sc.switchToQLSinhHoatScene(e);
    }

    @FXML
    public void switchToChonThanhVienScene(ActionEvent e) throws IOException {
        sc.switchToChonThanhVienScene(e);
    }

    @FXML
    public void switchToTimChuHoScene(ActionEvent e) throws IOException {
        sc.switchToTimChuHoScene(e);
    }

    @FXML
    public void switchToChuyenDi(ActionEvent e) throws IOException {
        sc.switchToChuyenDi(e);
    }

    @FXML
    public void switchToTachHoKhauScene(ActionEvent e) throws IOException {
        sc.switchToTachHoKhau(e);
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
    public void handleExit() {
        sc.handleExit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tenChuHo.setDisable(true);
        ngaySinh.setDisable(true);
        soCCCD.setDisable(true);
        ChonThanhVienController.thanhVienMoi = new ArrayList();
        TimChuHoController.nk = null;
        TimChuHoController.map = new HashMap<String, String>();
        this.map = new HashMap<String, String>();
        this.listThanhVien = new ArrayList<>();
        ChonThanhVienController.map = new HashMap<String, String>();
        setListThanhVien(ChonThanhVienController.thanhVienMoi);
        phan_quyen();
        start();
    }


    public void setNhanKhauBean(NhanKhauBean nhanKhauBean) {
        this.nhanKhauBean = nhanKhauBean;
//        if (this.nhanKhauBean.getChungMinhThuModel().getSoCMT() != null){
        tenChuHo.setText(nhanKhauBean.getNhanKhauModel().getHoTen());
        ngaySinh.setText(nhanKhauBean.getNhanKhauModel().getNamSinh().toString());
        soCCCD.setText(nhanKhauBean.getChungMinhThuModel().getSoCMT());
//        }
    }

    public void setListThanhVien(List<MemOfFamily> list) {
        this.listThanhVien = list;
        nhanKhauMoiList = FXCollections.observableList(this.listThanhVien);

        hoTenMoi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhau().getNhanKhauModel().getHoTen()));
        ngaySinhMoi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        quanHeChuHo.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));

        nhanKhauMoiTable.setItems(nhanKhauMoiList);
    }

    @Override
    public void handle(long now) {
        if (TimChuHoController.nk != null) {
            setNhanKhauBean(TimChuHoController.nk);
        }
        if (!ChonThanhVienController.thanhVienMoi.isEmpty()) {
            setListThanhVien(ChonThanhVienController.thanhVienMoi);
        }
    }

    @FXML
    public void confirm(ActionEvent e) throws IOException, ClassNotFoundException, SQLException {

        if (maHoKhau.getText().length() == 0 || maKhuVuc.getText().length() == 0
                || tenChuHo.getText().length() == 0 || diaChi.getText().length() == 0
                || ngaySinh.getText().length() == 0 || soCCCD.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
            alert.show();
        } else {
            HoKhauBean hoKhauBean = new HoKhauBean();
            hoKhauBean.setChuHo(nhanKhauBean.getNhanKhauModel());
            hoKhauBean.getHoKhauModel().setMaHoKhau(maHoKhau.getText().trim());
            hoKhauBean.getHoKhauModel().setDiaChi(diaChi.getText().trim());
            hoKhauBean.getHoKhauModel().setMaKhuVuc(maKhuVuc.getText().trim());
            ThanhVienCuaHoModel chuho = new ThanhVienCuaHoModel();
            chuho.setIdNhanKhau(nhanKhauBean.getNhanKhauModel().getID());
            chuho.setQuanHeVoiChuHo("Chủ hộ");
            this.listThanhVien.forEach(person -> {
                ThanhVienCuaHoModel temp = new ThanhVienCuaHoModel();
                temp.setIdNhanKhau(person.getNhanKhau().getNhanKhauModel().getID());
                temp.setQuanHeVoiChuHo(person.getThanhVienCuaHoModel().getQuanHeVoiChuHo());
                hoKhauBean.getListThanhVienCuaHo().add(temp);
            });
            hoKhauBean.getListThanhVienCuaHo().add(chuho);

            HoKhauService hoKhauService = new HoKhauService();
            hoKhauService.addNew(hoKhauBean);
            ThemHoKhauController.listThanhVien = new ArrayList();
            TimChuHoController.nk = null;
            ThemHoKhauController.map = new HashMap<String, String>();
            TimChuHoController.map = new HashMap<String, String>();
            ChonThanhVienController.map = new HashMap<String, String>();
            ChonThanhVienController.thanhVienMoi = new ArrayList();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn đã thêm thành công!");
            alert.setContentText("Nhấn OK để trở về màn hình hộ khẩu");
            alert.showAndWait();
            sc.switchToHoKhauScene(e);
        }

    }

    @FXML
    public void exitButton(ActionEvent e) throws IOException {
        int check = alertcancel();
        if (check == 1) {
            ThemHoKhauController.listThanhVien = new ArrayList();
            TimChuHoController.nk = null;
            ThemHoKhauController.map = new HashMap<String, String>();
            TimChuHoController.map = new HashMap<String, String>();
            ChonThanhVienController.map = new HashMap<String, String>();
            ChonThanhVienController.thanhVienMoi = new ArrayList();
            sc.switchToHoKhauScene(e);
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
