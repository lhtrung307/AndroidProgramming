package com.example.kiemtragiuaky.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiemtragiuaky.Models.Mon;
import com.example.kiemtragiuaky.R;

import java.util.List;

public class MonhocAdapter extends BaseAdapter {
    List<Mon> listMonhoc;
    Context context;

    public MonhocAdapter(List<Mon> listMonhoc, Context context) {
        this.listMonhoc = listMonhoc;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listMonhoc.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.customviewmon, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaMon = convertView.findViewById(R.id.tvMamon);
            viewHolder.tvTenMon = convertView.findViewById(R.id.tvTenMon);
            viewHolder.tvSoTiet = convertView.findViewById(R.id.tvSoTiet);
            viewHolder.tvisChecked = convertView.findViewById(R.id.tvisCheck);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Mon mon = listMonhoc.get(position);
        viewHolder.tvMaMon.setText(mon.getMaMon()+ "");
        viewHolder.tvTenMon.setText(mon.getTenMon());
        viewHolder.tvSoTiet.setText(mon.getSoTiet()+"");
        if (mon.isChecked()) {
            viewHolder.tvisChecked.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvisChecked.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView tvMaMon, tvTenMon,tvSoTiet ,tvisChecked;
    }
}

