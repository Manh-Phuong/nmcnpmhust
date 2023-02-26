package model;

import java.util.Date;

/**
 *
 * @author Hai
 */
public class KhaiTuModel {
    private int ID;
    private String soGiayKhaiTu;
    private int idNguoiKhai;
    private int idNguoiChet;
    private Date ngayKhai;
    private Date ngayChet;
    private String lyDoChet;

    public KhaiTuModel(int ID, String soGiayKhaiTu, int idNguoiKhai, int idNguoiChet, Date ngayKhai, Date ngayChet, String lyDoChet) {
        this.ID = ID;
        this.soGiayKhaiTu = soGiayKhaiTu;
        this.idNguoiKhai = idNguoiKhai;
        this.idNguoiChet = idNguoiChet;
        this.ngayKhai = ngayKhai;
        this.ngayChet = ngayChet;
        this.lyDoChet = lyDoChet;
    }

    public KhaiTuModel(String soGiayKhaiTu, int idNguoiKhai, int idNguoiChet, Date ngayKhai, Date ngayChet, String lyDoChet) {
        this.soGiayKhaiTu = soGiayKhaiTu;
        this.idNguoiKhai = idNguoiKhai;
        this.idNguoiChet = idNguoiChet;
        this.ngayKhai = ngayKhai;
        this.ngayChet = ngayChet;
        this.lyDoChet = lyDoChet;
    }

    
    
    public KhaiTuModel() {
        
    }
    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSoGiayKhaiTu() {
        return soGiayKhaiTu;
    }

    public void setSoGiayKhaiTu(String soGiayKhaiTu) {
        this.soGiayKhaiTu = soGiayKhaiTu;
    }

    public int getIdNguoiKhai() {
        return idNguoiKhai;
    }

    public void setIdNguoiKhai(int idNguoiKhai) {
        this.idNguoiKhai = idNguoiKhai;
    }

    public int getIdNguoiChet() {
        return idNguoiChet;
    }

    public void setIdNguoiChet(int idNguoiChet) {
        this.idNguoiChet = idNguoiChet;
    }

    public Date getNgayKhai() {
        return ngayKhai;
    }

    public void setNgayKhai(Date ngayKhai) {
        this.ngayKhai = ngayKhai;
    }

    public Date getNgayChet() {
        return ngayChet;
    }

    public void setNgayChet(Date ngayChet) {
        this.ngayChet = ngayChet;
    }

    public String getLyDoChet() {
        return lyDoChet;
    }

    public void setLyDoChet(String lyDoChet) {
        this.lyDoChet = lyDoChet;
    }
    
    
}
