package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.DBHelper.DBHelper;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.LoaiSach;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.Sach;

public class LoaiSachDao {
    private SQLiteDatabase db;

    public LoaiSachDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai",obj.tenLoai);
        return db.insert("LoaiSach",null,values);
    }

    public int update(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai",obj.tenLoai);
        return db.update("LoaiSach",values,"maLoai=?",new String[]{String.valueOf(obj.maLoai)});
    }

    public int delete(String id){
        return db.delete("LoaiSach","maLoai=?",new String[]{id});
    }

    // Get tất cả data
    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }

    // Get theo id
    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }

    // Get data nhiều tham số
    @SuppressLint("Range")
    private List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            obj.tenLoai = c.getString(c.getColumnIndex("tenLoai"));
            list.add(obj);
        }
        return list;
    }
}
