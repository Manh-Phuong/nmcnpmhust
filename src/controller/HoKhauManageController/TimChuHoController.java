/* 
    Created on : Oct 22, 2022
    Author     : Nguyen Trung Hieu
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller.HoKhauManageController;

import Services.HoKhauService;
import Services.NhanKhauService;
import bean.NhanKhauBean;
import controller.StageController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author T14
 */
public class TimChuHoController implements Initializable {

    StageController sc = new StageController();
    public static NhanKhauBean nk = null;
    public static Map<String, String> map = new HashMap<String, String>();
    @FXML
    private TextField searchText;
    @FXML
    private TableView<NhanKhauBean> nhanKhauTable;
    @FXML
    private TableColumn<NhanKhauBean, String> hoTen;
    @FXML
    private TableColumn<NhanKhauBean, String> gioiTinh;
    @FXML
    private TableColumn<NhanKhauBean, String> ngaySinh;
    @FXML
    private TableColumn<NhanKhauBean, String> soCCCD;

    @FXML
    private Button confirmButton;
    private ObservableList<NhanKhauBean> nhanKhauList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        map = ThemHoKhauController.map;
        setData();
    }

    public void setData() {
        NhanKhauService nhanKhauService = new NhanKhauService();
        List<NhanKhauBean> list = nhanKhauService.getListNhanKhau();

        nhanKhauList = FXCollections.observableList(list);

        hoTen.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhauModel().getHoTen()));
        gioiTinh.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhauModel().getGioiTinh()));
        ngaySinh.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhauModel().getNamSinh().toString()));
        soCCCD.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getChungMinhThuModel().getSoCMT()));

        nhanKhauTable.setItems(nhanKhauList);
    }

    public void search() {
        NhanKhauService nhanKhauService = new NhanKhauService();
        String keys = searchText.getText();
        keys = keys.trim();
        List<NhanKhauBean> list = new ArrayList();
        NhanKhauBean nhanKhauBean = nhanKhauService.getNhanKhau(keys);
        list.add(nhanKhauBean);
        nhanKhauList = FXCollections.observableList(list);
        if (nhanKhauBean.getChungMinhThuModel().getSoCMT() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thông tin tìm kiếm không chính xác. Vui lòng kiểm tra lại!");
            alert.show();
        } else {
            hoTen.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhauModel().getHoTen()));
            gioiTinh.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhauModel().getGioiTinh()));
            ngaySinh.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhauModel().getNamSinh().toString()));
            soCCCD.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getChungMinhThuModel().getSoCMT()));

            nhanKhauTable.setItems(nhanKhauList);
        }
    }

    @FXML
    public void txtFieldPress(KeyEvent e) {
        if (e.getCode().equals(KeyCode.BACK_SPACE)) {
            if (searchText.getText().length() <= 1) {
                setData();
            }
        } else if (e.getCode().equals(KeyCode.ENTER)) {
            search();
        }

    }

    @FXML
    public void searchButton(ActionEvent e) {
        search();
    }

    @FXML
    public void tableViewDoubleClick(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                NhanKhauBean nhanKhauBean = nhanKhauTable.getSelectionModel().getSelectedItem();

                HoKhauService hoKhauService = new HoKhauService();
                if (hoKhauService.checkPerson(nhanKhauBean.getNhanKhauModel().getID()) == false) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Thành viên đã thuộc hộ khác!");
                    alert.show();
                } else if (ChonThanhVienController.map.containsKey(nhanKhauBean.getChungMinhThuModel().getSoCMT())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Thành viên đã được chọn!");
                    alert.show();
                } else {
                    nk = nhanKhauBean;
                    if (!map.isEmpty()) {
                        map.clear();
                    }
                    map.put(nk.getChungMinhThuModel().getSoCMT(), "chủ hộ");
                    ThemHoKhauController.map = map;
                }
            }
        }
    }

    @FXML
    public void confirm(ActionEvent e) throws IOException {
        NhanKhauBean nhanKhauBean = nhanKhauTable.getSelectionModel().getSelectedItem();

        HoKhauService hoKhauService = new HoKhauService();
        if (hoKhauService.checkPerson(nhanKhauBean.getNhanKhauModel().getID()) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thành viên đã thuộc hộ khác!");
            alert.show();
        } else if (ChonThanhVienController.map.containsKey(nhanKhauBean.getChungMinhThuModel().getSoCMT())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thành viên đã được chọn!");
            alert.show();
        } else {
            nk = nhanKhauBean;
            if (!map.isEmpty()) {
                map.clear();
            }
            map.put(nk.getChungMinhThuModel().getSoCMT(), "chủ hộ");
            ThemHoKhauController.map = map;
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        }
    }
}
