package com.example.bai1tuan6;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    Activity context;
    int layout_resrc;
    ArrayList<Employee> data =null;
    public EmployeeAdapter( Activity context, int resource, ArrayList<Employee> list_Emp) {
        super(context, resource, list_Emp);
        this.context = context;
        this.layout_resrc = resource;
        this.data = list_Emp;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=
                context.getLayoutInflater();
        convertView=inflater.inflate(layout_resrc, null);
        if(data.size()>0&&position>=0) {
            final TextView txtID = (TextView) convertView.findViewById(R.id.viewEmployee);
            final Employee emp = data.get(position);
            txtID.setText(emp.toString());
            final ImageView imgItem = (ImageView) convertView.findViewById(R.id.imgAvatar);
            if (emp.isGender())// nếu là Nam thì lấy hình boy
                imgItem.setImageResource(R.drawable.boy);
            else//nếu là Nữ thì lấy hình girl
                imgItem.setImageResource(R.drawable.girl);
        }
        return convertView;
    }
}
