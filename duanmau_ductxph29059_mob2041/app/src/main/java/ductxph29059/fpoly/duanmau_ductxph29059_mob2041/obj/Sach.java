package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj;

public class Sach {
    public int maSach;
    public String tenSach;
    public int giaThue;
    public int maLoai;

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public Sach(int maSach, String tenSach, int giaThue, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }

    public Sach() {
    }
}
