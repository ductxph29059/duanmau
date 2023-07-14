package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "PNLIB";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Thủ thư
        String createTableThuThu =
                "create table ThuThu(" +
                        "maTT TEXT PRIMARY KEY," +
                        "hoTen TEXT NOT NULL," +
                        "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThuThu);

        // Tạo bảng Thành viên
        String createTableThanhVien =
                "create table ThanhVien(" +
                        "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "hoTen TEXT NOT NULL," +
                        "namSinh TEXT NOT NULL)";
        db.execSQL(createTableThanhVien);

        // Tạo bảng Loại sách
        String createTableLoaiSach =
                "create table LoaiSach(" +
                        "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSach);

        // Tạo bảng Sách
        String createTabSach =
                "create table Sach(" +
                        "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tenSach TEXT NOT NULL," +
                        "giaThue INTEGER NOT NULL," +
                        "maLoai INTEGER REFERENCES LoaiSach(maLoai) )";
        db.execSQL(createTabSach);

        // Tạo bảng Phiếu mượn
        String createTablePhieuMuon =
                "create table PhieuMuon(" +
                        "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "maTT TEXT REFERENCES ThuThu(maTT)," +
                        "maTV INTEGER REFERENCES ThanhVien(maTV)," +
                        "maSach INTEGER REFERENCES Sach(maSach)," +
                        "tienThue INTEGER NOT NULL," +
                        "ngay DATE NOT NULL," +
                        "traSach INTEGER NOT NULL )";
        db.execSQL(createTablePhieuMuon);
        db.execSQL(Data_SQLite.INSERT_THU_THU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }

}