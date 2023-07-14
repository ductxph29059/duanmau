package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.SachDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.ThanhVienDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment.PhieuMuonFragment;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.PhieuMuon;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.Sach;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.ThanhVien;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> list;
    TextView tvMaPM,tvTenTV, tvTenSach, tvTienThue,tvNgay,tvTraSach;
    ImageView imgDel;
    SachDao sachDAO;
    ThanhVienDao thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonAdapter(Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.phieu_muon_item,null);

        }
        final  PhieuMuon item = list.get(position);
        if (item!=null){
            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã phiếu mượn: "+item.maPM);
            sachDAO = new SachDao(context);
            Sach sach = sachDAO.getID(String.valueOf(item.maSach));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.tenSach);
            thanhVienDAO = new ThanhVienDao(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.maTV));
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành viên: "+thanhVien.hoTen);
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê: "+item.tienThue);
            tvNgay =v.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày thuê: "+sdf.format(item.ngay));
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if (item.traSach==1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            imgDel = v.findViewById(R.id.img_DeletePM);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Gọi phương thức xóa phiếu mượn ở fragmentPhieuMuon
                    fragment.xoa(String.valueOf(item.maPM));
                }
            });
        }
        return v;
    }
}
