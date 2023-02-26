/* 
    Created on : Oct 22, 2022
    Author     : Nguyen Trung Hieu
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.HoKhauManageController;

import Services.HoKhauService;
import bean.HoKhauBean;
import bean.MemOfFamily;
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
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.NhanKhauModel;
import model.ThanhVienCuaHoModel;

/**
 *
 * @author T14
 */
public class TachHoKhauController implements Initializable {

    private Map<Integer, String> map = new HashMap<Integer, String>();
    private StageController sc = new StageController();
    private MemOfFamily chuho;
    private List<MemOfFamily> thanhVienMoi = new ArrayList();
    @FXML
    private TextField searchText;
    @FXML
    private TextField chuHoHienTai;
    @FXML
    private TextField maKhuVuc;
    @FXML
    private TextField diaChi;
    @FXML
    private TextField maHoKhauMoi;
    @FXML
    private TextField chuHoMoi;
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
    private TableView<MemOfFamily> nhanKhauMoiTable;
    @FXML
    private TableColumn<MemOfFamily, String> hoTenMoi;
    @FXML
    private TableColumn<MemOfFamily, String> ngaySinhMoi;
    @FXML
    private TableColumn<MemOfFamily, String> quanHeChuHoMoi;

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
    public void switchToQLSinhHoatScene(ActionEvent e) throws IOException {
        sc.switchToQLSinhHoatScene(e);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.map = new HashMap<Integer, String>();
        listHoKhau();
    }

    @FXML
    public void txtFieldPress(KeyEvent e) {
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

        chuHoHienTai.setText(tenchuho.getCellData(hoKhauBean));
        setDataThanhVienTable(hoKhauBean);
    }

    @FXML
    public void tableViewDoubleClick(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                selectNhanKhauTableview();
            }
        }
    }

    @FXML
    public void tableViewMoiDoubleClick(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                selectNhanKhauMoiTableview();
            }
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

    public void selectNhanKhauTableview() {
        MemOfFamily memOfFamily = thanhVienTable.getSelectionModel().getSelectedItem();

        if (this.map.containsKey(memOfFamily.getNhanKhau().getNhanKhauModel().getID()) == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thành viên đã được chọn. Vui lòng chọn thành viên khác!");
            alert.show();
            System.out.println(this.map.size());
            System.out.println(memOfFamily.getNhanKhau().getChungMinhThuModel().getSoCMT());
            
            System.out.println(this.map.get(map.keySet()));
        }
        else {
            TextInputDialog notice = new TextInputDialog();
            notice.setHeaderText("Nhập quan hệ với chủ hộ");
            notice.showAndWait();

            if (notice.getEditor().getText().length() != 0) {
                String quanHe = notice.getEditor().getText();

                if (quanHe.equalsIgnoreCase("Chủ hộ")) {
                    chuHoMoi.setText(memOfFamily.getNhanKhau().getNhanKhauModel().getHoTen());
                    this.chuho = memOfFamily;
                }

                memOfFamily.getThanhVienCuaHoModel().setQuanHeVoiChuHo(quanHe);
                thanhVienMoi.add(memOfFamily);
                this.map.put(memOfFamily.getNhanKhau().getNhanKhauModel().getID(), quanHe);
                setDataMoi(thanhVienMoi);
                
                System.out.println(map.size());

            }
        }

    }

    public void selectNhanKhauMoiTableview() {
        MemOfFamily memOfFamily = nhanKhauMoiTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Cảnh báo");
        alert.setContentText("Bạn có chắc chắn muốn xóa không?");

        ButtonType buttonTypeOne = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            if (memOfFamily.getNhanKhau().getNhanKhauModel().getID() == chuho.getNhanKhau().getNhanKhauModel().getID()) {
                chuHoMoi.clear();
            }
            thanhVienMoi.remove(memOfFamily);
            map.remove(memOfFamily.getNhanKhau().getNhanKhauModel().getID());
            setDataMoi(thanhVienMoi);
        } else {
            System.out.println("Hủy xóa!");
        }
    }

    public void setDataMoi(List<MemOfFamily> list) {
        nhanKhauMoiList = FXCollections.observableList(list);

        hoTenMoi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhau().getNhanKhauModel().getHoTen()));
        ngaySinhMoi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        quanHeChuHoMoi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));

        nhanKhauMoiTable.setItems(nhanKhauMoiList);
    }

//    @FXML
//    public void confirm(ActionEvent e) throws IOException,  ClassNotFoundException, SQLException {
//        if (chuHoHienTai.getText().length() == 0 || maKhuVuc.getText().length() == 0
//                || chuHoMoi.getText().length() == 0 || diaChi.getText().length() == 0
//                || maHoKhauMoi.getText().length() == 0) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Thông báo");
//            alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
//            alert.show();
//        } else {
//            HoKhauBean hoKhauMoi = new HoKhauBean();
//            hoKhauMoi.setChuHo(chuho.getNhanKhau().getNhanKhauModel());
//
//            for (MemOfFamily person : thanhVienMoi) {
//                hoKhauMoi.getListNhanKhauModels().add(person.getNhanKhau().getNhanKhauModel());
//                ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
//                thanhVienCuaHoModel.setIdNhanKhau(person.getNhanKhau().getNhanKhauModel().getID());
//                thanhVienCuaHoModel.setQuanHeVoiChuHo(person.getThanhVienCuaHoModel().getQuanHeVoiChuHo());
//                hoKhauMoi.getListThanhVienCuaHo().add(thanhVienCuaHoModel);
//            }
//
//            hoKhauMoi.getHoKhauModel().setMaKhuVuc(maKhuVuc.getText());
//            hoKhauMoi.getHoKhauModel().setMaHoKhau(maHoKhauMoi.getText());
//            hoKhauMoi.getHoKhauModel().setDiaChi(diaChi.getText());
//
//            HoKhauService hoKhauService = new HoKhauService();
//            hoKhauService.tachHoKhau(hoKhauMoi);
//
//            this.map = new HashMap<String, String>();
//            this.thanhVienMoi = new ArrayList();
//            switchToHoKhauScene(e);
//        }
////    }
    @FXML
    public void confirm(ActionEvent e) throws IOException, ClassNotFoundException, SQLException {

        if (chuHoHienTai.getText().length() == 0 || maKhuVuc.getText().length() == 0
                || chuHoMoi.getText().length() == 0 || diaChi.getText().length() == 0
                || maHoKhauMoi.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng nhập đầy đủ thông tin!");
            alert.show();
        } else {
            HoKhauBean hoKhauMoi = new HoKhauBean();
            hoKhauMoi.setChuHo(chuho.getNhanKhau().getNhanKhauModel());

            for (MemOfFamily person : thanhVienMoi) {
                hoKhauMoi.getListNhanKhauModels().add(person.getNhanKhau().getNhanKhauModel());
                ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
                thanhVienCuaHoModel.setIdNhanKhau(person.getNhanKhau().getNhanKhauModel().getID());
                thanhVienCuaHoModel.setQuanHeVoiChuHo(person.getThanhVienCuaHoModel().getQuanHeVoiChuHo());
                hoKhauMoi.getListThanhVienCuaHo().add(thanhVienCuaHoModel);
            }

            hoKhauMoi.getHoKhauModel().setMaKhuVuc(maKhuVuc.getText());
            hoKhauMoi.getHoKhauModel().setMaHoKhau(maHoKhauMoi.getText());
            hoKhauMoi.getHoKhauModel().setDiaChi(diaChi.getText());

            HoKhauService hoKhauService = new HoKhauService();
            hoKhauService.tachHoKhau(hoKhauMoi);

            this.map = new HashMap<Integer, String>();
            this.thanhVienMoi = new ArrayList();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn đã chuyển thành công!");
            alert.setContentText("Nhấn OK để trở về màn hình hộ khẩu");
            alert.showAndWait();
            
            sc.switchToHoKhauScene(e);
        }
    }
    
        @FXML
    public void exitButton(ActionEvent e) throws IOException {
        int check = alertcancel();
        if (check == 1) {
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
