/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.CheckBox;
import model.HoKhauModel;
import model.NhanKhauModel;
import model.ThanhVienCuaHoModel;

/**
 *
 * @author Admin
 */
public class HoKhauBean {
    private HoKhauModel hoKhauModel;
    private NhanKhauModel chuHo;
    private List<NhanKhauModel> listNhanKhauModels;
    private List<ThanhVienCuaHoModel> listThanhVienCuaHo;
    private CheckBox check;
    private int tong_diem;

    public HoKhauBean(HoKhauModel hoKhauModel, NhanKhauModel chuHo, List<NhanKhauModel> listNhanKhauModels, List<ThanhVienCuaHoModel> listThanhVienCuaHo) {
        this.hoKhauModel = hoKhauModel;
        this.chuHo = chuHo;
        this.listNhanKhauModels = listNhanKhauModels;
        this.listThanhVienCuaHo = listThanhVienCuaHo;
    }

    public HoKhauBean(HoKhauModel hoKhauModel, NhanKhauModel chuHo, int tong_diem) {
        this.hoKhauModel = hoKhauModel;
        this.chuHo = chuHo;
        this.tong_diem = tong_diem;
    }
    
    
    public HoKhauBean() {
        this.hoKhauModel = new HoKhauModel();
        this.chuHo = new NhanKhauModel();
        this.listNhanKhauModels = new ArrayList<>();
        this.listThanhVienCuaHo = new ArrayList<>();
    }
    
    public HoKhauBean(HoKhauModel hoKhauModel, NhanKhauModel chuHo, CheckBox check) {
        this.hoKhauModel = hoKhauModel;
        this.chuHo = chuHo;
        this.check = check;
    }

    public int getTong_diem() {
        return tong_diem;
    }

    public void setTong_diem(int tong_diem) {
        this.tong_diem = tong_diem;
    }

    public CheckBox getCheck() {
        return check;
    }

    public void setCheck(CheckBox check) {
        this.check = check;
    }

    
    public HoKhauModel getHoKhauModel() {
        return hoKhauModel;
    }

    public void setHoKhauModel(HoKhauModel hoKhauModel) {
        this.hoKhauModel = hoKhauModel;
    }

    public NhanKhauModel getChuHo() {
        return chuHo;
    }

    public void setChuHo(NhanKhauModel chuHo) {
        this.chuHo = chuHo;
    }

    public List<NhanKhauModel> getListNhanKhauModels() {
        return listNhanKhauModels;
    }

    public void setListNhanKhauModels(List<NhanKhauModel> listNhanKhauModels) {
        this.listNhanKhauModels = listNhanKhauModels;
    }

    public List<ThanhVienCuaHoModel> getListThanhVienCuaHo() {
        return listThanhVienCuaHo;
    }

    public void setListThanhVienCuaHo(List<ThanhVienCuaHoModel> listThanhVienCuaHo) {
        this.listThanhVienCuaHo = listThanhVienCuaHo;
    }
}
