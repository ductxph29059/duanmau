package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.LoaiSach;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> list;
    TextView tvMaLS, tvTenLS;
    public LoaiSachSpinnerAdapter(Context context, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loaisach_item_spinner,null);
        }
        final  LoaiSach item = list.get(position);
        if (item!=null){
            tvMaLS = v.findViewById(R.id.tvTenLoaiSachSP);
            tvMaLS.setText(item.maLoai+". ");
            tvTenLS = v.findViewById(R.id.tvTenLoaiSachSP);
            tvTenLS.setText(item.tenLoai+". ");
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loaisach_item_spinner,null);
        }
        final  LoaiSach item = list.get(position);
        if (item!=null){
            tvMaLS = v.findViewById(R.id.tvMaLoaiSachSP);
            tvMaLS.setText(item.maLoai+". ");
            tvTenLS = v.findViewById(R.id.tvTenLoaiSachSP);
            tvTenLS.setText(item.tenLoai+". ");
        }
        return v;
    }
}
