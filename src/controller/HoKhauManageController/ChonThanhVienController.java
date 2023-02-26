/* 
    Created on : Oct 22, 2022
    Author     : Nguyen Trung Hieu
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller.HoKhauManageController;

import Services.HoKhauService;
import Services.NhanKhauService;
import bean.MemOfFamily;
import bean.NhanKhauBean;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChonThanhVienController implements Initializable {

    public static List<MemOfFamily> thanhVienMoi = new ArrayList();
    public static  Map<String, String> map;

    @FXML
    private TextField searchText;
    @FXML
    private Button confirmButton;
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
    private TableView<MemOfFamily> nhanKhauMoiTable;
    @FXML
    private TableColumn<MemOfFamily, String> hoTenMoi;
    @FXML
    private TableColumn<MemOfFamily, String> ngaySinhMoi;
    @FXML
    private TableColumn<MemOfFamily, String> quanHeChuHo;

    private ObservableList<MemOfFamily> nhanKhauMoiList;
    private ObservableList<NhanKhauBean> nhanKhauList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        thanhVienMoi = ThemHoKhauController.listThanhVien;
        map = ThemHoKhauController.map;
        System.out.println(this.map.size());
        setData();
        setDataMoi(thanhVienMoi);
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

    @FXML
    public void confirm() { 
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    public void selectNhanKhauTableview() {
        NhanKhauBean nhanKhauBean = nhanKhauTable.getSelectionModel().getSelectedItem();

        HoKhauService hoKhauService = new HoKhauService();
        if (hoKhauService.checkPerson(nhanKhauBean.getNhanKhauModel().getID()) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thành viên đã thuộc hộ khác!");
            alert.show();
        } else {
            if (map.containsKey(nhanKhauBean.getChungMinhThuModel().getSoCMT()) == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Thành viên đã được chọn. Vui lòng chọn thành viên khác!");
                alert.show();
            } else {
                TextInputDialog notice = new TextInputDialog();
                notice.setHeaderText("Nhập quan hệ với chủ hộ");
                notice.showAndWait();

                if (notice.getEditor().getText().length() == 0) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Thông báo");
//                    alert.setHeaderText("Vui lòng nhập quan hệ với chủ hộ!");
//                    alert.show();
                } else {
                    String quanHe = notice.getEditor().getText();
                    MemOfFamily memOfFamily = new MemOfFamily();
                    memOfFamily.setNhanKhau(nhanKhauBean);
                    memOfFamily.getThanhVienCuaHoModel().setQuanHeVoiChuHo(quanHe);
                    thanhVienMoi.add(memOfFamily);
                    map.put(nhanKhauBean.getChungMinhThuModel().getSoCMT(), quanHe);
                    setDataMoi(thanhVienMoi);
                }

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
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            thanhVienMoi.remove(memOfFamily);
            map.remove(memOfFamily.getNhanKhau().getChungMinhThuModel().getSoCMT());
            setDataMoi(thanhVienMoi);
        } else {
            System.out.println("Hủy xóa!");
        }
    }

    public void setDataMoi(List<MemOfFamily> list) {
        nhanKhauMoiList = FXCollections.observableList(list);

        hoTenMoi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhau().getNhanKhauModel().getHoTen()));
        ngaySinhMoi.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getNhanKhau().getNhanKhauModel().getNamSinh().toString()));
        quanHeChuHo.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getThanhVienCuaHoModel().getQuanHeVoiChuHo()));

        nhanKhauMoiTable.setItems(nhanKhauMoiList);
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
}
