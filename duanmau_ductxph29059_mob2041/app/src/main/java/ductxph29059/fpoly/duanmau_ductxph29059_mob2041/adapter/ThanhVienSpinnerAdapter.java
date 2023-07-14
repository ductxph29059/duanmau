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
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.ThanhVien;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV;
    public ThanhVienSpinnerAdapter(Context context,ArrayList<ThanhVien> list) {
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
            v = inflater.inflate(R.layout.thanhvien_item_spinner,null);
        }
        final  ThanhVien item = list.get(position);
        if (item!=null){
            tvMaTV = v.findViewById(R.id.tvMaTVSP);
            tvMaTV.setText(item.maTV+". ");
            tvTenTV = v.findViewById(R.id.tvTenTVSP);
            tvTenTV.setText(item.hoTen+". ");
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanhvien_item_spinner,null);
        }
        final  ThanhVien item = list.get(position);
        if (item!=null){
            tvMaTV = v.findViewById(R.id.tvMaTVSP);
            tvMaTV.setText(item.maTV+". ");
            tvTenTV = v.findViewById(R.id.tvTenTVSP);
            tvTenTV.setText(item.hoTen+". ");
        }
        return v;
    }
}
