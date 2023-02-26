/* 
    Created on : Oct 22, 2022
    Author     : Nguyen Manh Phuong
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import java.util.List;
import Services.*;
import bean.*;
import controller.NhanKhauManageController.ChiTietNhanKhauController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class NhanKhauController implements Initializable {

    private StageController sc = new StageController();
    private NhanKhauService nhanKhauService = new NhanKhauService();
    private List<NhanKhauBean> listNhanKhauBeans;

    @FXML
    private Pane nhankhau_pane;
    @FXML
    private Pane quanlysh_pane;
    @FXML
    private Pane hokhau_pane;
    @FXML
    private Pane thongke_pane;

    @FXML
    private TableView<NhanKhauBean> nhanKhau_table;

    @FXML
    private TableColumn<NhanKhauBean, String> hoVaTenColumn;

    @FXML
    private TableColumn<NhanKhauBean, String> ngaySinhColumn;

    @FXML
    private TableColumn<NhanKhauBean, String> diaChiHienTaiColumn;

    @FXML
    private TableColumn<NhanKhauBean, Integer> soCMTColumn;

    private ObservableList<NhanKhauBean> nhanKhauList;

    @FXML
    private TextField timKiem_TextFiel;

    @FXML
    private Button tim_Button;


    @FXML
    private void switchToThemNhanKhauScene(ActionEvent e) throws IOException {
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

    @FXML
    public void switchToChiTietNhanKhauScene(ActionEvent e) throws IOException {
        sc.switchToChiTietNhanKhauScene(e);
    }

    @FXML
    public void switchToHoKhauScene(ActionEvent e) throws IOException {
        sc.switchToHoKhauScene(e);
    }

    @FXML
    public void switchToNhanKhauScene(ActionEvent e) throws IOException {
        sc.switchToNhanKhauScene(e);

    }

    @FXML
    public void switchToTrangChuScene(ActionEvent e) throws IOException {
        sc.switchToTrangChuScene(e);
    }

    @FXML
    public void switchToThongKeScene(ActionEvent e) throws IOException {
        sc.switchToThongKeScene(e);
    }

    @FXML
    public void switchToQLSinhHoatScene(ActionEvent e) throws IOException {
        sc.switchToQLSinhHoatScene(e);
    }

    @FXML
    public void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirm?");
        alert.setHeaderText("A  ");
        alert.setContentText("Xóa nhân khẩu này?");

        ButtonType buttonYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonYes, buttonNo);
        alert.initModality(Modality.NONE);
        alert.showAndWait();
    }

    @FXML
    public void handleExit() {
        sc.handleExit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        phan_quyen();

        setData();
        
        nhanKhau_table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {

                FXMLLoader f = new FXMLLoader(getClass().getResource("/view/ChiTietNhanKhau.fxml"));

                Parent root1 = null;
                try {
                    root1 = (Parent) f.load();
                } catch (IOException ex) {
                    Logger.getLogger(NhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                ChiTietNhanKhauController controller = f.getController();


                NhanKhauBean nhanKhauBean = nhanKhau_table.getSelectionModel().getSelectedItem();
                nhanKhauBean = nhanKhauService.getNhanKhau(nhanKhauBean.getChungMinhThuModel().getSoCMT());
         
                controller.chiTiet(nhanKhauBean);
                
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(root1));
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.setResizable(false);
                stage1.show();
            }
        });
    }

    public TableView<NhanKhauBean> getNhanKhau_table() {
        return nhanKhau_table;
    }

    public void setNhanKhau_table(TableView<NhanKhauBean> nhanKhau_table) {
        this.nhanKhau_table = nhanKhau_table;
    }

    @FXML
    public void txtFieldPress(KeyEvent e) {
        if (e.getCode().equals(KeyCode.BACK_SPACE)) {
            if (timKiem_TextFiel.getText().length() <= 1) {
                setData();
            }
        } else if (e.getCode().equals(KeyCode.ENTER)) {
            searchData();

        }
    }

    @FXML
    public void search(ActionEvent e) {
        searchData();
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

    public void setData() {
        NhanKhauService nhanKhauService = new NhanKhauService();
        List<NhanKhauBean> list = nhanKhauService.getListNhanKhau();

        nhanKhauList = FXCollections.observableArrayList(list);
        hoVaTenColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhauModel().getHoTen()));
        ngaySinhColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(String.valueOf(cellData.getValue().getNhanKhauModel().getNamSinh())));
        diaChiHienTaiColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhauModel().getDiaChiHienNay()));
        soCMTColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getNhanKhauModel().getID()));

        nhanKhau_table.setItems(nhanKhauList);
    }

    public void searchData() {
        NhanKhauService nhanKhauService = new NhanKhauService();
        String keys = timKiem_TextFiel.getText();
        keys = keys.trim();
        List<NhanKhauBean> list = nhanKhauService.search(keys);
        nhanKhauList = FXCollections.observableList(list);
        if (nhanKhauList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thông tin tìm kiếm không chính xác. Vui lòng kiểm tra lại!");
            alert.show();
        } else {

            hoVaTenColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhauModel().getHoTen()));
            ngaySinhColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(String.valueOf(cellData.getValue().getNhanKhauModel().getNamSinh())));
            diaChiHienTaiColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhauModel().getDiaChiHienNay()));
            soCMTColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getNhanKhauModel().getID()));

            nhanKhau_table.setItems(nhanKhauList);
        }

    }
}
