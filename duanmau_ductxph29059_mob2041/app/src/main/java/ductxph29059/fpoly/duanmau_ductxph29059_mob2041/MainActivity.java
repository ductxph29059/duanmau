package ductxph29059.fpoly.duanmau_ductxph29059_mob2041;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.ThuThuDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.AddUserFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.DoanhThuFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.DoimatkhauFragement;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.LoaiSachFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.PhieuMuonFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.SachFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.ThanhVienFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.TopFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.ThuThu;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View mHeaderView;
    TextView tvUser;
    ThuThuDao thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        thuThuDAO = new ThuThuDao(this);
        // set toolbar thay thế cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        ab.setDisplayHomeAsUpEnabled(true);

        // Dùng fragment phiếu mượn làm home
        FragmentManager manager = getSupportFragmentManager();
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,phieuMuonFragment)
                .commit();

        NavigationView nv = findViewById(R.id.nv_View);
        // show user in header
        mHeaderView = nv.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.hoTen;
        tvUser.setText("Welcome "+username+"!");

        // admin có quyền add user
        if (user.equalsIgnoreCase("duc1")) {
            nv.getMenu().findItem(R.id.sub_AddUser).setVisible(true);
        }

        // Điều hướng Navigation
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_PhieuMuon:
                        setTitle("Quản lý phiếu mượn");
                        PhieuMuonFragment phieuMuonFragment1 = new PhieuMuonFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,phieuMuonFragment1)
                                .commit();
                        break;
                    case R.id.nav_LoaiSach:
                        setTitle("Quản lý loại sách");
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,loaiSachFragment)
                                .commit();
                        break;
                    case R.id.nav_Sach:
                        setTitle("Quản lý sách");
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction().replace(R.id.flContent,sachFragment)
                                .commit();
                        break;
                    case R.id.nav_ThanhVien:
                        setTitle("Quản lý thành viên");
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,thanhVienFragment)
                                .commit();
                        break;
                    case R.id.sub_Top:
                        setTitle("Top 10 sách cho thuê nhiều nhất");
                        TopFragment topFragment = new TopFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,topFragment)
                                .commit();
                        break;
                    case R.id.sub_DoanhThu:
                        setTitle("Thống kê doanh thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,doanhThuFragment)
                                .commit();
                        break;
                    case R.id.sub_AddUser:
                        setTitle("Thêm người dùng");
                        AddUserFragment addUserFragment = new AddUserFragment();
                        manager.beginTransaction()
                                .replace(R.id.flContent,addUserFragment)
                                .commit();
                        break;
                    case R.id.sub_Pass:
                        setTitle("Thay đổi mật khẩu");
                        DoimatkhauFragement doimatkhauFragement = new DoimatkhauFragement();
                        manager.beginTransaction()
                                .replace(R.id.flContent,doimatkhauFragement)
                                .commit();
                        break;
                    case R.id.sub_Logout:
                        setTitle("Quản lý phiếu mượn");
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        finish();
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home)
            drawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}