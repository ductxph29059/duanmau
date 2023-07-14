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

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.ThanhVienFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.ThanhVien;

public class ThanhVienAdapter  extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV,tvTenTV,tvNamSinh;
    ImageView imgDel;

    public ThanhVienAdapter(Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> list) {
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
            v = inflater.inflate(R.layout.thanhvien_item,null);
        }
        final ThanhVien item = list.get(position);
        if (item!=null){
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã thành viên: "+item.maTV);

            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên thành viên: "+item.hoTen);
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Mã thành viên: "+item.namSinh);
            imgDel = v.findViewById(R.id.imgDeleteTV);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gọi function xóa trong ThanhVienFragment
                fragment.xoa(String.valueOf(item.maTV));
            }
        });
        return v;

    }
}
