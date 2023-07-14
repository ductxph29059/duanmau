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
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.Sach;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
    TextView tvMaSach, tvTenSach;
    public SachSpinnerAdapter(Context context, ArrayList<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {

        if (v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item_spinner,null);
        }
        final  Sach item = list.get(position);
        if (item!=null){
            tvMaSach = v.findViewById(R.id.tvMaSachSP);
            tvMaSach.setText(item.maSach+". ");
            tvTenSach = v.findViewById(R.id.tvTenSachSP);
            tvTenSach.setText(item.tenSach+". ");
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item_spinner,null);
        }
        final  Sach item = list.get(position);
        if (item!=null){
            tvMaSach = v.findViewById(R.id.tvMaSachSP);
            tvMaSach.setText(item.maSach+". ");
            tvTenSach = v.findViewById(R.id.tvTenSachSP);
            tvTenSach.setText(item.tenSach+". ");
        }
        return v;
    }

}
