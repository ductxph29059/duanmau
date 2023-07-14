package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment;

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

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.ThanhVienDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter.ThanhVienAdapter;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.ThanhVien;


public class ThanhVienFragment extends Fragment {

    ListView lv;
    ArrayList<ThanhVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV,edTenTV,edNamSinh;
    Button btnSave,btnCancel;
    static ThanhVienDao dao;
    ThanhVienAdapter adapter;
    ThanhVien item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lv = v.findViewById(R.id.lvThanhVien);
        fab = v.findViewById(R.id.fabThanhVien);
        dao = new ThanhVienDao(getActivity());
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
        dialog.setContentView(R.layout.thanhvien_dialog);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        // Kiểm tra type insert 0 hay update 1
        edMaTV.setEnabled(false);
        if (type != 0){
            edMaTV.setText(String.valueOf(item.maTV));
            edTenTV.setText(item.hoTen);
            edNamSinh.setText(item.namSinh);
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
                item = new ThanhVien();
                item.hoTen = edTenTV.getText().toString();
                item.namSinh = edNamSinh.getText().toString();
                if (validate()>0){
                    if (type==0){
                        // type = 0 (insert)
                        if (dao.insert(item)>0){
                            Toast.makeText(context, "Thêm thành viên thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thành viên không thành công", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        // type =1 (update)
                        item.maTV = Integer.parseInt(edMaTV.getText().toString());
                        if (dao.update(item)>0){
                            Toast.makeText(context, "Sửa thành viên thành công", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(context, "Sửa thành viên không thành công", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(), "Xóa thành viên thành công", Toast.LENGTH_SHORT).show();
                    capNhatLV();
                    dialogInterface.cancel();
                }else {
                    Toast.makeText(getContext(), "Xóa thành viên không thành công", Toast.LENGTH_SHORT).show();

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
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edTenTV.getText().length()==0||edNamSinh.getText().length()==0){
            Toast.makeText(getContext(), "Dữ liệu không được để trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}