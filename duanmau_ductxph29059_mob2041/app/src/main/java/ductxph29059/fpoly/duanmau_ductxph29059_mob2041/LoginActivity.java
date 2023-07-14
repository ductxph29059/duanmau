package ductxph29059.fpoly.duanmau_ductxph29059_mob2041;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.ThuThuDao;

public class LoginActivity extends AppCompatActivity {


    EditText edUsername,edPassword;
    Button btnLogin,btnCancel;
    CheckBox chkRememberPass;
    String strUser,strPass;
    ThuThuDao dao;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassowrd);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);

        dao = new ThuThuDao(this);

        // Đọc user,pass trong SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUsername.setText(pref.getString("USERNAME",""));
        edPassword.setText(pref.getString("PASSWORD",""));
        chkRememberPass.setChecked(pref.getBoolean("REMEMBER",false));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUsername.setText("");
                edPassword.setText("");
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }
    public void checkLogin(){
        strUser = edUsername.getText().toString().trim();
        strPass = edPassword.getText().toString().trim();
        if (strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();

        }else {
            if (dao.checkLogin(strUser,strPass)>0){
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkRememberPass.isChecked());

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",strUser);
                startActivity(intent);
                finish();

            }else {
                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rememberUser(String u,String p,boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status){
            // Xóa tình trạng lưu trữ trước đó
            edit.clear();
        }else {
            // Lưu dữ liệu
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        // Lưu lại toàn bộ
        edit.commit();

    }
}