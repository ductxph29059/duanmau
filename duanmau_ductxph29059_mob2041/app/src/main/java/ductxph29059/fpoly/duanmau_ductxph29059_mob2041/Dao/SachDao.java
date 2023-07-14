package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.DBHelper.DBHelper;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.Sach;

public class SachDao {
    private SQLiteDatabase db;

    public SachDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach",obj.tenSach);
        values.put("giaThue",obj.giaThue);
        values.put("maLoai",obj.maLoai);
        return db.insert("Sach",null,values);
    }

    public int update(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach",obj.tenSach);
        values.put("giaThue",obj.giaThue);
        values.put("maLoai",obj.maLoai);
        return db.update("Sach",values,"maSach=?",new String[]{String.valueOf(obj.maSach)});
    }

    public int delete(String id){
        return db.delete("Sach","maSach=?",new String[]{id});
    }

    // Get tất cả data
    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    // Get theo id
    public Sach getID(String id){
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }

    // Get data nhiều tham số
    @SuppressLint("Range")
    private List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Sach obj = new Sach();
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.tenSach = c.getString(c.getColumnIndex("tenSach"));
            obj.giaThue = c.getInt(c.getColumnIndex("giaThue"));
            obj.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            list.add(obj);
        }
        return list;
    }
}
