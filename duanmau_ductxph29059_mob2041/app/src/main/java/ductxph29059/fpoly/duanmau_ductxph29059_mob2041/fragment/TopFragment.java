package ductxph29059.fpoly.duanmau_ductxph29059_mob2041.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.Dao.ThongKeDao;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.R;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.adapter.TopAdapter;
import ductxph29059.fpoly.duanmau_ductxph29059_mob2041.obj.Top;


public class TopFragment extends Fragment {

    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        lv=v.findViewById(R.id.lvTop);
        ThongKeDao thongKeDao=new ThongKeDao(getActivity());
        list=(ArrayList<Top>) thongKeDao.getTop();
        adapter=new TopAdapter(getActivity(),this.list);
        lv.setAdapter(adapter);
        return v;
    }
}