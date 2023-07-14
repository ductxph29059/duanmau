package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.LoaiSachDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.SachFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.LoaiSach;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.Sach;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    SachFragment fragment;
    private ArrayList<Sach> list;
    TextView tvMaSach,tvTenSach,tvGiaThue,tvLoaiSach;
    ImageView imgDel;
    LoaiSachDao loaiSachDAO;

    public SachAdapter(Context context, SachFragment fragment, ArrayList<Sach> list) {
        super(context, 0,list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item,null);
        }
        final Sach item = list.get(position);
        if (item!=null){
            LoaiSachDao loaiSachDao=new LoaiSachDao(context);

            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã sách: "+item.maSach);

            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+item.tenSach);

            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá thuê: "+item.giaThue);

            LoaiSach loaiSach = loaiSachDao.getID(String.valueOf(item.maLoai));
            tvLoaiSach = v.findViewById(R.id.tvLoaiSach);
            tvLoaiSach.setText("Tên loại sách: "+loaiSach.tenLoai);
            imgDel = v.findViewById(R.id.imgDeleteTV);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gọi function xóa trong ThanhVienFragment
                fragment.xoa(String.valueOf(item.maSach));
            }
        });
        return v;

    }
}
