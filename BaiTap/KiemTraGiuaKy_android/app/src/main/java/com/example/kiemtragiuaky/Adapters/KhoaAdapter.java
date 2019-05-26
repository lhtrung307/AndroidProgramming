package com.example.kiemtragiuaky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kiemtragiuaky.Models.Khoa;
import com.example.kiemtragiuaky.R;
import java.util.List;

public class KhoaAdapter extends BaseAdapter {
    List<Khoa> listKhoa;
    Context context;

    public KhoaAdapter(List<Khoa> listKhoa, Context context) {
        this.listKhoa = listKhoa;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listKhoa.size();
    }

    @Override
    public Object getItem(int position) {
        return listKhoa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listKhoa.get(position).getMakhoa();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.customviewkhoa,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaKhoa = convertView.findViewById(R.id.txtMakhoa);
            viewHolder.tvTenKhoa = convertView.findViewById(R.id.txtTenKhoa);
            viewHolder.tvisChecked = convertView.findViewById(R.id.tvisChecked);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Khoa khoa = listKhoa.get(position);
        viewHolder.tvMaKhoa.setText(khoa.getMakhoa()+"");
        viewHolder.tvTenKhoa.setText(khoa.getTenkhoa());
        if(khoa.isChecked()){
            viewHolder.tvisChecked.setVisibility(View.VISIBLE);
        }
        else{
            viewHolder.tvisChecked.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView tvMaKhoa,tvTenKhoa,tvisChecked;
    }

}
