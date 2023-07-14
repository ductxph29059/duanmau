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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.PhieuMuonDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.SachDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.ThanhVienDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter.PhieuMuonAdapter;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter.SachSpinnerAdapter;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter.ThanhVienSpinnerAdapter;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.PhieuMuon;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.Sach;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.ThanhVien;


public class PhieuMuonFragment extends Fragment {
    ListView lv;
    ArrayList<PhieuMuon> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV,spSach;
    TextView tvNgay,tvTienThue;
    CheckBox chkTraSach;
    Button btnSave,btnCancel;
    static PhieuMuonDao dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDao thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDao sachDAO;
    Sach sach;
    int maSach,tienThue;
    int positionTV,positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lv = v.findViewById(R.id.lv_PhieuMuon);
        fab = v.findViewById(R.id.fabPhieuMuon);
        dao = new PhieuMuonDao(getActivity());
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
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);

        thanhVienDAO = new ThanhVienDao(context);
        //listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context,listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        // lấy mã thành viên
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maThanhVien = listThanhVien.get(i).maTV;
                //Toast.makeText(context, "Chọn: "+listThanhVien.get(i).hoTen, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sachDAO = new SachDao(context);
        //listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context,listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        // Lấy mã sách và tiền thuê
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSach = listSach.get(i).maSach;
                tienThue = listSach.get(i).giaThue;
                //Toast.makeText(context, "Chọn: "+listSach.get(i).tenSach, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Kiểm tra type insert 0 hay update 1
        edMaPM.setEnabled(false);
        if (type!=0){
            edMaPM.setText(String.valueOf(item.maPM));
            for (int i = 0;i<listThanhVien.size();i++){
                if (item.maTV==(listThanhVien.get(i).maTV)){
                    positionTV = i;
                }
            }
            spTV.setSelection(positionTV);
            for (int i = 0;i<listSach.size();i++){
                if (item.maSach==(listSach.get(i).maSach)){
                    positionSach = i;
                }
            }
            spSach.setSelection(positionSach);
            tvNgay.setText("Ngày thuê: "+sdf.format(item.ngay));
            tvTienThue.setText("Tiền thuê: "+item.tienThue);
            if (item.traSach==1){
                chkTraSach.setChecked(true);
            }else {
                chkTraSach.setChecked(false);
            }

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
                item = new PhieuMuon();
                item.maSach = maSach;
                item.maTV = maThanhVien;
                item.ngay = new Date();
                item.tienThue = tienThue;
                if (chkTraSach.isChecked()){
                    item.traSach=1;
                }else {
                    item.traSach=0;
                }
                if (validate()>0){
                    if (type == 0) {
                        if (dao.insert(item)>0){
                            Toast.makeText(context, "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm phiếu mượn không thành công", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        item.maPM = Integer.parseInt(edMaPM.getText().toString());
                        if (dao.update(item)>0){
                            Toast.makeText(context, "Cập nhật phiếu mượn thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Cập nhật phiếu mượn không thành công", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(getContext(), "Xóa phiếu mượn thành công", Toast.LENGTH_SHORT).show();
                    capNhatLV();
                    dialogInterface.cancel();
                }else {
                    Toast.makeText(getContext(), "Xóa phiếu mượn không thành công", Toast.LENGTH_SHORT).show();

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
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
//        if (edTenTV.getText().length()==0||edNamSinh.getText().length()==0){
//            Toast..makeText(getContext(), "Dữ liệu không được để trống", Toast.LENGTH_SHORT).show();
//            check = -1;
//        }
        return check;
    }

}