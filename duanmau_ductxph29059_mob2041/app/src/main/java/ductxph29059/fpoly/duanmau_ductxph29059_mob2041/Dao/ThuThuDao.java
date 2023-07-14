package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.DBHelper.DBHelper;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.ThuThu;

public class ThuThuDao {
    private SQLiteDatabase db;

    public ThuThuDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("maTT",obj.maTT);
        values.put("hoTen",obj.hoTen);
        values.put("matKhau",obj.matKhau);

        return db.insert("ThuThu",null,values);
    }

    public int updatePass(ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("maTT",obj.maTT);
        values.put("hoTen",obj.hoTen);
        values.put("matKhau",obj.matKhau);
        return db.update("ThuThu",values,"maTT=?",new String[]{obj.maTT});
    }

    public int delete(String id){
        return db.delete("ThuThu","maTT=?",new String[]{id});
    }

    // Get tất cả data
    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    // Get theo id
    public ThuThu getID(String id){
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }

    // Get data nhiều tham số
    @SuppressLint("Range")
    private List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThuThu obj = new ThuThu();
            obj.maTT = c.getString(c.getColumnIndex("maTT"));
            obj.hoTen = c.getString(c.getColumnIndex("hoTen"));
            obj.matKhau = c.getString(c.getColumnIndex("matKhau"));
            list.add(obj);
        }
        return list;
    }

    // CheckLogin
    public int checkLogin (String id,String password){
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql,id,password);
        if(list.size()==0)
            return -1;
        return 1;
    }
}
