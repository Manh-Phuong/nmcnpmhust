/* 
    Created on : Oct 22, 2022
    Author     : Nguyen Manh Phuong - Bui Anh Tuan
    Teacher    : Trinh Thanh Trung
    Class      : Nhap mon cong nghe phan mem - code: 136813
 */

package controller.NhanKhauManageController;

import bean.NhanKhauBean;
import controller.StageController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ChiTietNhanKhauController implements Initializable {

    StageController sc = new StageController();

    @FXML
    private TextField hoVaTen_TextField;

    @FXML
    private TextField ngaySinh_TextField;

    @FXML
    private TextField nguyenQuan_TextField;

    @FXML
    private TextField danToc_TextField;

    @FXML
    private TextField soCMT_TextField;

    @FXML
    private TextField noiThuongTru_TextField;

    @FXML
    private TextField trinhDoHocVan_TextField;

    @FXML
    private TextField trinhDoNgoaiNgu_TextField;

    @FXML
    private TextField ngheNghiep_TextField;

    @FXML
    private TextField bietDanh_TextField;

    @FXML
    private TextField gioiTinh_TextField;

    @FXML
    private TextField tonGiao_TextField;

    @FXML
    private TextField quocTich_TextField;

    @FXML
    private TextField hoChieuSo_TextField;

    @FXML
    private TextField diaChiHienTai_TextField;

    @FXML
    private TextField trinhDoChuyenMon_TextField;

    @FXML
    private TextField bietTiengDanToc_TextField;

    @FXML
    private TextField noiLamViec_TextField;
    
    @FXML
    private TextField ghiChu_TextField;
    
    @FXML
    private TextField noiTamTru_TextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setEditableInput();
    }

    @FXML
    public void switchToNhanKhauScene(ActionEvent e) throws IOException {
        sc.switchToNhanKhauScene(e);
    }

    public void chiTiet(NhanKhauBean nhanKhauBean) {
        hoVaTen_TextField.setText(nhanKhauBean.getNhanKhauModel().getHoTen());

        ngaySinh_TextField.setText(nhanKhauBean.getNhanKhauModel().getNamSinh().toString());

        nguyenQuan_TextField.setText(nhanKhauBean.getNhanKhauModel().getNguyenQuan());

        danToc_TextField.setText(nhanKhauBean.getNhanKhauModel().getDanToc());

        soCMT_TextField.setText(nhanKhauBean.getChungMinhThuModel().getSoCMT());

        noiThuongTru_TextField.setText(nhanKhauBean.getNhanKhauModel().getNoiThuongTru());

        trinhDoHocVan_TextField.setText(nhanKhauBean.getNhanKhauModel().getTrinhDoHocVan());;

        trinhDoNgoaiNgu_TextField.setText(nhanKhauBean.getNhanKhauModel().getTrinhDoNgoaiNgu());

        ngheNghiep_TextField.setText(nhanKhauBean.getNhanKhauModel().getNgheNghiep());

        bietDanh_TextField.setText(nhanKhauBean.getNhanKhauModel().getBietDanh());

        gioiTinh_TextField.setText(nhanKhauBean.getNhanKhauModel().getGioiTinh());

        tonGiao_TextField.setText(nhanKhauBean.getNhanKhauModel().getTonGiao());

        quocTich_TextField.setText(nhanKhauBean.getNhanKhauModel().getQuocTich());

        hoChieuSo_TextField.setText(nhanKhauBean.getNhanKhauModel().getSoHoChieu());

        diaChiHienTai_TextField.setText(nhanKhauBean.getNhanKhauModel().getDiaChiHienNay());

        trinhDoChuyenMon_TextField.setText(nhanKhauBean.getNhanKhauModel().getTrinhDoChuyenMon());

        bietTiengDanToc_TextField.setText(nhanKhauBean.getNhanKhauModel().getBietTiengDanToc());

        noiLamViec_TextField.setText(nhanKhauBean.getNhanKhauModel().getNoiLamViec());
        
        ghiChu_TextField.setText(nhanKhauBean.getNhanKhauModel().getGhiChu());
        
        noiTamTru_TextField.setText(nhanKhauBean.getNhanKhauModel().getDiaChiMoi());
        
        System.out.println("dia chi moi: " +nhanKhauBean.getNhanKhauModel().getDiaChiMoi());
        System.out.println(nhanKhauBean.getNhanKhauModel().getGhiChu());
        
    }

    public void setEditableInput() {
        hoVaTen_TextField.setEditable(false);

        ngaySinh_TextField.setEditable(false);

        nguyenQuan_TextField.setEditable(false);

        danToc_TextField.setEditable(false);

        soCMT_TextField.setEditable(false);

        noiThuongTru_TextField.setEditable(false);
        
        trinhDoHocVan_TextField.setEditable(false);
        
        trinhDoNgoaiNgu_TextField.setEditable(false);

        ngheNghiep_TextField.setEditable(false);

        bietDanh_TextField.setEditable(false);

        gioiTinh_TextField.setEditable(false);

        tonGiao_TextField.setEditable(false);

        quocTich_TextField.setEditable(false);

        hoChieuSo_TextField.setEditable(false);

        diaChiHienTai_TextField.setEditable(false);

        trinhDoChuyenMon_TextField.setEditable(false);

        bietTiengDanToc_TextField.setEditable(false);

        noiLamViec_TextField.setEditable(false);
        
        ghiChu_TextField.setEditable(false);
        
        noiTamTru_TextField.setEditable(false);
    }
}
