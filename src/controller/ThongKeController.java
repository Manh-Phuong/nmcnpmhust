/* 
    Created on : Oct 22, 2022
    Author     : Bui Duc Dang
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */
package controller;

import static Services.MysqlConnection.getMysqlConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.NhanKhauModel;

public class ThongKeController implements Initializable {

    private StageController sc = new StageController();

    @FXML
    private AnchorPane pane1;
    @FXML
    private Pane genderChartPane;
    @FXML
    private Pane ageChartPane;
    @FXML
    private Pane emptyPane;

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
    private PieChart pie_chart;
    @FXML
    private BarChart<String, Integer> bar_chart;
    @FXML
    private ComboBox<String> choice_box;
    @FXML
    private ComboBox<String> choice_box1;
    @FXML
    private Button thongke_button;
    @FXML
    private Pane nhankhau_pane;
    @FXML
    private Pane quanlysh_pane;
    @FXML
    private Pane hokhau_pane;
    @FXML
    private Pane thongke_pane;
    @FXML
    private TextField tuoiTu;
    @FXML
    private TextField tuoiDen;
    @FXML
    private TextField namTu;
    @FXML
    private TextField namDen;
    @FXML
    private TableView<NhanKhauModel> table;
    @FXML
    private TableColumn<NhanKhauModel, Integer> idColumn;

    @FXML
    private TableColumn<NhanKhauModel, String> hoTenColumn;

    @FXML
    private TableColumn<NhanKhauModel, String> ngaySinhColumn;

    @FXML
    private TableColumn<NhanKhauModel, String> gioiTinhColumn;
    @FXML
    private TableColumn<NhanKhauModel, String> diaChiColumn;

    private ObservableList<NhanKhauModel> nhanKhauList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        phan_quyen();

        nhanKhauList = FXCollections.observableArrayList();

        String[] options = {"Toàn Bộ", "Nam", "Nữ",};
        choice_box.getItems().addAll(options);

        String[] options1 = {"Toàn Bộ", "Thường trú", "Tạm trú", "Tạm vắng",};
        choice_box1.getItems().addAll(options1);

    }

    @FXML
    private void switchToTrangChuScene(ActionEvent event) throws IOException {
        sc.switchToTrangChuScene(event);
    }

    @FXML
    private void switchToHoKhauScene(ActionEvent event) throws IOException {
        sc.switchToHoKhauScene(event);
    }

    @FXML
    private void switchToNhanKhauScene(ActionEvent event) throws IOException {
        sc.switchToNhanKhauScene(event);
    }

    @FXML
    void switchToQLSinhHoatScene(ActionEvent e) throws IOException {
        sc.switchToQLSinhHoatScene(e);
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
    private void handleAction(ActionEvent event) {

        if (choice_box.getValue() == null || choice_box1.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn giới tính và tình trạng!");
            alert.show();
        } else if (event.getSource() == thongke_button) {
            for (int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }

            int TuTuoi = -1;
            int denTuoi = 200;
            int tuNam = 0;
            int denNam = 5000;

            if (!this.tuoiTu.getText().trim().isEmpty()) {
                TuTuoi = Integer.parseInt(tuoiTu.getText());
            } else {
                TuTuoi = -1;
            }
            if (!this.tuoiDen.getText().trim().isEmpty()) {
                denTuoi = Integer.parseInt(tuoiDen.getText());
            } else {
                denTuoi = 200;
            }
            if (!this.namTu.getText().trim().isEmpty()) {
                tuNam = Integer.parseInt(namTu.getText());
            }
            if (!this.namDen.getText().trim().isEmpty()) {
                denNam = Integer.parseInt(namDen.getText());
            }

            String gender = choice_box.getValue();
            String Status = choice_box1.getValue();

            String query = "SELECT * FROM nhan_khau "
                    + " INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau"
                    + " LEFT JOIN tam_tru ON nhan_khau.ID = tam_tru.idNhanKhau "
                    + " LEFT JOIN tam_vang ON nhan_khau.ID = tam_vang.idNhanKhau "
                    + " WHERE ROUND(DATEDIFF(CURDATE(),namSinh)/365 , 0) >= "
                    + TuTuoi
                    + " AND ROUND(DATEDIFF(CURDATE(),namSinh)/365 , 0) <= "
                    + denTuoi;
            if (!gender.equalsIgnoreCase("Toàn Bộ")) {
                query += " AND nhan_khau.gioiTinh = '" + gender + "'";
            }
            if (Status.equalsIgnoreCase("Toàn Bộ")) {
                query += " AND (tam_tru.denNgay >= CURDATE() OR tam_tru.denNgay IS NULL)"
                        + " AND (tam_vang.denNgay <= CURDATE() OR tam_vang.denNgay IS NULL)";
            } else if (Status.equalsIgnoreCase("Thường trú")) {
                query += " AND tam_tru.denNgay IS NULL";

            } else if (Status.equalsIgnoreCase("Tạm trú")) {
                query += " AND (YEAR(tam_tru.tuNgay) BETWEEN "
                        + tuNam
                        + " AND "
                        + denNam
                        + ")";
            } else if (Status.equalsIgnoreCase("Tạm vắng")) {
                query += " AND (YEAR(tam_vang.tuNgay) BETWEEN "
                        + tuNam
                        + " AND "
                        + denNam
                        + ")";
            }
            query += " ORDER BY ngayTao DESC";

            try {
                Connection connection = getMysqlConnection();
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                int idNhanKhau = -1;
                while (rs.next()) {

                    NhanKhauModel nkm = new NhanKhauModel(
                            rs.getInt("ID"),
                            rs.getString("maNhanKhau"),
                            rs.getString("hoTen"),
                            rs.getString("bietDanh"),
                            rs.getDate("namSinh"),
                            rs.getString("gioiTinh"),
                            rs.getString("noiSinh"),
                            rs.getString("nguyenQuan"),
                            rs.getString("danToc"),
                            rs.getString("tonGiao"),
                            rs.getString("quocTich"),
                            rs.getString("soHoChieu"),
                            rs.getString("noiThuongTru"),
                            rs.getString("diaChiHienNay"),
                            rs.getString("trinhDoHocVan"),
                            rs.getString("trinhDoChuyenMon"),
                            rs.getString("bietTiengDanToc"),
                            rs.getString("trinhDoNgoaiNgu"),
                            rs.getString("ngheNghiep"),
                            rs.getString("noiLamViec"),
                            rs.getString("tienAn"),
                            rs.getDate("ngayChuyenDen"),
                            rs.getString("lyDoChuyenDen"),
                            rs.getDate("ngayChuyenDi"),
                            rs.getString("lyDoChuyenDi"),
                            rs.getString("diaChiMoi"),
                            rs.getDate("ngayTao"),
                            rs.getInt("idNguoiTao"),
                            rs.getDate("ngayXoa"),
                            rs.getInt("idNguoiXoa"),
                            rs.getString("lyDoXoa"),
                            rs.getString("ghiChu")
                    );

                    nhanKhauList.add(nkm);
                    idColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, Integer>("ID"));
                    hoTenColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("hoTen"));
                    ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("namSinh"));
                    gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("gioiTinh"));
                    diaChiColumn.setCellValueFactory(new PropertyValueFactory<NhanKhauModel, String>("diaChiHienNay"));

                    table.setItems(nhanKhauList);
                }

            } catch (Exception e) {

            }
        }
    }

}
