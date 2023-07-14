package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.LoaiSachDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.SachDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter.LoaiSachSpinnerAdapter;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter.SachAdapter;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.LoaiSach;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.Sach;


public class SachFragment extends Fragment {
    ListView lv;
    ArrayList<Sach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach,edTenSach,edGiaThue;
    Spinner spLS;
    Button btnSave,btnCancel;
    static SachDao dao;
    SachAdapter adapter;
    Sach item;
    LoaiSachSpinnerAdapter loaiSachSpinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach=new ArrayList<>();
    LoaiSachDao loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach;
    int positionLS;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sach, container, false);
        lv = v.findViewById(R.id.lvSach);
        fab = v.findViewById(R.id.fabSach);
        dao = new SachDao(getActivity());
        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);// 0 add
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(),1); //1 update
                return false;
            }
        });
        return  v;
    }
    void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edMaSach = dialog.findViewById(R.id.edMaSach);

        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spLS = dialog.findViewById(R.id.spLoaiSach);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);

        loaiSachDAO = new LoaiSachDao(context);
        listLoaiSach=new ArrayList<LoaiSach>();
        //listThanhVien = new ArrayList<ThanhVien>();
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        loaiSachSpinnerAdapter = new LoaiSachSpinnerAdapter(context,listLoaiSach);
        spLS.setAdapter(loaiSachSpinnerAdapter);
        // lấy mã loại sách
        spLS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoaiSach = listLoaiSach.get(i).maLoai;
                Toast.makeText(context, "Chọn: "+listLoaiSach.get(i).maLoai, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Kiểm tra type insert 0 hay update 1
        edMaSach.setEnabled(false);
        if (type!=0){
            edMaSach.setText(String.valueOf(item.maSach));
            for (int i = 0;i<listLoaiSach.size();i++){
                if (item.maSach==(listLoaiSach.get(i).maLoai)){
                    positionLS = i;
                }
            }
            spLS.setSelection(positionLS);

            edTenSach.setText(item.tenSach);
            edGiaThue.setText(item.giaThue+"");
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new Sach();
                item.tenSach = edTenSach.getText().toString();
                item.giaThue = Integer.parseInt(edGiaThue.getText().toString());
                item.maLoai = maLoaiSach;
                if (validate()>0){

                    if (type == 0) {
                        if (dao.insert(item)>0){
                            Toast.makeText(context, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm sách không thành công", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        item.maSach = Integer.parseInt(edMaSach.getText().toString());
                        if (dao.update(item)>0){
                            Toast.makeText(context, "Cập nhật sách thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Cập nhật sách không thành công", Toast.LENGTH_SHORT).show();

                        }
                    }
                    capNhatLV();
                    dialog.dismiss();

                }
            }
        });
        dialog.show();
    }
    public void xoa(final String id){
        // Sử dụng AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dao.delete(id)>0){
                    Toast.makeText(getContext(), "Xóa sách thành công", Toast.LENGTH_SHORT).show();
                    capNhatLV();
                    dialogInterface.cancel();
                }else {
                    Toast.makeText(getContext(), "Xóa sách không thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capNhatLV(){
        list = (ArrayList<Sach>) dao.getAll();
        adapter = new SachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edTenSach.getText().length()==0||edGiaThue.getText().length()==0){
            Toast.makeText(getContext(), "Dữ liệu không được để trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {

            try{
                Integer.parseInt(edGiaThue.getText().toString());
            }catch (Exception e){
                Toast.makeText(getContext(), "Giá thuê không đúng số nguyên", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

}