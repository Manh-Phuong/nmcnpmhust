/* 
    Created on : Oct 22, 2022
    Author     : Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */


package controller;

import Services.HoKhauService;
import Services.SuKienService;
import bean.HoKhauBean;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TinhDiemController implements Initializable {

    private final StageController sc = new StageController();
    private SuKienService sks = new SuKienService();
    private boolean checkEmpty = true; // check các ô điểm danh có trống hay không
    
    @FXML
    private Pane hokhau_pane;
    @FXML
    private Pane nhankhau_pane;
    @FXML
    private Pane thongke_pane;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableView<HoKhauBean> check_table;
    @FXML
    private TableColumn<HoKhauBean, String> sohokhau_col;
    @FXML
    private TableColumn<HoKhauBean, String> chuho_col;
    @FXML
    private TableColumn<HoKhauBean, CheckBox> check_col;

    private SuKienService SKService = new SuKienService();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        phan_quyen();
        try {
            showTable();
        } catch (SQLException ex) {
            Logger.getLogger(TinhDiemController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TinhDiemController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            comboBox.getItems().addAll(SKService.getNameSukien());
        } catch (SQLException ex) {
            Logger.getLogger(TinhDiemController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TinhDiemController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void handleLogout(ActionEvent e) throws IOException {
        sc.handleLogout(e);
    }

    public void phan_quyen() {
        hokhau_pane.setVisible(false);
        hokhau_pane.setManaged(false);

        nhankhau_pane.setVisible(false);
        nhankhau_pane.setManaged(false);

        thongke_pane.setVisible(false);
        thongke_pane.setManaged(false);
    }

    public void showTable() throws SQLException, ClassNotFoundException {
        HoKhauService hoKhauService = new HoKhauService();
        List<HoKhauBean> list = hoKhauService.getListHoKhau();
        for (HoKhauBean a : list){
            a.setCheck(new CheckBox());
        }
        ObservableList<HoKhauBean> oList = FXCollections.observableList(list);
        
        sohokhau_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoKhauModel().getMaHoKhau()));

        chuho_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getChuHo().getHoTen()));

        check_col.setCellValueFactory(new PropertyValueFactory<>("check"));

        // check_table.setItems(new SuKienService().getABC());
        check_table.setItems(oList);

    }

    @FXML
    public void diemDanh() throws SQLException, ClassNotFoundException {
        if (comboBox.getValue() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Vui lòng chọn sự kiện");
            alert.showAndWait();
        } else {
            int id_su_kien = sks.findSuKien(comboBox.getValue());
            check_table.getItems().forEach(element -> {
                if (element.getCheck().isSelected()) {
                    checkEmpty = false;
                    try {
                        sks.diem_danh(element.getHoKhauModel().getMaHoKhau(), id_su_kien);
                        element.getCheck().setSelected(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(TinhDiemController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TinhDiemController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            if (checkEmpty == false) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("Thành công");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("Vui lòng đánh dấu hộ dân tham dự");
                alert.showAndWait();
                
                checkEmpty = true;
            }
        }
    }

    @FXML
    public void switchToBangDiemScene() throws IOException {
        FXMLLoader f = new FXMLLoader(getClass().getResource("/view/BangDiem.fxml"));
        Parent root1 = (Parent) f.load();
        Stage stage1 = new Stage();
        stage1.initModality(Modality.APPLICATION_MODAL);
        stage1.setResizable(false);
        stage1.setScene(new Scene(root1));
        stage1.show();
    }
}
