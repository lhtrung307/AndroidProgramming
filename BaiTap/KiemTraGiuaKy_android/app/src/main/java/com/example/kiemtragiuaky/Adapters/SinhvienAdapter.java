package com.example.kiemtragiuaky.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kiemtragiuaky.Activities.SinhvienActivity;
import com.example.kiemtragiuaky.Models.SinhVien;
import com.example.kiemtragiuaky.R;

import java.util.List;

public class SinhvienAdapter extends BaseAdapter {
    List<SinhVien> list_Sinhvien;

    public SinhvienAdapter(List<SinhVien> list_Sinhvien, Context context) {
        this.list_Sinhvien = list_Sinhvien;
        this.context = context;
    }

    Context context;

    @Override
    public int getCount() {
        return list_Sinhvien.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.customlistviewsinhvien, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaSinhVien = convertView.findViewById(R.id.tvMaSV);
            viewHolder.tvTenSinhVien = convertView.findViewById(R.id.tvTenSV);
            viewHolder.tvisCheckedSV = convertView.findViewById(R.id.tvisCheckedSV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        // cập nhật giá trị lên editText sau khi item trong listview được click
        SinhVien svn = list_Sinhvien.get(position);
        viewHolder.tvMaSinhVien.setText(svn.getMaSV() + "");
        viewHolder.tvTenSinhVien.setText(svn.getHoVaTen());
        if (svn.isChecked()) {
            viewHolder.tvisCheckedSV.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvisCheckedSV.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView tvMaSinhVien, tvTenSinhVien, tvisCheckedSV;
    }
}
