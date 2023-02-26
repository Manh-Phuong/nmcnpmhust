/* 
    Created on : Oct 22, 2022
    Author     : Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
    Cung cấp các phương thức lấy dữ liệu từ cơ sở dữ liệu trong nghiệp vụ sự kiện
 */

package Services;

import bean.HoKhauBean;
import controller.LoginController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.HoKhauModel;
import model.NhanKhauModel;
import model.SuKien;

/**
 *
 * @author Admin
 */
public class SuKienService {

    public void ThemSuKien(String name, String place, String content, String time, Label check, String start, String end) throws SQLException, ClassNotFoundException {
        Connection conn = MysqlConnection.getMysqlConnection();
        PreparedStatement pst = null;
        String sql = "insert into su_kien (name, place, content,time, start, end) values(?,?,?,?,?,?)";
        System.out.println(time);
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, place);
            pst.setString(3, content);
            pst.setString(4, time);
            pst.setString(5, start);
            pst.setString(6, end);
            
            pst.execute();
            check.setText("Thêm sự kiện thành công!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ObservableList getSuKien() throws SQLException, ClassNotFoundException {
        Connection conn = MysqlConnection.getMysqlConnection();
        ObservableList su_kien = FXCollections.observableArrayList();
        ResultSet result = null;
        String sql = "select * from su_kien ";
        try {
            Statement statement = conn.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                su_kien.add(new SuKien(result.getInt("id"), result.getString("name"), result.getString("place"), result.getString("time"), result.getString("content"), result.getString("start"), result.getString("end")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return su_kien;
    }

    public List<String> getNameSukien() throws SQLException, ClassNotFoundException {
        Connection conn = MysqlConnection.getMysqlConnection();
        List<String> listName = new ArrayList<>();
        String sql = "select name from su_kien";
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                listName.add(result.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listName;
    }

//    public ObservableList getABC() throws SQLException, ClassNotFoundException {
//        Connection conn = MysqlConnection.getMysqlConnection();
//        ObservableList res = FXCollections.observableArrayList();
//        String sql = "SELECT * from ho_khau as hk join nhan_khau as nk on nk.ID = hk.idChuHo";
//        try {
//            Statement statement = conn.createStatement();
//            ResultSet result = statement.executeQuery(sql);
//            while (result.next()) {
//                HoKhauModel hk;
//                hk = new HoKhauModel(result.getInt("id"), result.getString("maHoKhau"), result.getInt("idChuHo"), result.getString("maKhuVuc"), result.getString("diaChi"), result.getDate("ngayLap"), result.getDate("ngayChuyenDi"), result.getString("lyDoChuyen"), result.getInt("nguoiThucHien"));
//                NhanKhauModel nk;
//                nk = new NhanKhauModel(result.getInt("ID"), result.getString("maNhanKhau"), result.getString("hoTen"), result.getString("bietDanh"), result.getDate("namSinh"), result.getString("gioiTinh"), result.getString("noiSinh"), result.getString("nguyenQuan"), result.getString("danToc"), result.getString("tonGiao"), result.getString("quocTich"), result.getString("soHoChieu"), result.getString("noiThuongTru"), result.getString("diaChiHienNay"), result.getString("trinhDoHocVan"), result.getString("trinhDoChuyenMon"), null, null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null);
//                res.add(new HoKhauBean(hk, nk, new CheckBox()));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return res;
//    }

    public ObservableList diemso(int nam) throws SQLException, ClassNotFoundException {
        Connection conn = MysqlConnection.getMysqlConnection();
        ObservableList res = FXCollections.observableArrayList();
        String sql = "SELECT *,COUNT(id_sukien) as Tongdiem FROM `tham_du` as td join ho_khau as hk on hk.maHoKhau = td.so_ho_khau join nhan_khau as nk on hk.idChuHo  = nk.ID join su_kien as sk on sk.id = td.id_sukien WHERE YEAR(sk.time) = " + nam + " GROUP BY hk.maHoKhau order by Tongdiem DESC";
        try  {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                HoKhauModel hk;
                hk = new HoKhauModel(result.getInt("id"), result.getString("maHoKhau"), result.getInt("idChuHo"), result.getString("maKhuVuc"),result.getString("diaChi"), result.getDate("ngayLap"), result.getDate("ngayChuyenDi"), result.getString("lyDoChuyen"), result.getInt("nguoiThucHien"));
                NhanKhauModel nk;
                nk = new NhanKhauModel(result.getInt("ID"), result.getString("maNhanKhau"), result.getString("hoTen"), result.getString("bietDanh"), result.getDate("namSinh"), result.getString("gioiTinh"), result.getString("noiSinh"),result.getString("nguyenQuan"),result.getString("danToc"),result.getString("tonGiao"),result.getString("quocTich"),result.getString("soHoChieu"),result.getString("noiThuongTru"),result.getString("diaChiHienNay"),result.getString("trinhDoHocVan"),result.getString("trinhDoChuyenMon"), null, null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null);
                res.add(new HoKhauBean(hk, nk, result.getInt("Tongdiem")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    
    public int diem_danh(String shk, int id) throws SQLException, ClassNotFoundException {
        Connection conn = MysqlConnection.getMysqlConnection();
        PreparedStatement pst = null;
        String sql = "insert into tham_du (so_ho_khau, id_sukien) values(?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, shk);
            pst.setInt(2, id);
            pst.execute();
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        }
    }

    public int findSuKien(String name) throws SQLException, ClassNotFoundException {
        Connection conn = MysqlConnection.getMysqlConnection();
        String sql = "SELECT * from su_kien where name like \"" + name+ "\"";

        SuKien res = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                res = new SuKien(result.getInt("id"), result.getString("name"), result.getString("place"), result.getString("time"), result.getString("content"), result.getString("start"), result.getString("end"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res.getId();
    }

    public ObservableList getYearFromSuKien() throws SQLException, ClassNotFoundException {
        Connection conn = MysqlConnection.getMysqlConnection();
        ObservableList res = FXCollections.observableArrayList();
        String sql = "SELECT distinct year(time) as nam from su_kien order by nam desc";
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                res.add(result.getInt("nam"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public int updateSuKien(int id, String ten, String diaDiem, String thoiGian, String noiDung, String batDau, String ketThuc) throws SQLException, ClassNotFoundException {
        Connection conn = MysqlConnection.getMysqlConnection();
        PreparedStatement pst = null;
        String sql = "update su_kien set name='" + ten + "', place = '" + diaDiem + "', time ='" + thoiGian +"', content='" + noiDung + "', start='" +batDau +"',end='"+ketThuc+ "' where id = " + id;
        try {
            pst = conn.prepareStatement(sql);
            pst.execute();
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        }
    }
}
