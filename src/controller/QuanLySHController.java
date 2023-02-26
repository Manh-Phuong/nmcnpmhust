/* 
    Created on : Oct 22, 2022
    Author     : Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller;

import Services.SuKienService;
import controller.QuanLySHManageController.ChiTietSuKienController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.SuKien;

public class QuanLySHController implements Initializable {

    private StageController sc = new StageController();
    private SuKienService SKService = new SuKienService();

    @FXML
    private Pane nhankhau_pane;
    @FXML
    private Pane quanlysh_pane;
    @FXML
    private Pane hokhau_pane;
    @FXML
    private Pane thongke_pane;
    @FXML
    private TextField event;
    @FXML
    private TextField place;
    @FXML
    private TextField start;
    @FXML
    private TextField end;
    @FXML
    private DatePicker time;
    @FXML
    private TextArea content;
    @FXML
    private Label check;
    @FXML
    private TableView<SuKien> sukien_table;
    @FXML
    private TableColumn<SuKien, String> event_col;
    @FXML
    private TableColumn<SuKien, String> place_col;
    @FXML
    private TableColumn<SuKien, String> time_col;
    @FXML
    private TableColumn<SuKien, Integer> id_col;
    @FXML
    private TableColumn<SuKien, String> batDau_col;
    @FXML
    private TableColumn<SuKien, Integer> ketThuc_col;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        phan_quyen();

        try {
            showSuKien();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySHController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLySHController.class.getName()).log(Level.SEVERE, null, ex);
        }

        sukien_table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {

                FXMLLoader f = new FXMLLoader(getClass().getResource("/view/ChiTietSuKien.fxml"));

                Parent root1 = null;
                try {
                    root1 = (Parent) f.load();
                } catch (IOException ex) {
                    Logger.getLogger(QuanLySHController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ChiTietSuKienController controller = f.getController();
                SuKien s = sukien_table.getSelectionModel().getSelectedItem();
                controller.setThongTinSuKien(s);

                Stage stage1 = new Stage();
                stage1.setScene(new Scene(root1));
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.setResizable(false);
                stage1.show();

            }
        });
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
    void switchToTinhDiemScene(ActionEvent e) throws IOException {
        sc.switchToTinhDiemScene(e);
    }

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
    private void switchToChiTietSuKienScene(ActionEvent e) throws IOException {
        sc.switchToChiTietSuKienScene(e);
    }

    @FXML
    void switchToThongKeScene(ActionEvent e) throws IOException {
        sc.switchToThongKeScene(e);
    }

    @FXML
    public void handleExit() {
        sc.handleExit();
    }

    @FXML
    public void handleAddEvent() throws SQLException, ClassNotFoundException {
        String name = event.getText();
        String iplace = place.getText();
        LocalDate itime = time.getValue();
        String icontent = content.getText();
        String start_ = start.getText();
        String end_ = end.getText();

        if (icontent.equals("") || name.equals("") || iplace.equals("") || itime == null || "".equals(start_) || "".equals(end_)) {
            check.setText("Vui lòng nhập đầy đủ thông tin");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
            String convertTime = itime.format(formatter);
            SKService.ThemSuKien(name, iplace, icontent, convertTime, check, start_, end_);
            if (check.getText().contains("thành công")) {
                event.setText("");
                place.setText("");
                time.setValue(null);
                content.setText("");
                start.setText("");
                end.setText("");
                showSuKien();
            }
        }
    }

    @FXML
    public void showSuKien() throws SQLException, ClassNotFoundException {
        event_col.setCellValueFactory(new PropertyValueFactory<>("name"));

        place_col.setCellValueFactory(new PropertyValueFactory<>("place"));

        time_col.setCellValueFactory(new PropertyValueFactory<>("time"));

        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        batDau_col.setCellValueFactory(new PropertyValueFactory<>("start"));
        
        ketThuc_col.setCellValueFactory(new PropertyValueFactory<>("end"));

        sukien_table.setItems(SKService.getSuKien());
    }
}
