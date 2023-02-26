// hokhauchuyendicontroller
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.HoKhauManageController;

import Services.HoKhauService;
import bean.HoKhauBean;
import bean.MemOfFamily;
import static controller.HoKhauManageController.ChonThanhVienController.map;
import static controller.HoKhauManageController.ChonThanhVienController.thanhVienMoi;
import controller.StageController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.NhanKhauModel;
import model.ThanhVienCuaHoModel;

/**
 *
 * @author T14
 */
public class HoKhauChuyenDiController extends AnimationTimer implements Initializable {

    private StageController sc = new StageController();
    private MemOfFamily mem = new MemOfFamily();

    @FXML
    private TextField searchText;

    @FXML
    private TextField tenThanhVien;

    @FXML
    private TextField diaChiChuyenDen;

    @FXML
    private TextField soHoKhau;

    @FXML
    private TextField quanHeChuHoMoi;
    @FXML
    private ComboBox<String> choice_box;

    @FXML
    private TableView<HoKhauBean> hokhau_table;
    @FXML
    private TableColumn<HoKhauBean, String> sohokhau;
    @FXML
    private TableColumn<HoKhauBean, String> tenchuho;
    @FXML
    private TableColumn<HoKhauBean, String> diachi;

    private ObservableList<HoKhauBean> hoKhauList;

    @FXML
    private TableView<MemOfFamily> thanhVienTable;
    @FXML
    private TableColumn<MemOfFamily, String> hoTenThanhVien;
    @FXML
    private TableColumn<MemOfFamily, String> ngaySinhThanhVien;
    @FXML
    private TableColumn<MemOfFamily, String> quanHeChuHo;

    private ObservableList<MemOfFamily> nhanKhauList;

    @FXML
    private void switchToHoKhauScene(ActionEvent e) throws IOException {
        sc.switchToHoKhauScene(e);
    }

    @FXML
    private void switchToNhanKhauScene(ActionEvent e) throws IOException {
        sc.switchToNhanKhauScene(e);
    }

    @FXML
    public void switchToTachHoKhauScene(ActionEvent e) throws IOException {
        sc.switchToTachHoKhau(e);
    }

    @FXML
    public void switchToChuyenDi(ActionEvent e) throws IOException {
        sc.switchToChuyenDi(e);
    }

    @FXML
    void switchToTrangChuScene(ActionEvent e) throws IOException {
        sc.switchToTrangChuScene(e);
    }

    @FXML
    void switchToThemHoKhauScene(ActionEvent e) throws IOException {
        sc.switchToThemHoKhauScene(e);
    }

    @FXML
    void switchToThongKeScene(ActionEvent e) throws IOException {
        sc.switchToThongKeScene(e);
    }

    @FXML
    void handleExit(ActionEvent e) throws IOException {
        sc.handleExit();
    }

    @FXML
    public void switchToQLSinhHoatScene(ActionEvent e) throws IOException {
        sc.switchToQLSinhHoatScene(e);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] options = {"Chuyển đi nơi khác", "Chuyển sang hộ khác",};
        choice_box.getItems().addAll(options);

        listHoKhau();
        diaChiChuyenDen.setDisable(true);
        soHoKhau.setDisable(true);
        quanHeChuHoMoi.setDisable(true);

        start();
    }

    @FXML
    void txtFieldPress(KeyEvent e) {
        if (e.getCode().equals(KeyCode.BACK_SPACE)) {
            if (searchText.getText().length() <= 1) {
                listHoKhau();
            }
        } else if (e.getCode().equals(KeyCode.ENTER)) {
            search();

        }
    }

    @FXML
    public void searchbutton(ActionEvent e) {
        search();
    }

    @FXML
    public void getDataFromCell(MouseEvent e) {
        HoKhauBean hoKhauBean = hokhau_table.getSelectionModel().getSelectedItem();
        if (hoKhauBean == null) {
            return;
        }

        setDataThanhVienTable(hoKhauBean);
    }

    @FXML
    public void getDataFromCellThanhVienTable(MouseEvent e) {
        MemOfFamily memOfFamily = thanhVienTable.getSelectionModel().getSelectedItem();

        if (memOfFamily == null) {
            return;
        }
        this.mem = memOfFamily;
        choice_box.setValue(null);
        tenThanhVien.setText(memOfFamily.getNhanKhau().getNhanKhauModel().getHoTen());
    }

    public void choiceComboBox() {
        if (choice_box.getValue() == null) {
            diaChiChuyenDen.clear();
            soHoKhau.clear();
            quanHeChuHoMoi.clear();

            diaChiChuyenDen.setDisable(true);
            soHoKhau.setDisable(true);
            quanHeChuHoMoi.setDisable(true);
            return;
        } else if ("Chuyển đi nơi khác".equals(choice_box.getValue())) {
            diaChiChuyenDen.setDisable(false);
            soHoKhau.setDisable(true);
            quanHeChuHoMoi.setDisable(true);
        } else if ("Chuyển sang hộ khác".equals(choice_box.getValue())) {
            diaChiChuyenDen.setDisable(true);
            soHoKhau.setDisable(false);
            quanHeChuHoMoi.setDisable(false);
        }
    }

    public void search() {
        HoKhauService hoKhauService = new HoKhauService();
        String keys = searchText.getText();
        keys = keys.trim();
        List<HoKhauBean> list = hoKhauService.search(keys);
        hoKhauList = FXCollections.observableList(list);
        if (hoKhauList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thông tin tìm kiếm không chính xác. Vui lòng kiểm tra lại!");
            alert.show();
        } else {
            sohokhau.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getHoKhauModel().getMaHoKhau()));
            tenchuho.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getChuHo().getHoTen()));
            diachi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getHoKhauModel().getDiaChi()));
            hokhau_table.setItems(hoKhauList);
        }
    }

    public void listHoKhau() {
        HoKhauService hoKhauService = new HoKhauService();
        List<HoKhauBean> list = hoKhauService.getListHoKhau();
        hoKhauList = FXCollections.observableList(list);

        sohokhau.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getHoKhauModel().getMaHoKhau()));
        tenchuho.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getChuHo().getHoTen()));
        diachi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getHoKhauModel().getDiaChi()));

        hokhau_table.setItems(hoKhauList);
    }

    @FXML
    public void confirm(ActionEvent e) throws IOException, ClassNotFoundException {
        if ("Chuyển đi nơi khác".equals(choice_box.getValue()) && diaChiChuyenDen.getText().length() > 0) {
            HoKhauService hoKhauService = new HoKhauService();
            hoKhauService.xoaNhanKhau(this.mem);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thông báo");
            alert.setContentText("Chuyển thành công");
            alert.showAndWait();
            sc.switchToHoKhauScene(e);
        } else if ("Chuyển sang hộ khác".equals(choice_box.getValue()) && soHoKhau.getText().length() > 0 && quanHeChuHoMoi.getText().length() > 0) {
            this.mem.getThanhVienCuaHoModel().setQuanHeVoiChuHo(quanHeChuHoMoi.getText());
            HoKhauService hoKhauService = new HoKhauService();
            if (hoKhauService.themNhanKhau(mem, soHoKhau.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Thông báo");
                alert.setContentText("Chuyển thành công");
                alert.showAndWait();
                sc.switchToHoKhauScene(e);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Cảnh báo");
                alert.setContentText("Vui lòng kiểm tra lại!");
                alert.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Cảnh báo");
            alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
            alert.show();
        }

    }

    @FXML
    public void exitButton(ActionEvent e) throws IOException {
        int check = alertcancel();
        if (check == 1) {
            sc.switchToHoKhauScene(e);
        }

    }

    @FXML
    public void getDataFromThanhVienTable(MouseEvent e) {
        MemOfFamily memOfFamily = thanhVienTable.getSelectionModel().getSelectedItem();
        if (memOfFamily == null) {
            return;
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

    public void setDataThanhVienTable(HoKhauBean hoKhauBean) {

        List<MemOfFamily> list = new ArrayList();
        List<NhanKhauModel> listNhanKhau = hoKhauBean.getListNhanKhauModels();
        List<ThanhVienCuaHoModel> listThanhVien = hoKhauBean.getListThanhVienCuaHo();

        for (int i = 0; i < listNhanKhau.size(); i++) {
            MemOfFamily a = new MemOfFamily();
            a.getNhanKhau().setNhanKhauModel(listNhanKhau.get(i));
            a.setThanhVienCuaHoModel(listThanhVien.get(i));

            list.add(a);
        }

        nhanKhauList = FXCollections.observableList(list);

        hoTenThanhVien.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhau().getNhanKhauModel().getHoTen()));
        //gioiTinh.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhau().getNhanKhauModel().getGioiTinh()));
        ngaySinhThanhVien.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        quanHeChuHo.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));
//        
        thanhVienTable.setItems(nhanKhauList);

    }

    @Override
    public void handle(long now) {
        choiceComboBox();
    }

}