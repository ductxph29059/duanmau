package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.ThuThuDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.ThuThu;


public class AddUserFragment extends Fragment {
    TextInputEditText edUser,edHoTen,edPass,edRePass;
    Button btnSave,btnCancel;
    ThuThuDao dao;
    List<ThuThu> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_user, container, false);
        edUser = v.findViewById(R.id.edUser);
        edHoTen = v.findViewById(R.id.edName);
        edPass = v.findViewById(R.id.edPass);
        edRePass = v.findViewById(R.id.edRePass);
        btnSave = v.findViewById(R.id.btnSaveUser);
        btnCancel = v.findViewById(R.id.btnCancelAddUser);

        dao = new ThuThuDao(getActivity());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUser.setText("");
                edHoTen.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ThuThu thuThu = new ThuThu();
                thuThu.maTT = edUser.getText().toString().trim();
                thuThu.hoTen = edHoTen.getText().toString().trim();
                thuThu.matKhau = edPass.getText().toString().trim();
                if (validate()>0){
                    if (dao.insert(thuThu)>0){
                        Toast.makeText(getActivity(), "Lưu người dùng thành công", Toast.LENGTH_SHORT).show();
                        edUser.setText("");
                        edHoTen.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Lưu người dùng không thành công", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        return v;
    }
    public int validate(){
        list = new ArrayList<ThuThu>();
        list = dao.getAll();
        int check = 1;
        if (edUser.getText().length()==0||
                edHoTen.getText().length()==0||
                edPass.getText().length()==0||
                edRePass.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check =-1;
        }else {
            String pass = edPass.getText().toString().trim();
            String rePass = edRePass.getText().toString().trim();
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            for (int i=0;i<list.size();i++){
                if (edUser.getText().toString().trim().equals(list.get(i).maTT)){
                    Toast.makeText(getContext(), "Mã người dùng đã được đăng ký", Toast.LENGTH_SHORT).show();
                    check = -1;
                }
            }


        }

        return check;
    }



}