/* 
    Created on : Oct 22, 2022
    Author     : Nguyen Trung Hieu
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813

    
 */
package controller;

import Services.HoKhauService;
import bean.HoKhauBean;
import controller.HoKhauManageController.ThongTinHoKhauController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

public class HoKhauController implements Initializable {

    private final StageController sc = new StageController();

    @FXML
    private Pane nhankhau_pane;
    @FXML
    private Pane quanlysh_pane;
    @FXML
    private Pane hokhau_pane;
    @FXML
    private Pane thongke_pane;
    @FXML
    private TextField searchText;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Choice box

        phan_quyen();

        HoKhauService hoKhauService = new HoKhauService();
        List<HoKhauBean> list = hoKhauService.getListHoKhau();
        hoKhauList = FXCollections.observableList(list);

        sohokhau.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getHoKhauModel().getMaHoKhau()));
        tenchuho.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getChuHo().getHoTen()));
        diachi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getHoKhauModel().getDiaChi()));

        hokhau_table.setItems(hoKhauList);
    }

    private void switchToHoKhauScene(ActionEvent e) throws IOException {
        sc.switchToHoKhauScene(e);
    }

    @FXML
    private void switchToNhanKhauScene(ActionEvent e) throws IOException {
        sc.switchToNhanKhauScene(e);
    }

    public void switchToTachHoKhauScene(ActionEvent e) throws IOException {
        sc.switchToTachHoKhau(e);
    }

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

    void switchToThongTinHoKhau(MouseEvent e) throws IOException {
        sc.switchToThongTinHoKhauScene(e);
    }

    @FXML
    void switchToDetailScene(ActionEvent e) throws IOException {
        sc.switchToDetailScene();
    }

    @FXML
    void switchToQLSinhHoatScene(ActionEvent e) throws IOException {
        sc.switchToQLSinhHoatScene(e);
    }

    @FXML
    public void switchToTachHoKhau(ActionEvent e) throws IOException {
        sc.switchToTachHoKhau(e);
    }

    @FXML
    void txtFieldPress(KeyEvent e) {
        if (e.getCode().equals(KeyCode.BACK_SPACE)) {
            if (searchText.getText().length() <= 1) {
                HoKhauService hoKhauService = new HoKhauService();
                List<HoKhauBean> list = hoKhauService.getListHoKhau();
                hoKhauList = FXCollections.observableList(list);

                sohokhau.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getHoKhauModel().getMaHoKhau()));
                tenchuho.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getChuHo().getHoTen()));
                diachi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getHoKhauModel().getDiaChi()));

                hokhau_table.setItems(hoKhauList);
            }
        } else if (e.getCode().equals(KeyCode.ENTER)) {
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
    }

    @FXML
    public void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirm?");
        alert.setHeaderText("A  ");
        alert.setContentText("Xóa hộ khẩu này?");

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
    public void search(ActionEvent e) {
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

    public void tableViewDoubleClick(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                HoKhauBean hoKhauBean = hokhau_table.getSelectionModel().getSelectedItem();
                if (hoKhauBean == null) {
                    return;
                }
                ThongTinHoKhauController thongTinHoKhauController = new ThongTinHoKhauController();
                thongTinHoKhauController.setHoKhauBean(hoKhauBean);
                switchToThongTinHoKhau(mouseEvent);
            }
        }
    }

    @FXML
    public void setData(ActionEvent e) throws IOException {
        HoKhauService hoKhauService = new HoKhauService();
        List<HoKhauBean> list = hoKhauService.getListHoKhau();
        hoKhauList = FXCollections.observableList(list);

        sohokhau.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getHoKhauModel().getMaHoKhau()));
        tenchuho.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getChuHo().getHoTen()));
        diachi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getHoKhauModel().getDiaChi()));

        hokhau_table.setItems(hoKhauList);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Cập nhật thành công!");
        alert.show();
    }
}
