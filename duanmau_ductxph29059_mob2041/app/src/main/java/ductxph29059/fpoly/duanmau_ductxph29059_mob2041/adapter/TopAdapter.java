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
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.TopFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.Top;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment fragment;
    private ArrayList<Top> list;
    TextView tvSach,tvSL;

    public TopAdapter(Context context, ArrayList<Top> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item,null);

        }
        final Top item = list.get(position);
        if (item!=null){
            tvSach = v.findViewById(R.id.tvTenSachTop);
            tvSach.setText("Sách: "+item.tenSach);
            tvSL = v.findViewById(R.id.tvSoLuong);
            tvSL.setText("Số lượng: "+item.soLuong);
        }
        return v;
    }
}
