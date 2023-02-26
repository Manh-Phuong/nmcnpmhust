/* 
    Created on : Oct 22, 2022
    Author     : Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller.QuanLySHManageController;

import Services.SuKienService;
import bean.HoKhauBean;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ThongKeDiemController implements Initializable {

    private SuKienService sks = new SuKienService();
    @FXML
    private ComboBox<Integer> comboBox;
    @FXML
    private TableView<HoKhauBean> diem_table;
    @FXML
    private TableColumn<HoKhauBean, String> sohokhau_col;
    @FXML
    private TableColumn<HoKhauBean, String> chuho_col;
    @FXML
    private TableColumn<HoKhauBean, CheckBox> diem_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            if (sks.getYearFromSuKien() != null) {
                comboBox.getItems().addAll(sks.getYearFromSuKien());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDiemController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeDiemController.class.getName()).log(Level.SEVERE, null, ex);
        }

        comboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            try {
                showTable();
            } catch (SQLException ex) {
                Logger.getLogger(ThongKeDiemController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThongKeDiemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void showTable() throws SQLException, ClassNotFoundException {
        sohokhau_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoKhauModel().getMaHoKhau()));

        chuho_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getChuHo().getHoTen()));

        diem_col.setCellValueFactory(new PropertyValueFactory<>("tong_diem"));

        diem_table.setItems(new SuKienService().diemso(comboBox.getValue()));

    }

}
