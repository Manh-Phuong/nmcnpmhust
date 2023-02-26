package model;

import java.util.Date;

/**
 *
 * @author Hai
 */
public class TamVangModel {
    private int ID;
    private int idNhanKhau;
    private String maGiayTamVang;
    private String noiTamTru;
    private Date tuNgay;
    private Date denNgay;
    private String lyDo;
    
    

    public TamVangModel(int ID, int idNhanKhau, String maGiayTamVang, String noiTamTru, Date tuNgay, Date denNgay, String lyDo) {
        this.ID = ID;
        this.idNhanKhau = idNhanKhau;
        this.maGiayTamVang = maGiayTamVang;
        this.noiTamTru = noiTamTru;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
    }

    public TamVangModel(int idNhanKhau, String maGiayTamVang, String noiTamTru, Date tuNgay, Date denNgay, String lyDo) {
        this.idNhanKhau = idNhanKhau;
        this.maGiayTamVang = maGiayTamVang;
        this.noiTamTru = noiTamTru;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
    }
    
    public TamVangModel() {
        
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getMaGiayTamVang() {
        return maGiayTamVang;
    }

    public void setMaGiayTamVang(String maGiayTamVang) {
        this.maGiayTamVang = maGiayTamVang;
    }

    public String getNoiTamTru() {
        return noiTamTru;
    }

    public void setNoiTamTru(String noiTamTru) {
        this.noiTamTru = noiTamTru;
    }

    public Date getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(Date tuNgay) {
        this.tuNgay = tuNgay;
    }

    public Date getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(Date denNgay) {
        this.denNgay = denNgay;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
    
    
}
