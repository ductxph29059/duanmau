package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.DBHelper.DBHelper;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.ThanhVien;

public class ThanhVienDao {
    private SQLiteDatabase db;

    public ThanhVienDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.hoTen);
        values.put("namSinh",obj.namSinh);

        return db.insert("ThanhVien",null,values);
    }

    public int update(ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen",obj.hoTen);
        values.put("namSinh",obj.namSinh);
        return db.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(obj.maTV)});
    }

    public int delete(String id){
        return db.delete("ThanhVien","maTV=?",new String[]{id});
    }

    // Get tất cả data
    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    // Get theo id
    public ThanhVien getID(String id){
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }

    // Get data nhiều tham số
    @SuppressLint("Range")
    private List<ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            obj.hoTen = c.getString(c.getColumnIndex("hoTen"));
            obj.namSinh = c.getString(c.getColumnIndex("namSinh"));
            list.add(obj);
        }
        return list;
    }
}
