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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.LoaiSachDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter.LoaiSachAdapter;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.LoaiSach;


public class LoaiSachFragment extends Fragment {

    ListView lv;
    ArrayList<LoaiSach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLS,edTenLS;
    Button btnSave,btnCancel;
    static LoaiSachDao dao;
    LoaiSachAdapter adapter;
    LoaiSach item;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lv = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fabLoaiSach);
        dao = new LoaiSachDao(getActivity());
        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);// 0.Add
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(),1);// 1.Update
                return false;
            }
        });
        return  v;
    }
    protected void openDialog(final Context context, final int type){
        // custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loaisach_dialog);
        edMaLS = dialog.findViewById(R.id.edMaLS);
        edTenLS = dialog.findViewById(R.id.edTenLS);
        btnCancel = dialog.findViewById(R.id.btnCancelLS);
        btnSave = dialog.findViewById(R.id.btnSaveLS);
        // Kiểm tra type insert 0 hay update 1
        edMaLS.setEnabled(false);
        if (type != 0){
            edMaLS.setText(String.valueOf(item.maLoai));
            edTenLS.setText(item.tenLoai);
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
                item = new LoaiSach();
                item.tenLoai = edTenLS.getText().toString();
                if (validate()>0){
                    if (type==0){
                        // type = 0 (insert)
                        if (dao.insert(item)>0){
                            Toast.makeText(context, "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        // type =1 (update)
                        item.maLoai = Integer.parseInt(edMaLS.getText().toString());
                        if (dao.update(item)>0){
                            Toast.makeText(context, "Sửa loại sách thành công", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(context, "Sửa loại sách không thành công", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(), "Xóa loại sách thành công", Toast.LENGTH_SHORT).show();
                    capNhatLV();
                    dialogInterface.cancel();
                }else {
                    Toast.makeText(getContext(), "Xóa loại sách không thành công", Toast.LENGTH_SHORT).show();

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
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edTenLS.getText().length()==0){
            Toast.makeText(getContext(), "Dữ liệu không được để trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}