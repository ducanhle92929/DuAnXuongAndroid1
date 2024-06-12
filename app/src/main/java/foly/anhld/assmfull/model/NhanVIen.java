package foly.anhld.assmfull.model;


import java.io.Serializable;

public class NhanVIen implements Serializable {
    private String MaNV,HoTen,PhongBan;


    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getPhongBan() {
        return PhongBan;
    }

    public void setPhongBan(String phongBan) {
        PhongBan = phongBan;
    }

    public NhanVIen(String maNV, String hoTen, String phongBan) {
        MaNV = maNV;
        HoTen = hoTen;
        PhongBan = phongBan;
    }
}
