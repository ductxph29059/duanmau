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
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.LoaiSachFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.LoaiSach;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> list;
    TextView tvMaLS,tvTenLS;
    ImageView imgDel;

    public LoaiSachAdapter(Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> list) {
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
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loaisach_item,null);
        }
        final LoaiSach item = list.get(position);
        if (item!=null){
            tvMaLS = v.findViewById(R.id.tvMaLS);
            tvMaLS.setText("Mã loại sách: "+item.maLoai);

            tvTenLS = v.findViewById(R.id.tvTenLS);
            tvTenLS.setText("Tên loại sách: "+item.tenLoai);

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gọi function xóa trong ThanhVienFragment
                fragment.xoa(String.valueOf(item.maLoai));
            }
        });
        return v;

    }
}
