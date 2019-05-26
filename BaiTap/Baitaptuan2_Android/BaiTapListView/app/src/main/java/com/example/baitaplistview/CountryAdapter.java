package com.example.baitaplistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends BaseAdapter {

    private List<Country> countries;
    private LayoutInflater layoutInflater;
    private Context context;

    public CountryAdapter(List<Country> countries, Context context) {
        this.countries = countries;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }



    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CountryHolder holder;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new CountryHolder();
            holder.countryNameView = (TextView) convertView.findViewById(R.id.countryName);
            holder.populationView = (TextView) convertView.findViewById(R.id.population);
            holder.imgView = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }
        else {
            holder = (CountryHolder) convertView.getTag();
        }


        Country country = this.countries.get(position);
        holder.countryNameView.setText(country.getCountryName());
        holder.populationView.setText("Population: " + country.getPopulation());

        int imageId = this.getMipmapResIdByName(country.getCountryImg());

        holder.imgView.setImageResource(imageId);

        return convertView;

    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class CountryHolder{
        ImageView imgView;
        TextView countryNameView;
        TextView populationView;
    }
}
