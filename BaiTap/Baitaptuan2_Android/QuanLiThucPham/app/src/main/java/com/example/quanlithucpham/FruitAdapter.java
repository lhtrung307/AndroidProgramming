package com.example.quanlithucpham;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends BaseAdapter {
    List<Fruit> fruits;
    private LayoutInflater layoutInflater;
    private Context context;

    public FruitAdapter(List<Fruit> fruits, Context context) {
        this.fruits = fruits;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return fruits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FruitHolder holder;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new FruitHolder();
            holder.fruitNameView = (TextView) convertView.findViewById(R.id.fruitName);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.description);
            holder.imgView = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }
        else {
            holder = (FruitHolder) convertView.getTag();
        }


        Fruit fruit = this.fruits.get(position);
        holder.fruitNameView.setText(fruit.getFruitName());
        holder.descriptionView.setText("Description: " + fruit.getDescription());

        int imageId = this.getMipmapResIdByName(fruit.getImg());

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

    static class FruitHolder{
        ImageView imgView;
        TextView fruitNameView;
        TextView descriptionView;
    }
}
