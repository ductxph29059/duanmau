package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.DBHelper.DBHelper;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.PhieuMuon;

public class PhieuMuonDao {
    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT",obj.maTT);
        values.put("maTV",obj.maTV);
        values.put("maSach",obj.maSach);
        values.put("ngay",sdf.format(obj.ngay));
        values.put("tienThue",obj.tienThue);
        values.put("traSach",obj.traSach);
        return db.insert("PhieuMuon",null,values);
    }

    public int update(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT",obj.maTT);
        values.put("maTV",obj.maTV);
        values.put("maSach",obj.maSach);
        values.put("ngay",sdf.format(obj.ngay));
        values.put("tienThue",obj.tienThue);
        values.put("traSach",obj.traSach);
        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(obj.maPM)});
    }

    public int delete(String id){
        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }

    // Get tất cả data
    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    // Get theo id
    public PhieuMuon getID(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }

    // Get data nhiều tham số
    @SuppressLint("Range")
    private List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.maPM = Integer.parseInt(c.getString(c.getColumnIndex("maPM")));
            obj.maTT = c.getString(c.getColumnIndex("maTT"));
            obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.tienThue = c.getInt(c.getColumnIndex("tienThue"));
            obj.traSach = Integer.parseInt(c.getString(c.getColumnIndex("traSach")));
            try {
                obj.ngay = sdf.parse(c.getString(c.getColumnIndex("ngay")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(obj);
        }
        return list;
    }
}
