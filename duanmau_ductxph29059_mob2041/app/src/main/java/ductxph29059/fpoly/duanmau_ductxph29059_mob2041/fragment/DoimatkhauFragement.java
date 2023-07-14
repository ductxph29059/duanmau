package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.ThuThuDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.ThuThu;


public class DoimatkhauFragement extends Fragment {

    TextInputEditText edPassOld,edPassNew,edRePass;
    Button btnSave,btnCancel;
    ThuThuDao dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doimatkhau_fragement, container, false);
        edPassOld = v.findViewById(R.id.edPassowrdOld);
        edPassNew = v.findViewById(R.id.edPassowrdNew);
        edRePass = v.findViewById(R.id.edRePassowrd);
        btnSave = v.findViewById(R.id.btnSave);
        btnCancel = v.findViewById(R.id.btnCancelChangePass);

        dao = new ThuThuDao(getActivity());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edPassOld.setText("");
                edPassNew.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Đọc user,pass trong SharedPrefences
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if (validate()>0){
                    ThuThu thuThu = dao.getID(user);
                    thuThu.matKhau = edPassNew.getText().toString().trim();
                    if (dao.updatePass(thuThu)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPassNew.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        return v;
    }



    public  int validate(){
        int check = 1;
        if (edPassOld.getText().length()==0 || edPassNew.getText().length()==0
                || edRePass.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            // Đọc user, pass trong shareFreferences
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD","");
            String pass = edPassNew.getText().toString().trim();
            String rePass = edRePass.getText().toString().trim();
            if (!passOld.equals(edPassOld.getText().toString().trim())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

}